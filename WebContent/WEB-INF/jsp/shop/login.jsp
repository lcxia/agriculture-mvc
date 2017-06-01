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
								<div class="about_wrapper">
									<h1>${message}</h1>
								</div>
								<div class="section group">
									<div class="col span_2_of_c">
										<div class="contact-form">
											<h3>登录信息</h3>
											<form method="post" action="guestLogin" method="post">
												<div>
													<span><label>用户名</label></span> <span><input
														name="guestId" type="text" ></span>
												</div>
												<div>
													<span><label>密码</label></span> <span><input
														name="password" type="password" ></span>
												</div>
												<div>
													<span><input type="submit" value="提交"></span>
												</div>
											</form>
										</div>
									</div>
									<div class="clear"></div>
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



