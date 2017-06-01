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
<c:if test="${sessionScope.UVO.guestName==null}">
	<meta http-equiv="REFRESH" content="0;url=initGuestLogin">
</c:if>
<script src="shop/js/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#submitAlipay").click(function(){
		alipayForm.target = "newWindow";
		var win = window.open("about:blank", "newWindow");
		win.focus();
		alipayForm.submit();
		window.location.href="initGoods?type=liangshi";
	});
});
</script>
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
								<form:form modelAttribute="alipayForm" action="alipaySubmit"
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
									<input type="button" id="submitAlipay" class="button" value="登录支付宝结账" />
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



