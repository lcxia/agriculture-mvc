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
<c:if test="${sessionScope.UVO.guestName==null}">
	<meta http-equiv="REFRESH" content="0;url=initGuestLogin" />
</c:if>
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
							<div id="slideshow">
								
							</div>
							<div class="header-para">
								<jsp:include page="/WEB-INF/jsp/mobile/rightBar.jsp"></jsp:include>
							</div>
							<div class="header_bottom_right_images">
								<form:form modelAttribute="alipayForm" action="replayAlipaySubmit"
									method="post">
									<form:hidden path="outTradeNo" value="${alipayForm.outTradeNo}"/>
									<form:hidden path="subject" value="${alipayForm.subject}"/>
									<form:hidden path="body" value="${alipayForm.body}"/>
									<form:hidden path="price" value="${alipayForm.price}"/>
									<form:hidden path="showUrl" value="${alipayForm.showUrl}"/>
									<form:hidden path="receiveName" value="${alipayForm.receiveName}"/>
									<form:hidden path="receiveAddress" value="${alipayForm.receiveAddress}"/>
									<form:hidden path="receiveZip" value="${alipayForm.receiveZip}"/>
									<form:hidden path="receivePhone" value="${alipayForm.receivePhone}"/>
									<form:hidden path="receiveMobile" value="${alipayForm.receiveMobile}"/>
									<div class="about_wrapper">
										<h1>
											该支付信息取自您登录的个人信息，请确认，如果有问题请您修改您的个人信息。
										</h1>
									</div>
									
									<div class="about-top">
										<div class="grid span_2_of_3">
											<h3>客户订单号：${alipayForm.outTradeNo}</h3>
											<p>订单名称：${alipayForm.subject}</p>
											<p>订单描述：${alipayForm.body}</p>
											<p>付款金额：${alipayForm.price}</p>
											<p>商品展示地址：${alipayForm.showUrl}</p>
											<p>收货人姓名：${alipayForm.receiveName}</p>
											<p>收货人地址：${alipayForm.receiveAddress}</p>
											<p>收货人邮编：${alipayForm.receiveZip}</p>
											<p>收货人电话号码：${alipayForm.receivePhone}</p>
											<p>收货人手机号码：${alipayForm.receiveMobile}</p>
										</div>
										<div class="clear"></div>
									</div>
								
									<div class="clear"></div>
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



