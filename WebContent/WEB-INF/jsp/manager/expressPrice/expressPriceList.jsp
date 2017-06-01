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
                        <li><a href="#">快递商列表</a></li>
                        <li><a href="#">快递商所属价格列表</a></li>
                    </ul>
                </div>

				<div class="row">
                    <div class="box col-md-12">
                        <div class="box-inner">
                            <div class="box-header well" data-original-title="">
                                <h2>
                                    <i class="glyphicon glyphicon-edit"></i> 快递商信息
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
                                <table class="table table-bordered responsive">
                              		<tr>
										<td style="background-color: #f9f9f9;">名称</td>
										<td>${expressForm.expressName}</td>
										<td style="background-color: #f9f9f9;">联系人</td>
										<td>${expressForm.contacts}</td>
										<td style="background-color: #f9f9f9;">手机</td>
										<td>${expressForm.mobile}</td>
									</tr>
									<tr>
										<td style="background-color: #f9f9f9;">传真</td>
										<td>${expressForm.fax}</td>
										<td style="background-color: #f9f9f9;">固定电话</td>
										<td>${expressForm.telephone}</td>
										<td style="background-color: #f9f9f9;">状态</td>
										<td>${expressForm.status}</td>
									</tr>
									<tr>
										<td style="background-color: #f9f9f9;">地址</td>
										<td colspan="5">${expressForm.address}</td>
									</tr>
									<tr>
										<td style="background-color: #f9f9f9;">备注</td>
										<td colspan="5">${expressForm.note}</td>
									</tr>
                            	</table>
                            </div>
                        </div>
                    </div>
                    <!--/span-->

                </div>

                <div class="row">
                    <div class="box col-md-12">
                        <div class="box-inner">
                            <div class="box-header well" data-original-title="">
                                <h2>
                                    <i class="glyphicon glyphicon-edit"></i> 该快递商各地价格列表
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
                                <table
									class="table table-striped table-bordered bootstrap-datatable datatable responsive">
									<thead>
										<tr>
											<th>省</th>
											<th>分隔重量g</th>
											<th>首重费（元）</th>
											<th>续重费（元）</th>
											<th>状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="expressPricesInfo" items="${list}"
											varStatus="sts">
											<tr>
												<td>${expressPricesInfo.provinceName}</td>
												<td>${expressPricesInfo.separateWeight}</td>
												<td>${expressPricesInfo.firstHeavyPrice}</td>
												<td>${expressPricesInfo.continuedHeavyPrice}</td>
												<td>${expressPricesInfo.status}</td>
												<td class="center"><a class="btn btn-info" href="initEditExpressPrice?expressPriceId=${expressPricesInfo.expressPriceId}"> <i
														class="glyphicon glyphicon-edit icon-white"></i> 编辑
												</a> <a class="btn btn-danger" onclick="showDialog('delExpressPrice?expressId=${expressPricesInfo.expressId}&expressPriceId=${expressPricesInfo.expressPriceId}')" href="#" data-toggle="modal" data-target="#myModal"> <i
														class="glyphicon glyphicon-trash icon-white"></i> 删除
												</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="input-group has-success col-md-12" align="right">
									<table>
										<tr>
											<td colspan="11" align="right">
												<a class="btn btn-success" href="initAddExpressPrice?expressId=${expressForm.expressId}">
													<i class="glyphicon glyphicon-zoom-in icon-white"></i> 追加
												</a>
												&nbsp;&nbsp;&nbsp;&nbsp;
	                                       		<a class="btn btn-danger" href="initExpress">
													<i class="glyphicon glyphicon-trash icon-white"></i> 取 消
												</a>
											</td>
										</tr>
									</table>
								</div>
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
						<h3>确认删除</h3>
					</div>
					<div class="modal-body">
						<p>该内容删除后不可恢复，请确认是否删除！</p>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
						<a id="modalLink" href="delCar" class="btn btn-primary">删除</a>
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
		function showDialog(url) {
			$("#modalLink").attr("href", url);
		}
	</script>
</body>
</html>