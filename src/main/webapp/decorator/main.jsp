<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='sec' %>
<c:set var="serverUrl" value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <!-- Standard Meta -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <!-- Site Properties -->
    <title><sitemesh:write property='title'/></title>

    <link rel="icon" href="./images/favicon.ico" mce_href="./images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="./css/semantic.css">
    <link rel="stylesheet" type="text/css" href="./css/app.css">

    <script src="./js/jquery-3.2.1.min.js" ></script>
    <script src="./js/semantic.js" ></script>
    <script src="./js/grc.js" ></script>

    <style type="text/css">
        body {
            background-color: #FFFFFF;
        }
        .ui.menu .item img.logo {
            margin-right: 1.5em;
            height: auto;
            width: auto;
        }
        .main.container {
            margin-top: 5em;
        }
        .wireframe {
            margin-top: 2em;
        }
        .ui.footer.segment {
            margin: 5em 0em 0em;
            padding: 5em 0em;
        }

        .app_left_menu {
            width: 5rem;
            display: inline;
        }

        .app_content{
            /*width: calc(100% - 10rem);*/
            width: calc(100%);
        }

        .ui.vertical.menu {
            width: 19rem;
            margin-left: 1.0rem;
            margin-right: 1.0rem;
        }
    </style>
    <sitemesh:write property='head'/>
</head>
<body>
<div class="ui fixed inverted menu">
    <div class="ui container">
        <a href="#" class="item">
            <img class="logo" src="./images/CG_logo_teal.png">
        </a>
        <a class="item" href="userRequest-list">用户需求</a>
        <a class="item" href="userRequest-add">新建需求</a>
        <a class="item" href="test">test</a>

        <div class="right menu">
            <div class="ui simple dropdown item">
                <i class="user icon"></i>您好，<sec:authentication property="principal.displayName"/>
                <i class="dropdown icon"></i>
                <div class="menu">
                    <a class="item" href="user?userId=<sec:authentication property="principal.id"/>">账号信息</a>
                    <a class="item" href="grc_logout">Logout</a>
                </div>
            </div>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="right menu">
                <div class="ui simple dropdown item">用户管理
                    <i class="dropdown icon"></i>
                    <div class="menu">
                      <a class="item" href="user-list">用户列表</a>
                        <a class="item" href="userAction-list">用户操作记录</a>
                    </div>
                </div>
            </div>
            </sec:authorize>
            <div class="right menu">
                <div class="ui simple dropdown item">工具
                    <i class="dropdown icon"></i>
                    <div class="menu">
                        <a class="item" href=""/>过境窗口查询</a>
                        <a class="item" href=""/>经纬度转换</a>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<div class="ui main container" style="min-height: 100px">
    <div class="ui grid">
        <%--<div class="app_left_menu">--%>
        <%--</div>--%>
        <div class="app_content">
            <sitemesh:write property='body'/>
        </div>
    </div>
</div>

</body>
</html>
