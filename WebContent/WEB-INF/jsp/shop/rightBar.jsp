<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/manager/include.jsp"%>
<div class="categories">
	<div class="list-categories">
		<div class="first-list">
			<div class="div_2">
				<a href="initGoods?type=tiaoliao">调料</a>
			</div>
			<div class="div_img">
				<img src="shop/images/tiaoliao.jpg" alt="Cars" title="Cars"
					width="60" height="39">
			</div>
			<div class="clear"></div>
		</div>
		<div class="first-list">
			<div class="div_2">
				<a href="initGoods?type=huazhuangpin">化妆品</a>
			</div>
			<div class="div_img">
				<img src="shop/images/huazhuangpin.jpg" alt="Cars" title="Cars"
					width="60" height="39">
			</div>
			<div class="clear"></div>
		</div>
		<div class="first-list">
			<div class="div_2">
				<a href="initGoods?type=chongyin">冲饮</a>
			</div>
			<div class="div_img">
				<img src="shop/images/chongyin.jpg" alt="Cars" title="Cars"
					width="60" height="39">
			</div>
			<div class="clear"></div>
		</div>
		<div class="first-list">
			<div class="div_2">
				<a href="initGoods?type=lingshi">零食</a>
			</div>
			<div class="div_img">
				<img src="shop/images/lingshi.jpg" alt="Cars" title="Cars"
					width="60" height="39">
			</div>
			<div class="clear"></div>
		</div>
	</div>
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
	<div class="box-title">
		<h1>
			<span class="title-icon"></span><a href="">友情链接</a>
		</h1>
	</div>
	<div class="section group example">
		<div class="col_1_of_2 span_1_of_2">
			<img src="shop/images/pic21.jpg" alt="" /> <img
				src="shop/images/pic24.jpg" alt="" /> <img
				src="shop/images/pic25.jpg" alt="" /> <img
				src="shop/images/pic27.jpg" alt="" />
		</div>
		<div class="col_1_of_2 span_1_of_2">
			扫描二维码安装安卓客户端
			<img src="shop/images/test.png" alt="" />
		</div>
		<div class="clear"></div>
	</div>
	<div class="clear"></div>
</div>