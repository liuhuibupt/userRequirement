<%--
  Created by IntelliJ IDEA.
  User: PANZHENG
  Date: 2018/1/14
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='sec' %>
<html>
<head>
<title>用户信息</title>
<script>
$(document).ready(function () {
    $('.ui.selection.dropdown').dropdown();
    $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
    });

    $('.ui.form').form({
        fields: {
            userName: {
                identifier: 'userName',
                rules: [
                    {
                        type: 'empty',
                        prompt: 'Please enter [User Name]'
                    }
                ]
            },
            displayName: {
                identifier: 'displayName',
                rules: [
                    {
                        type: 'empty',
                        prompt: 'Please enter [Display Name]'
                    }
                ]
            },
            password: {
                identifier: 'password',
                rules: [
                    {
                        type: 'empty',
                        prompt: 'Please enter [Password]'
                    }
                ]
            },
            passwordConfirm: {
                identifier: 'passwordConfirm',
                rules: [
                    {
                        type: 'match[password]',
                        prompt: 'Confirm is different from [password], please check again'
                    }
                ]
            },
            departmentName: {
                identifier: 'departmentName',
                rules: [
                    {
                        type: 'empty',
                        prompt: 'Please select [Department Name]'
                    }
                ]
            },
            role: {
                identifier: 'role',
                rules: [
                    {
                        type: 'empty',
                        prompt: 'Please select [User Role]'
                    }
                ]
            }
        }
    });
});
</script>
</head>
<body>
<h2 class="ui header">用户信息</h2>
<div class="ui divider"></div>

<form class="ui form" method="post" action="saveUser" style="margin-top: 0.5rem">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" name="id" value="${user.id}" />
    <input type="hidden" id="enable" name="enable" placeholder="Is enable" value="${user.enable}">
    <div class="ui error message">
    </div>
    <div class="three fields">
        <c:if test="${user.enable == true}">
            <div class="field">
                <div class="ui blue message">User Enabled = TRUE</div>
            </div>
        </c:if>
        <c:if test="${user.enable == false}">
            <div class="field">
                <div class="ui red message">User Enabled = FALSE</div>
            </div>
        </c:if>
    </div>
    <div class="three fields">
        <div class="field">
            <label>User Name</label>
            <c:if test="${user == null}">
                <input type="text" id="userName" name="userName" placeholder="User Name" value="${user.userName}">
            </c:if>
            <c:if test="${user != null}">
                <input type="text" id="userName" name="userName" placeholder="User Name" value="${user.userName}" readonly>
            </c:if>
        </div>
    </div>
    <div class="three fields">
        <div class="field">
            <label>Display Name</label>
            <input type="text" id="displayName" name="displayName" placeholder="Display Name" value="${user.displayName}">
        </div>
    </div>

    <div class="three fields">
        <div class="field">
            <label>Password</label>
            <input type="password" id="password" name="password" placeholder="Password" value="${user.password}">
        </div>
    </div>

    <div class="three fields">
        <div class="field">
            <label>Password Confirm</label>
            <input type="password" id="passwordConfirm" placeholder="Password Confirm" value="${user.password}">
        </div>
    </div>
    <div class="three fields">
        <div class="<sec:authorize access="not hasRole('ROLE_ADMIN')">disabled</sec:authorize> field">
            <label>Department Name</label>
            <div class="ui fluid dropdown selection" tabindex="0">
                <select id="departmentName" name="departmentName" >
                    <option value=""></option>
                    <option value="市场部" <c:if test="${user.departmentName == '市场部'}">selected</c:if>>ROLE_DEV</option>
                    <option value="数据中心一室" <c:if test="${user.departmentName == '数据中心一室'}">selected</c:if>>数据中心一室</option>
                    <option value="数据中心二室" <c:if test="${user.departmentName == '数据中心二室'}">selected</c:if>>数据中心二室</option>
                    <option value="数据中心三室" <c:if test="${user.departmentName == '数据中心三室'}">selected</c:if>>数据中心三室</option>
                    <option value="遥感大数据室" <c:if test="${user.departmentName == '遥感大数据室'}">selected</c:if>>遥感大数据室</option>
                </select><i class="dropdown icon"></i>
                <div class="default text">Department Name</div>
                <div class="menu transition hidden" tabindex="-1">
                    <div class="item" data-value="市场部">市场部</div>
                    <div class="item" data-value="数据中心一室">数据中心一室</div>
                    <div class="item" data-value="数据中心二室">数据中心二室</div>
                    <div class="item" data-value="数据中心三室">数据中心三室</div>
                    <div class="item" data-value="遥感大数据室">遥感大数据室</div>
                </div>
            </div>
        </div>
    </div>
    <div class="three fields">
        <div class="<sec:authorize access="not hasRole('ROLE_ADMIN')">disabled</sec:authorize> field">
            <label>User Role</label>
            <div class="ui fluid dropdown selection" tabindex="0">
                <select id="role" name="role">
                    <option value=""></option>
                    <option value="ROLE_USER" <c:if test="${user.role == 'ROLE_USER'}">selected</c:if>>ROLE_USER</option>
                    <option value="ROLE_DEV" <c:if test="${user.role == 'ROLE_DEV'}">selected</c:if>>ROLE_DEV</option>
                    <option value="ROLE_ADMIN" <c:if test="${user.role == 'ROLE_ADMIN'}">selected</c:if>>ROLE_ADMIN</option>
                </select><i class="dropdown icon"></i>
                <div class="default text">User Role</div>
                <div class="menu transition hidden" tabindex="-1">
                    <div class="item" data-value="ROLE_USER">ROLE_USER</div>
                    <div class="item" data-value="ROLE_DEV">ROLE_DEV</div>
                    <div class="item" data-value="ROLE_ADMIN">ROLE_ADMIN</div>
                </div>
            </div>
        </div>
    </div>
    <div class="three fields">
    <div class="field">
        <label>Last Request Time</label>
        <input type="text" readonly value="<fmt:formatDate value="${user.lastRequestTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
        <input type="hidden" name="lastRequestTime" value="<fmt:formatDate value="${user.lastRequestTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
    </div>
    </div>
    <div class="three fields">
        <div class="field">
            <label>Cell Number</label>
            <input type="text" id="cellNum" name="cellNum" placeholder="Cell Number" value="${user.cellNum}">
        </div>
    </div>
    <div class="field">
        <div class="sixteen fields">
            <c:if test="${author.id eq user.id}">
                <div class="field">
                    <input class="ui teal submit button" type="submit" value="Save User">
                </div>
            </c:if>
            <c:if test="${author.id != user.id}">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="field">
                    <input class="ui blue submit button" type="submit" value="Save User">
                </div></sec:authorize>
            </c:if>
            <c:if test="${user.enable == false}">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="field">
                        <a class="ui teal submit button" href="enableUser?userId=${user.id}">Enable User</a>
                    </div></sec:authorize>
            </c:if>
            <c:if test="${user.enable == true}">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="field">
                        <a class="ui teal submit button" href="disableUser?userId=${user.id}">Enable User</a>
                    </div></sec:authorize>
            </c:if>
        </div>
    </div>
</form>
</body>
</html>
