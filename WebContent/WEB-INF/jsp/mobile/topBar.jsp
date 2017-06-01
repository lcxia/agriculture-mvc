<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/manager/include.jsp"%>

	<ul>
		<li <c:if test="${goodsForm.type == '粮食'}">class="active"</c:if>><a href="initGoods?type=liangshi">粮食</a></li>
		<li <c:if test="${goodsForm.type == '调料'}">class="active"</c:if>><a href="initGoods?type=tiaoliao">调料</a></li>
		<li <c:if test="${goodsForm.type == '化妆品'}">class="active"</c:if>><a href="initGoods?type=huazhuangpin">化妆品</a></li>
		<li <c:if test="${goodsForm.type == '冲饮'}">class="active"</c:if>><a href="initGoods?type=chongyin">冲饮</a></li>
		<li <c:if test="${goodsForm.type == '零食'}">class="active"</c:if>><a href="initGoods?type=lingshi">零食</a></li>
		<li <c:if test="${goodsForm.type == '茗茶'}">class="active"</c:if>><a href="initGoods?type=mingcha">茗茶</a></li>
	</ul>