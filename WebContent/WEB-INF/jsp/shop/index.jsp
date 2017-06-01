<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/manager/include.jsp"%>
<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<title>好农易商城</title>
<link href="shop/css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--slider-->
<script src="shop/js/jquery.min.js"></script>
<script src="shop/js/script.js" type="text/javascript"></script>
<script src="shop/js/superfish.js"></script>
<link rel="shortcut icon" href="shop/images/pic28.jpg">
<link rel="Bookmark" href="shop/images/pic28.jpg">
</head>
<body>
	<div class="header-bg">
		<div class="wrap">
			<div class="h-bg">
				<div class="total">
					<div class="header">
						<div class="box_header_user_menu">
							<ul class="user_menu">
								<jsp:include page="/WEB-INF/jsp/shop/userBar.jsp"></jsp:include>
							</ul>
						</div>
						<div class="clear"></div>
						<div class="header-bot">
							<jsp:include page="/WEB-INF/jsp/shop/logo.jsp"></jsp:include>
						</div>
					</div>
					<div class="menu">
						<jsp:include page="/WEB-INF/jsp/shop/topBar.jsp"></jsp:include>
					</div>
					<div class="banner-top">
						<div class="header-bottom">
							<div class="header_bottom_right_images">
								<div id="slideshow">
									<ul class="slides">
										<li><a href="#"><canvas></canvas>
												<img src="shop/images/banner4.jpg"></a></li>
										<li><a href="#"><canvas></canvas>
												<img src="shop/images/banner2.jpg"></a></li>
										<li><a href="#"><canvas></canvas>
												<img src="shop/images/banner3.jpg"></a></li>
										<li><a href="#"><canvas></canvas>
												<img src="shop/images/banner1.jpg"></a></li>
									</ul>
									<span class="arrow previous"></span> <span class="arrow next"></span>
								</div>
								<div class="content-wrapper">
									<div class="content-top">
										<div class="box_wrapper">
											<h1>商品信息</h1>
										</div>
										<div class="text">
											<c:forEach items="${list}" var="goodsInfo" varStatus="status">
												<c:choose>
													<c:when test="${status.count%3==1}">
														<div class="grid_1_of_3 grid_1_of_3first images_1_of_3">
													</c:when>
													<c:otherwise>
														<div class="grid_1_of_3 images_1_of_3">
													</c:otherwise>
												</c:choose>
													<div class="grid_1">
														<a href="initGoodsDetail?commodityId=${goodsInfo.commodityId}"><img alt="商品详细情况" src="showImage?pictureId=${goodsInfo.pictureId}" style="height:185px;width:330px;"></a>
														<div class="grid_desc">
															<p class="title">${goodsInfo.commodityName}</p>
															<p class="title1">库存:${goodsInfo.stock}</p>
															<div class="price" style="height: 35px;">
																<span class="reducedfrom">￥${goodsInfo.retailPrice}元</span>
																<span class="reducedfrom">每${goodsInfo.unit}${goodsInfo.specifications}</span>
															</div>
															<div class="cart-button">
																<div class="cart">
																	<a href="initGoodsDetail?commodityId=${goodsInfo.commodityId}"><img src="shop/images/cart.png" alt="商品详细情况" /></a>
																</div>
																<div class="clear"></div>
															</div>
														</div>
													</div>
													<div class="clear"></div>
												</div>
											</c:forEach>
											<div class="clear"></div>
										</div>
									</div>
								</div>
							</div>
							<div class="header-para">
								<jsp:include page="/WEB-INF/jsp/shop/rightBar.jsp"></jsp:include>
							</div>
							<div class="clear"></div>
							<div class="footer-bottom">
								<div class="copy">
									<jsp:include page="/WEB-INF/jsp/shop/copyRight.jsp"></jsp:include>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>



