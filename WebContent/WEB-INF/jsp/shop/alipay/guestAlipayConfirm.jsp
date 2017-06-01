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
<script src="shop/js/jquery.min.js"></script>
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
								<form:form modelAttribute="alipayForm" action="guestAlipaySubmit"
									method="post">
									<form:hidden path="outTradeNo" value="${alipayForm.outTradeNo}"/>
									<form:hidden path="subject" value="${alipayForm.subject}"/>
									<form:hidden path="body" value="${alipayForm.body}"/>
									<form:hidden path="price" value="${alipayForm.price}"/>
									<form:hidden path="showUrl" value="${alipayForm.showUrl}"/>
									<form:hidden path="commodityId" value="${alipayForm.commodityId}"/>
									<form:hidden path="guestId" value="${alipayForm.guestId}"/>
									<form:hidden path="count" value="${alipayForm.count}"/>
									<div class="about_wrapper">
										<h1>
											您现在是匿名购买该商品，请认真填写以下每项内容以保证商品能够顺利到达您的手中。
											<br/>
											${message}<form:errors path="*"></form:errors>
										</h1>
									</div>
									
									<div class="section group">
										<div class="col span_2_of_c">
											<div class="contact-form">
												<h3>订单信息</h3>
												<div>
													<span><label>商品信息</label></span> <span>${alipayForm.body}</span>
												</div>
												<div>
													<span><label>价格</label></span> <span>${alipayForm.price}</span>
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
									<div class="clear"></div>
									<button class="button"><span>登录支付宝结账</span></button>
								</form:form>
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



