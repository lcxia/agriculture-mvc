<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/manager/include.jsp"%>
<div class="categories">
	<div class="box">
		<div class="box-heading">
			<h1>
				<a href="initCart">购物车:&nbsp;</a>
			</h1>
		</div>
		<div class="box-content">
			<c:choose>
				<c:when test="${cartList.size() == null ||cartList.size() == 0}">
					您尚未登录，或您尚未购买任何商品。
				</c:when>
				<c:otherwise>
					您的购物车有&nbsp;<a href="initCart"><strong> ${cartList.size()}件商品</strong></a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="clear"></div>
</div>