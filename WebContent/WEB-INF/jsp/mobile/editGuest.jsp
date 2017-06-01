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
								<form:form modelAttribute="guestForm" action="editGuest"
									method="post">
									<div class="about_wrapper">
										<h1>
											${message}<form:errors path="*"></form:errors>
										</h1>
									</div>
									<div class="section group">
										<div class="col span_2_of_c">
											<div class="contact-form">
												<h3>修改客户信息</h3>
												<div>
													<span><label>用户名<span style="display:inline;color:red;">*</span></label></span> <span><input
														name="guestId" type="text" value="${guestForm.guestId}" /></span>
												</div>
												<div>
													<span><label>密码<span style="display:inline;color:red;">*</span></label></span> <span><input
														name="password" type="password" /></span>
												</div>
												<div>
													<span><label>确认密码</label></span> <span><input
														name="passwordConfirm" type="password" /></span>
												</div>
												<div>
													<span><label>姓名<span style="display:inline;color:red;">*</span></label></span> <span><input
														name="guestName" type="text" value="${guestForm.guestName}" /></span>
												</div>
												<div>
													<span><label>性别</label></span> <span><input
														name="gender" type="text" value="${guestForm.gender}" /></span>
												</div>
												<div>
													<span><label>收货地址<span style="display:inline;color:red;">*</span></label></span> <span><input
														name="address" type="text" value="${guestForm.address}" /></span>
												</div>
												<div>
													<span><label>电话号码<span style="display:inline;color:red;">*</span></label></span> <span><input
														name="mobile" type="text" value="${guestForm.mobile}" /></span>
												</div>
												<div>
													<span><label>Email</label></span> <span><input
														name="email" type="text" value="${guestForm.email}" /></span>
												</div>
												<div>
													<span><label>QQ</label></span> <span><input
														name="qq" type="text" value="${guestForm.qq}" /></span>
												</div>
												<div>
													<span><label>座机号码</label></span> <span><input
														name="phone" type="text" value="${guestForm.phone}" /></span>
												</div>
												<div>
													<span><label>邮政编码</label></span> <span><input
														name="zip" type="text" value="${guestForm.zip}" /></span>
												</div>
												<div>
													<span><input type="submit" value="提交" /></span>
												</div>
											</div>
										</div>
										<div class="clear"></div>
									</div>
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



