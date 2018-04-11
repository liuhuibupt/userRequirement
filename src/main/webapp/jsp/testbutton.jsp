<%--CREATED BY LIUHUI ON 2018/3/20--%>
<%--EDITED BY PANSHENGNAN--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='sec' %>

<c:set var="serverUrl" value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<html>
<head>
    <script>
        function OpenDiv(){
            document.getElementById("more-constraint-conditions").style.display="block";
            document.getElementById("open-more").style.display="none";
        }
        function CloseDiv(){
            document.getElementById("more-constraint-conditions").style.display="none";
            document.getElementById("open-more").style.display="block";
        }
    </script>
    <style>
        #more-constraint-conditions{
            display: none;
            position: absolute;
            width:500px;
            height:500px;
            background-color:gray;
            text-align: center;
        }
        #open-more{
            position: absolute;
            width:100px;
            height:50px;
        }
    </style>

</head>

<body>
<div id="more-constraint-conditions">

    <a href="javascript:CloseDiv();">关闭</a>
</div>
<input type="button" value="+更多约束条件" onclick="javascript:OpenDiv();" id="open-more">
</body>

</html>

