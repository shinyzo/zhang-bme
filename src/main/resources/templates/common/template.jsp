<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:fragment="header(title, link, style)">
    <th:block th:if="${title == null}">
        <title>BME-平台基础框架</title>
    </th:block>
    <th:block th:if="${title != null}">
        <title th:replace="${title}">title</title>
    </th:block>

    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="BME后台管理系统" />
    <meta name="keywords" content="BME, BME后台管理系统, SpringBoot, Jpa, Thymeleaf, Shiro" />

    <link rel="shortcut icon" th:href="@{/favicon.ico}" type="image/x-icon">
    <link rel="stylesheet" th:href="@{/css/layui/css/layui.css}"  media="all">
    <link rel="stylesheet" th:href="@{/css/main.css}"  media="all">
    <th:block th:if="${link != null}">
        <th:block th:replace="${link}"></th:block>
    </th:block>

    <th:block th:if="${style != null}">
        <th:block th:replace="${style}"></th:block>
    </th:block>
</head>
<body>
    <th:block th:fragment="script">
        <script th:src="@{/css/layui/layui.js}" charset="utf-8"></script>
       <script th:src="@{/js/main.js}" charset="utf-8"></script>
    </th:block>
</body>
</html>