package com.zhang.bme.cas.filter;

import com.zhang.bme.SpringContextUtil;
import com.zhang.bme.cas.CasClientProperties;
import com.zhang.bme.util.PatternUtil;
import lombok.extern.slf4j.Slf4j;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2019/10/30.
 */
@Slf4j
public class CASLoginFilter implements Filter {


    private String excludedURL; //存储web.xml里面配置的filter的init-param的init-value
    private String[] exUrls = {};

    @Autowired
    CasClientProperties casClientProperties;

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        excludedURL = filterConfig.getInitParameter("excludedURL");
        if(excludedURL != null){
            exUrls = excludedURL.split(","); //使用,号分割
        }
        log.info("==> excludedURL :" + excludedURL);
    }

    /**
     * 由于Filter在servlet之前初始化，所以 @Autowired无法注入
     * 采用从上下文获取
     */
    private void initCasProperties(){
        if(null == casClientProperties){
            casClientProperties = SpringContextUtil.getInstance().getBean("casClientProperties");
            log.info("SpringContext get bean :" + casClientProperties);
        }
    }

    /**
     * 校验是否忽略请求
     * @param request
     * @return
     */
    private boolean checkIgnoreUrl(HttpServletRequest request){
        boolean isExcludedPage = false;
        for(int i=0 ; i<exUrls.length ; i++){
            if("".equals(exUrls[i]) || exUrls[i] == null){
                continue;
            }
            //判断请求的页面是否excluded page
            if(request.getServletPath().startsWith(exUrls[i].trim())){
                isExcludedPage = true;
                log.info("==> ignore url :"+exUrls[i]);
                break;
            }
        }
        return isExcludedPage;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("==> CASAuthTicketFilter.doFilter()");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        initCasProperties();
        // 设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        boolean isExcludedPage = false;
        log.info("==> request url :" + request.getRequestURL());
        log.info("==> request param:"+ request.getQueryString());
        log.info("==> servletPath :" + request.getServletPath());

        isExcludedPage = checkIgnoreUrl(request);
        if(isExcludedPage){
            filterChain.doFilter(request, response);
            return;
        }

        // 如果ticket存在，则表示前端采用了CAS页面登录，且未使用CAS定义的验证链接，自行进行验证（自定义模式）
        String ticket = request.getParameter("ticket");
        if(null != ticket){

            log.info("==> CAS servcer ticket checking... ");
            // "https://localhost:8443/cas/serviceValidate?service=https%3A%2F%2Flocalhost%3A8443%2FTest%2F&ticket=ST-1-v0dwVSgdfwGVpFySbzSr&"
            String casTicketCheckURL = casClientProperties.getServerValidate()+"?service="+request.getRequestURL()+"&ticket="+ticket;
            log.info("==> casTicketCheckURL:" + casTicketCheckURL);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(casTicketCheckURL, String.class);
            String responseBody = responseEntity.getBody();
            HttpStatus statusCode = responseEntity.getStatusCode();
            log.info("==> responsecode:"+statusCode);
            log.info("==> responsebody:"+responseBody);

            if(HttpStatus.OK == statusCode){
                String username = PatternUtil.getRgexText(responseBody,"<cas:user>(.*?)</cas:user>");
                if(!StringUtils.isEmpty(username)){
                    log.info("==> CAS server ticket check success.");
                    request.getSession().setAttribute("USER",username);
                    request.getSession().setAttribute("CAS",true);
                    // 去掉ticket 跳转访问请求过来的链接
                    response.sendRedirect(request.getRequestURL().toString());
                }else{
                    // ticket失效，或者验证失败
                    log.error("==> CAS ticket  not recognized:" + ticket);
                    // 后续判断会跳转到登录页
                }
            }else {
                log.error("==> http status error,please check CAS Server !" + statusCode);
                return;
            }
        }

        // 本地用户
        String username = (String) request.getSession().getAttribute("USER");
        // CAS中心用户 (CAS配置模式)
        Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        if(null != username){
            Object CASFlag =  request.getSession().getAttribute("CAS");
            if(CASFlag == null ){
                log.info("==> LOCAL 本地登录用户.");
            }else{
                log.info("==> CAS-Server 认证用户.");
            }
            log.info("==> loginuser : " + username);
            log.info("==> sessionid :" +  request.getSession().getId());
            request.getSession().setAttribute("USER",username);
            filterChain.doFilter(request,response);

        }else if(assertion !=  null){
            log.info("==> CAS 认证中心登录用户.");
            AttributePrincipal principal = assertion.getPrincipal();
            log.info("==> loginuser : " + principal.getName());
            log.info("==> sessionid :" +  request.getSession().getId());

            request.getSession().setAttribute("USER",principal.getName());
            request.getSession().setAttribute("CAS",true);
            filterChain.doFilter(request,response);
        }else{
            // 非法请求，跳转到登录页面
            log.info("==> cas and local not login ,skip login page");
            String onOff = casClientProperties.getOnOff();
            log.info("==> sso onOff :"  + onOff );

            String ssoLoginUrl ="";
            if("true".equals(onOff) || "on".equalsIgnoreCase(onOff) ){
                ssoLoginUrl = casClientProperties.getServerLoginUrl()+"?service="+request.getRequestURL();
                // 参数不为空，添加参数
               /* if(!StringUtils.isEmpty(request.getQueryString())){
                    ssoLoginUrl += "?"+request.getQueryString();
                }*/
            }else{
                ssoLoginUrl = casClientProperties.getClientLoginUrl();
            }

            log.info("==> login url :"  + ssoLoginUrl );
            response.sendRedirect(ssoLoginUrl);
        }

    }

    @Override
    public void destroy() {

    }
}
