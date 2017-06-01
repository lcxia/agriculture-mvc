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
								<form:form modelAttribute="cartForm" action="alipayConfirm"
									method="post">
									<div class="about_wrapper">
										<h1>
											购物车信息
										</h1>
									</div>
									<c:forEach var="cartsInfo" items="${cartList}" varStatus="sts">
										<div class="about-top">
											<div class="grid images_3_of_1">
												<img alt="购物车详细情况" src="showImage?pictureId=${cartsInfo.pictureId}" style="height:185px;width:330px;" />
											</div>
											<div class="grid span_2_of_3">
												<h3>${cartsInfo.commodityName}</h3>
												<p>品牌：${cartsInfo.brandName}</p>
												<p>经销商：${cartsInfo.supplierName}</p>
												<p>规格：每${cartsInfo.unit}${goodsForm.specifications}</p>
												<p>零售价：${cartsInfo.retailPrice}元</p>
												<p>购买日期：${cartsInfo.updateTime}</p>
												<p>
													${cartsInfo.count}件
													<a class="button" href="delCart?cartId=${cartsInfo.cartId}&count=${cartsInfo.count}&commodityId=${cartsInfo.commodityId}"><span>删除</span></a>
												</p>
											</div>
											<div class="clear"></div>
										</div>
									</c:forEach>
									<div class="clear"></div>
									<c:if test="${cartList.size() != null && cartList.size() != 0 }">
										<button class="button"><span>结帐</span></button>
									</c:if>
								</form:form>
								<div class="clear"></div>
								<div class="about_wrapper">
									<h1>
										购买历史信息
									</h1>
								</div>
								<c:forEach var="buyedInfo" items="${list}" varStatus="status">
									<div class="about-top">
										<div class="grid">
											<h3>商户订单号:${buyedInfo.outTradeNo}</h3>
											<p>订单名称：${buyedInfo.subject}</p>
											<p>付款金额：${buyedInfo.price}</p>
											<p>订单描述：${buyedInfo.body}</p>
											<p>收货人姓名：${buyedInfo.receiveName}</p>
											<p>收货人地址：${buyedInfo.receiveAddress}</p>
											<p>收货人邮编：${buyedInfo.receiveZip}</p>
											<p>收货人电话号码：${buyedInfo.receivePhone}</p>
											<p>收货人手机号码：${buyedInfo.receiveMobile}</p>
											<p>购买日期：${buyedInfo.updateTime}</p>
											<p>
												如果该单已经付过款则重新付款会在支付宝页面提示失败，请不必担心重复付款的问题。
												<a class="button" href="replayAlipay?outTradeNo=${buyedInfo.outTradeNo}"><span>重新付款</span></a>
											</p>
										</div>
										<div class="clear"></div>
									</div>
								</c:forEach>
								<div class="clear"></div>
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



