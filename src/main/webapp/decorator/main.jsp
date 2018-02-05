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
            margin-top: 7em;
        }
        .wireframe {
            margin-top: 2em;
        }
        .ui.footer.segment {
            margin: 5em 0em 0em;
            padding: 5em 0em;
        }

        .app_left_menu {
            width: 21rem;
            display: inline;
        }

        .app_content{
            width: calc(100% - 22rem);
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
            <!--长光卫星 - 地面资源中心-->
        </a>
        <div class="right menu">
            <div class="ui simple dropdown item">
                <i class="user icon"></i>萨瓦迪卡，<sec:authentication property="principal.displayName"/>
                <i class="dropdown icon"></i>
                <div class="menu">
                    <a class="item" href="user?userId=<sec:authentication property="principal.id"/>">账号信息</a>
                    <a class="item" href="grc_logout">Logout</a>
                </div>
            </div>
            <div class="ui simple dropdown item">
                <i class="configure icon"></i>开发工具
                <i class="dropdown icon"></i>
                <div class="menu">
                    <a class="item" href="webservice">Webservice</a>
                </div>
            </div>
            <div class="item">
                <div class="ui mini icon input">
                    <input type="text" placeholder="很强大，但是还没做">
                    <i class="search icon"></i>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="ui main container" style="min-height: 680px">
    <div class="ui grid">
        <div class="app_left_menu">
            <div class="ui vertical menu">
                <div class="item">
                    <div class="header">User Requirements</div>
                    <div class="menu">
                        <a class="item" href="userRequest-list" >需求检索<div class="ui mini left pointing teal label">可以用了</div></a>
                        <a class="item" href="userRequest-submit">新建需求<div class="ui mini left pointing teal label">可以用了</div></a>
                    </div>
                </div>
                <div class="item">
                    <div class="header">Imaging Plan</div>
                    <div class="menu">
                        <a class="item" href="#">拍摄计划检索<div class="ui mini left pointing blue label">新想出来的</div></a>
                        <a class="item">More</a>
                    </div>
                </div>
                <div class="item">
                    <div class="header">Imaging Tasks</div>
                    <div class="menu">
                        <a class="item" href="imagingTask-list">拍摄任务检索<div class="ui mini left pointing blue label">开发中</div></a>
                        <a class="item">More</a>
                    </div>
                </div>
                <div class="item">
                    <div class="header">Receiving Plan</div>
                    <div class="menu">
                        <a class="item">跟踪接收计划<div class="ui mini left pointing label">等昊哥呢</div></a>
                        <a class="item">More</a>
                    </div>
                </div>
                <div class="item">
                    <div class="header">Production Schedule</div>
                    <div class="menu">
                        <a class="item">生产计划<div class="ui mini left pointing label">还没开始开发呢</div></a>
                        <a class="item">More</a>
                    </div>
                </div>
                <div class="item">
                    <div class="header">Product Management</div>
                    <div class="menu">
                        <a class="item">产品管理<div class="ui mini left pointing label">还没开始开发呢</div></a>
                        <a class="item">More</a>
                    </div>
                </div>
                <div class="item">
                    <div class="header">Telemetry Plan</div>
                    <div class="menu">
                        <a class="item">遥测计划<div class="ui mini left pointing label">还没开始开发呢</div></a>
                        <a class="item">More</a>
                    </div>
                </div>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="item">
                    <div class="header">User List</div>
                    <div class="menu">
                        <a class="item" href="user-list">用户列表<div class="ui mini left pointing teal label">可以用了</div></a>
                        <a class="item">More</a>
                    </div>
                </div></sec:authorize>
            </div>
        </div>
        <div class="app_content">
            <sitemesh:write property='body'/>
        </div>
    </div>
</div>

</body>
</html>
