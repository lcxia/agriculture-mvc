<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/manager/include.jsp"%>
<div class="btn-group pull-right">
	<button class="btn btn-default dropdown-toggle"
		data-toggle="dropdown">
		<i class="glyphicon glyphicon-user"></i><span
			class="hidden-sm hidden-xs"> ${sessionScope.UVO.userName}</span> <span class="caret"></span>
	</button>
	<ul class="dropdown-menu">
		<c:if test="${sessionScope.UVO.admin == 'on'}">
		<li><a href="initEditUser">个人信息</a></li>
		</c:if>
		<li class="divider"></li>
		<li><a href="${pageContext.request.contextPath}/init">退出</a></li>
	</ul>
</div>
