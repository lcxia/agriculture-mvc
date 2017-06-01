<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/manager/include.jsp"%>
<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN"
"http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html>
<head>
<title>好农易商城</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="mobile/css/style.css" type="text/css"
	media="all" />
</head>
<body>
	<div class="wrap">
		<div class="header-bg">
			<div class="h-bg">
				<div class="total">
					<div class="header">
						<div class="box_header_user_menu">
							<jsp:include page="/WEB-INF/jsp/mobile/userBar.jsp"></jsp:include>
						</div>
						<div class="clear"></div>
					</div>
					<div class="header-bot">
						<jsp:include page="/WEB-INF/jsp/mobile/logo.jsp"></jsp:include>
						<nav class="clearfix"> <a href="#Menu">Menu<img
							class="menu" src="mobile/images/nav-icon.png" title="Menu" /></a>
						</nav>
					</div>
					<div class="header-bottom">
						<div class="banner-top">
							<div class="header_bottom_right_images">
								<form:form modelAttribute="specialForm" action="specialAlipaySubmit"
									method="post">
									<form:hidden path="outTradeNo" value="${specialForm.outTradeNo}"/>
									<form:hidden path="subject" value="${specialForm.subject}"/>
									<form:hidden path="body" value="${specialForm.body}"/>
									<form:hidden path="price" value="${specialForm.price}"/>
									<form:hidden path="showUrl" value="${specialForm.showUrl}"/>
									<form:hidden path="commodityId" value="${specialForm.commodityId}"/>
									<div class="about_wrapper">
										<h1>
											这是一个促销商品的订单，该商品只有一件，且每被转发一次就会降价0.5元，随时会被别人拍走，请认真填写以下每项内容以保证商品能够顺利到达您的手中。
											<br/>
											${message}<form:errors path="*"></form:errors>
										</h1>
									</div>
									
									<div class="section group">
										<div class="col span_2_of_c">
											<div class="contact-form">
												<h3>订单信息</h3>
												<div>
													<span><label>商品信息</label></span> <span>${specialForm.body}</span>
												</div>
												<div>
													<span><label>价格</label></span> <span>${specialForm.price}</span>
												</div>
												<div>
													<span><label>收货人姓名<span style="display:inline;color:red;">*</span></label></span> <span><input
														name="receiveName" type="text" /></span>
												</div>
												<div>
													<span><label>收货人地址<span style="display:inline;color:red;">*</span></label></span> <span><input
														name="receiveAddress" type="text" /></span>
												</div>
												<div>
													<span><label>收货人邮编<span style="display:inline;color:red;">*</span></label></span> <span><input
														name="receiveZip" type="text" /></span>
												</div>
												<div>
													<span><label>收货人电话号码<span style="display:inline;color:red;">*</span></label></span> <span><input
														name="receivePhone" type="text" /></span>
												</div>
												<div>
													<span><label>收货人手机号码<span style="display:inline;color:red;">*</span></label></span> <span><input
														name="receiveMobile" type="text" /></span>
												</div>
											</div>
										</div>
										<div class="clear"></div>
									</div>
									<c:if test="${specialForm.stock > 0}">
										<button class="button"><span>生成支付宝订单</span></button>
									</c:if>
									
								</form:form>
							</div>
							<div class="clear"></div>
							<div class="navgation-links">
								<a name="Menu"> </a>
								<jsp:include page="/WEB-INF/jsp/mobile/topBar.jsp"></jsp:include>
							</div>
							<div class="footer-bottom">
								<div class="copy">
									<jsp:include page="/WEB-INF/jsp/mobile/copyRight.jsp"></jsp:include>
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



