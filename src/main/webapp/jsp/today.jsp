<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>今日计划</title>
<script>
$(document).ready(function() {

    $('#testPopup').popup({
        hoverable: true,
        delay: {
            show: 100,
            hide: 100
        }
    });

});

</script>
</head>
<body>
<h2 class="ui header">今日计划</h2>
<div class="ui divider"></div>
<div class="ui button" id="testPopup">popup</div>
<div class="ui flowing popup top left transition hidden">
    <div class="ui relaxed divided list">
        <div class="item">
            <i class="large github middle aligned icon"></i>
            <div class="content">
                <a class="header">Semantic-Org/Semantic-UI</a>
                <div class="description">Updated 10 mins ago</div>
            </div>
        </div>
        <div class="item">
            <i class="large github middle aligned icon"></i>
            <div class="content">
                <a class="header">Semantic-Org/Semantic-UI-Docs</a>
                <div class="description">Updated 22 mins ago</div>
            </div>
        </div>
        <div class="item">
            <i class="large github middle aligned icon"></i>
            <div class="content">
                <a class="header">Semantic-Org/Semantic-UI-Meteor</a>
                <div class="description">Updated 34 mins ago</div>
            </div>
        </div>
    </div>
</div>
<table class="ui celled table">
    <thead>
    <tr>
        <th width="10%">标题</th>
        <th>标题</th>
        <th width="10%">标题</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            <div class="ui ribbon label">First</div>
            Cell
        </td>
        <td>

            Cell</td>
        <td>Cell</td>
    </tr>
    <tr>
        <td>
            <div class="ui ribbon label">First</div>Cell</td>
        <td>Cell</td>
        <td>Cell</td>
    </tr>
    <tr>
        <td>Cell</td>
        <td>Cell</td>
        <td>Cell</td>
    </tr>
    </tbody>
    <tfoot>
    <tr>
        <th colspan="3">
            <div class="ui right floated pagination menu">
                <a class="icon item">
                    <i class="left chevron icon"></i>
                </a>
                <a class="item">1</a>
                <a class="item">2</a>
                <a class="item">3</a>
                <a class="item">4</a>
                <a class="icon item">
                    <i class="right chevron icon"></i>
                </a>
            </div>
        </th>
    </tr>
    </tfoot>
</table>
</body>
</html>
