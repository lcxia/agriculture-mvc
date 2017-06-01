<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/manager/include.jsp"%>
<html>
<head>
<!--
        ===
        This comment should NOT be removed.

        Charisma v2.0.0

        Copyright 2012-2014 Muhammad Usman
        Licensed under the Apache License v2.0
        http://www.apache.org/licenses/LICENSE-2.0

        http://usman.it
        http://twitter.com/halalit_usman
        ===
    -->
<meta charset="utf-8">
<title>农产品管理平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
    content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

<!-- The styles -->
<link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">
<link href="css/datepicker.css" rel="stylesheet">
<link href="css/charisma-app.css" rel="stylesheet">
<link href='bower_components/fullcalendar/dist/fullcalendar.css'
    rel='stylesheet'>
<link href='bower_components/fullcalendar/dist/fullcalendar.print.css'
    rel='stylesheet' media='print'>
<link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
<link href='bower_components/colorbox/example3/colorbox.css'
    rel='stylesheet'>
<link href='bower_components/responsive-tables/responsive-tables.css'
    rel='stylesheet'>
<link
    href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
    rel='stylesheet'>
<link href='css/jquery.noty.css' rel='stylesheet'>
<link href='css/noty_theme_default.css' rel='stylesheet'>
<link href='css/elfinder.min.css' rel='stylesheet'>
<link href='css/elfinder.theme.css' rel='stylesheet'>
<link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='css/uploadify.css' rel='stylesheet'>
<link href='css/animate.min.css' rel='stylesheet'>

<!-- jQuery -->
<script src="bower_components/jquery/jquery.min.js"></script>

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="img/favicon.ico">

</head>

<body>
    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
            <button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="init"> <img
                alt="Charisma Logo" src="img/logo20.png" class="hidden-xs" /> <span>Charisma</span></a>

            <!-- user dropdown starts -->
            <jsp:include page="/WEB-INF/jsp/manager/userBar.jsp"></jsp:include>
            <!-- user dropdown ends -->

            <!-- theme selector starts -->
            <div class="btn-group pull-right theme-container animated tada">
                <button class="btn btn-default dropdown-toggle"
                    data-toggle="dropdown">
                    <i class="glyphicon glyphicon-tint"></i><span
                        class="hidden-sm hidden-xs"> Change Theme / Skin</span> <span
                        class="caret"></span>
                </button>
                <ul class="dropdown-menu" id="themes">
                    <li><a data-value="classic" href="#"><i class="whitespace"></i>
                            Classic</a></li>
                    <li><a data-value="cerulean" href="#"><i
                            class="whitespace"></i> Cerulean</a></li>
                    <li><a data-value="cyborg" href="#"><i class="whitespace"></i>
                            Cyborg</a></li>
                    <li><a data-value="simplex" href="#"><i class="whitespace"></i>
                            Simplex</a></li>
                    <li><a data-value="darkly" href="#"><i class="whitespace"></i>
                            Darkly</a></li>
                    <li><a data-value="lumen" href="#"><i class="whitespace"></i>
                            Lumen</a></li>
                    <li><a data-value="slate" href="#"><i class="whitespace"></i>
                            Slate</a></li>
                    <li><a data-value="spacelab" href="#"><i
                            class="whitespace"></i> Spacelab</a></li>
                    <li><a data-value="united" href="#"><i class="whitespace"></i>
                            United</a></li>
                </ul>
            </div>
            <!-- theme selector ends -->

        </div>
    </div>
    <!-- topbar ends -->
    <div class="ch-container">
        <div class="row">

            <!-- left menu starts -->
            <jsp:include page="/WEB-INF/jsp/manager/menuBar.jsp"></jsp:include>
            <!--/span-->
            <!-- left menu ends -->

            <noscript>
                <div class="alert alert-block col-md-12">
                    <h4 class="alert-heading">Warning!</h4>

                    <p>
                        You need to have <a href="http://en.wikipedia.org/wiki/JavaScript"
                            target="_blank">JavaScript</a> enabled to use this site.
                    </p>
                </div>
            </noscript>

            <div id="content" class="col-lg-10 col-sm-10">
                <!-- content starts -->
                <div>
                    <ul class="breadcrumb">
                        <li><a href="#">首页</a></li>
                        <li><a href="#">个人信息</a></li>
                    </ul>
                </div>

                <div class="row">
                    <div class="box col-md-12">
                        <div class="box-inner">
                            <div class="box-header well" data-original-title="">
                                <h2>
                                    <i class="glyphicon glyphicon-edit"></i> 个人信息
                                </h2>

                                <div class="box-icon">
                                    <a href="#" class="btn btn-setting btn-round btn-default"><i
                                        class="glyphicon glyphicon-cog"></i></a> <a href="#"
                                        class="btn btn-minimize btn-round btn-default"><i
                                        class="glyphicon glyphicon-chevron-up"></i></a> <a href="#"
                                        class="btn btn-close btn-round btn-default"><i
                                        class="glyphicon glyphicon-remove"></i></a>
                                </div>
                            </div>

                            <div class="box-content">
                                <form:form modelAttribute="userForm" action="editUser" method="post">
                                	<div class="alert alert-info">${message}<form:errors path="*"></form:errors></div>
                                    <table class="table table-bordered responsive">
                                        <tr>
											<td style="background-color: #f9f9f9;">用户ID<span style="color:red;">*</span></td>
											<td><form:input path="userId" cssClass="form-control" cssErrorClass="form-control error" value="${userForm.userId}"/></td>
											
											<td style="background-color: #f9f9f9;">用户密码<span style="color:red;">*</span></td>
											<td><form:input path="password" type="password" cssClass="form-control" cssErrorClass="form-control error"/></td>
											<td style="background-color: #f9f9f9;">确认密码<span style="color:red;">*</span></td>
											<!-- <td><input type="password" name="passwordConfirm" class="form-control" id="inputSuccess1" /></td> -->
										    <td><form:input path="passwordConfirm" type="password" cssClass="form-control" cssErrorClass="form-control error"/></td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">用户姓名<span style="color:red;">*</span></td>
											<!-- <td><input name="userName" class="form-control" id="inputSuccess1" value="${userForm.userName}" /></td> -->
											<td><form:input path="userName" cssClass="form-control" cssErrorClass="form-control error" value="${userForm.userName}"/></td>
											<td style="background-color: #f9f9f9;">出生年月<span style="color:red;">*</span></td>
											<!-- <td><input name="birthday" class="form-control" id="birthday" value="${userForm.birthday}" data-date-format="yyyy-mm-dd" /></td> -->
											<td><form:input path="birthday" cssClass="form-control" cssErrorClass="form-control error" value="${userForm.birthday}" data-date-format="yyyy-mm-dd"/></td>
											<td style="background-color: #f9f9f9;">性别<span style="color:red;">*</span></td>
											<td>
												<div class="controls">
													<form:select path="gender" id="selectError" data-rel="chosen">
														<form:option value="未知">&nbsp;&nbsp;&nbsp;&nbsp;未知&nbsp;&nbsp;&nbsp;&nbsp;</form:option>
														<form:option value="男">&nbsp;&nbsp;&nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;</form:option>
														<form:option value="女">&nbsp;&nbsp;&nbsp;&nbsp;女&nbsp;&nbsp;&nbsp;&nbsp;</form:option>
													</form:select>
												</div>
											</td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">住址<span style="color:red;">*</span></td>
											<!-- <td colspan="5"><input name="address" class="form-control"
												id="inputSuccess1" value="${userForm.address}" /></td> -->
												<td colspan="5"><form:input path="address" cssClass="form-control" cssErrorClass="form-control error"/></td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">身份证号<span style="color:red;">*</span></td>
											<!-- <td><input name="idCard" class="form-control" id="inputSuccess1" value="${userForm.idCard}" /></td> -->
											<td><form:input path="idCard" cssClass="form-control" cssErrorClass="form-control error" value="${userForm.idCard}"/></td>
											<td style="background-color: #f9f9f9;">电子邮箱<span style="color:red;">*</span></td>
											<!-- <td><input name="email" class="form-control" id="inputSuccess1" value="${userForm.email}" /></td> -->
											<td><form:input path="email" cssClass="form-control" cssErrorClass="form-control error" value="${userForm.email}"/></td>
											<td style="background-color: #f9f9f9;">电话<span style="color:red;">*</span></td>
											<!-- <td><input name="telephone" class="form-control" id="inputSuccess1" value="${userForm.telephone}" /></td> -->
											<td><form:input path="telephone" cssClass="form-control" cssErrorClass="form-control error" value="${userForm.telephone}" /></td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">所在部门<span style="color:red;">*</span></td>
											<!-- <td><input name="department" class="form-control" id="inputSuccess1" value="${userForm.department}" /></td> -->
											<td><form:input path="department" cssClass="form-control" cssErrorClass="form-control error" value="${userForm.department}"/></td>
											<td style="background-color: #f9f9f9;">职位<span style="color:red;">*</span></td>
											<!-- <td><input name="position" class="form-control" id="inputSuccess1" value="${userForm.position}" /></td> -->
											<td><form:input path="position" cssClass="form-control" cssErrorClass="form-control error" value="${userForm.position}"/></td>
											<td style="background-color: #f9f9f9;">所属公司<span style="color:red;">*</span></td>
											<!-- <td><input name="companyName" class="form-control" id="inputSuccess1" value="${userForm.companyName}" /></td> -->
											<td><form:input path="companyName" cssClass="form-control" cssErrorClass="form-control error" value="${userForm.companyName}"/></td>
										</tr>
                                    </table>
                                    <div class="input-group has-success col-md-12" align="right">
                                        <button type="submit" class="btn btn-success">提 交</button>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                       
                                        <a href="initMenu" class="btn btn-danger">取 消</a>
                                    </div>

                                </form:form>
                            </div>
                        </div>
                    </div>
                    <!--/span-->

                </div>
                <!--/row-->

                <!-- content ends -->
            </div>
            <!--/#content.col-md-0-->
        </div>
        <!--/fluid-row-->

        <!-- Ad, you can remove it -->

        <!-- Ad ends -->

        <hr>

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
            aria-labelledby="myModalLabel" aria-hidden="true">

            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">×</button>
                        <h3>Settings</h3>
                    </div>
                    <div class="modal-body">
                        <p>Here settings can be configured...</p>
                    </div>
                    <div class="modal-footer">
                        <a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
                        <a href="#" class="btn btn-primary" data-dismiss="modal">Save
                            changes</a>
                    </div>
                </div>
            </div>
        </div>

        <footer class="row">
            <p class="col-md-9 col-sm-9 col-xs-12 copyright">
                &copy; <a href="http://usman.it" target="_blank">Muhammad Usman</a>
                2012 - 2014
            </p>

            <p class="col-md-3 col-sm-3 col-xs-12 powered-by">
                Powered by: <a href="http://usman.it/free-responsive-admin-template">Charisma</a>
            </p>
        </footer>

    </div>
    <!--/.fluid-container-->

    <!-- external javascript -->

    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- library for cookie management -->
    <script src="js/jquery.cookie.js"></script>
    <!-- calender plugin -->
    <script src='bower_components/moment/min/moment.min.js'></script>
    <script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
    <!-- data table plugin -->
    <script src='js/jquery.dataTables.min.js'></script>

    <!-- select or dropdown enhancer -->
    <script src="bower_components/chosen/chosen.jquery.min.js"></script>
    <!-- plugin for gallery image view -->
    <script src="bower_components/colorbox/jquery.colorbox-min.js"></script>
    <!-- notification plugin -->
    <script src="js/jquery.noty.js"></script>
    <!-- library for making tables responsive -->
    <script src="bower_components/responsive-tables/responsive-tables.js"></script>
    <!-- tour plugin -->
    <script
        src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
    <!-- star rating plugin -->
    <script src="js/jquery.raty.min.js"></script>
    <!-- for iOS style toggle switch -->
    <script src="js/jquery.iphone.toggle.js"></script>
    <!-- autogrowing textarea plugin -->
    <script src="js/jquery.autogrow-textarea.js"></script>
    <!-- multiple file upload plugin -->
    <script src="js/jquery.uploadify-3.1.min.js"></script>
    <!-- history.js for cross-browser state change on ajax -->
    <script src="js/jquery.history.js"></script>
    <!-- application script for Charisma demo -->
    <script src="js/charisma.js"></script>

	<script src="js/bootstrap-datepicker.js"></script>
	<script type="text/javascript">
			$('#birthday').datepicker();
	</script>
</body>
</html>