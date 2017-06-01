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
							<div id="slideshow">
								
							</div>
							<div class="header-para">
								<jsp:include page="/WEB-INF/jsp/mobile/rightBar.jsp"></jsp:include>
							</div>
							<div class="header_bottom_right_images">
								<div class="about_wrapper">
									<h1>
										您已成功支付，请等待商家送货。
									</h1>
								</div>
								<div class="clear"></div>
								<a class="button" href="initGoods?type=liangshi"><span>回到首页</span></a>
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



