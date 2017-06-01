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
								<form:form modelAttribute="alipayForm" action="distributorAlipaySubmit"
									method="post">
									<form:hidden path="outTradeNo" value="${alipayForm.outTradeNo}"/>
									<form:hidden path="subject" value="${alipayForm.subject}"/>
									<form:hidden path="body" value="${alipayForm.body}"/>
									<form:hidden path="price" value="${alipayForm.price}"/>
									<form:hidden path="showUrl" value="${alipayForm.showUrl}"/>
									<form:hidden path="commodityId" value="${alipayForm.commodityId}"/>
									<form:hidden path="guestId" value="${alipayForm.guestId}"/>
									<form:hidden path="distributorPriceId" value="${alipayForm.distributorPriceId}"/>
									<div class="about_wrapper">
										<h1>
											该页面来自您朋友${alipayForm.distributorName}的推荐，请认真填写以下每项内容以保证商品能够顺利到达您的手中。
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
									<button class="button"><span>登录支付宝结账</span></button>
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



