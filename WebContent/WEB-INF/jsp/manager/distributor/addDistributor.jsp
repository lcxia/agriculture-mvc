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
<script src="bower_components/jquery/jquery.js"></script>

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
                        <li><a href="#">添加分销商</a></li>
                    </ul>
                </div>

                <div class="row">
                    <div class="box col-md-12">
                        <div class="box-inner">
                            <div class="box-header well" data-original-title="">
                                <h2>
                                    <i class="glyphicon glyphicon-edit"></i> 分销商信息
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
                                <form:form modelAttribute="distributorForm" action="addDistributor" method="post">
                                	<div class="alert alert-info">${message}<form:errors path="*"></form:errors></div>
                                    <table class="table table-bordered responsive">
                                        <tr>
                                        	<td style="background-color: #f9f9f9;">登录名<span style="color:red">*</td>
											<td><form:input path="distributorId" cssClass="form-control" value="${distributorForm.distributorId}" cssErrorClass="form-control error" /></td>
											<td style="background-color: #f9f9f9;">类型<span style="color:red">*</td>
											<td>
												<div class="controls">
													<form:select path="type">
														<form:option value="一般纳税人公司">一般纳税人公司</form:option>
														<form:option value="小规模公司">小规模公司</form:option>
														<form:option value="个人">个人</form:option>
													</form:select>
												</div>
											</td>
											<td style="background-color: #f9f9f9;">等级</td>
											<td>
												<div class="controls">
													<form:select path="level">
														<form:option value="一级分销商">一级分销商</form:option>
														<form:option value="二级分销商">二级分销商</form:option>
													</form:select>
												</div>
											</td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">名称<span style="color:red">*</td>
											<td><form:input path="distributorName" cssClass="form-control" cssErrorClass="form-control error" /></td>
											<td style="background-color: #f9f9f9;">打印名称<span style="color:red">*</td>
											<td><form:input path="printName" cssClass="form-control" cssErrorClass="form-control error" /></td>
											<td style="background-color: #f9f9f9;">增量百分比</td>
											<td>
												<div class="controls">
													<form:select path="priceIncrement">
														<form:option value="0.01">1%</form:option>
														<form:option value="0.05">5%</form:option>
														<form:option value="0.1">10%</form:option>
														<form:option value="0.15">15%</form:option>
														<form:option value="0.2">20%</form:option>
														<form:option value="0.25">25%</form:option>
														<form:option value="0.3">30%</form:option>
														<form:option value="0.35">35%</form:option>
														<form:option value="0.4">40%</form:option>
														<form:option value="0.45">45%</form:option>
														<form:option value="0.5">50%</form:option>
													</form:select>
												</div>
											</td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">省</td>
											<td>
												<form:select path="provinceId" >
													<form:options id="provinceId" items="${provinceList}" itemLabel="label" itemValue="value"/>
												</form:select>
											</td>
											<td style="background-color: #f9f9f9;">市</td>
											<td>
												<form:select path="cityId">
													<form:option value="">等待上一级选择</form:option>
													<form:options id="cityId"/>
												</form:select>
											</td>
											<td style="background-color: #f9f9f9;">地址<span style="color:red">*</td>
											<td><form:input path="address" cssClass="form-control" cssErrorClass="form-control error"/></td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">联系人<span style="color:red">*</td>
											<td><form:input path="contacts" cssClass="form-control" cssErrorClass="form-control error"/></td>
											<td style="background-color: #f9f9f9;">传真<span style="color:red">*</td>
											<td><form:input path="fax" cssClass="form-control" cssErrorClass="form-control error"/></td>
											<td style="background-color: #f9f9f9;">固定电话<span style="color:red">*</td>
											<td><form:input path="telephone" cssClass="form-control" cssErrorClass="form-control error"/></td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">移动电话<span style="color:red">*</td>
											<td><form:input path="mobile" cssClass="form-control" cssErrorClass="form-control error" /></td>
											<td style="background-color: #f9f9f9;">Email<span style="color:red">*</td>
											<td><form:input path="email" cssClass="form-control" cssErrorClass="form-control error" /></td>
											<td style="background-color: #f9f9f9;">微信<span style="color:red">*</td>
											<td><form:input path="wechat" cssClass="form-control" cssErrorClass="form-control error" /></td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">QQ<span style="color:red">*</td>
											<td><form:input path="qq" cssClass="form-control" cssErrorClass="form-control error" /></td>
											<td style="background-color: #f9f9f9;">支付宝账号<span style="color:red">*</td>
											<td><form:input path="alipay" cssClass="form-control" cssErrorClass="form-control error" /></td>
											<td style="background-color: #f9f9f9;">备注</td>
											<td><input name="note" class="form-control" /></td>
										</tr>
                                    </table>
                                    <div class="input-group has-success col-md-12" align="right">
                                        <button type="submit" class="btn btn-success">提 交</button>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                      <a href="initDistributor"><button type="button" class="btn btn-danger">取 消</button></a>
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
			$(function(){
				$("#provinceId").change(function(){
					var pro=$("#provinceId").val();
					$.ajax({
						type: "POST",
						url: "getCity",
						data: {
							provinceId:pro
						},
						dataType: "json",
						success: function(data){
							$("#cityId").empty();
							$.each(data.citys, function(index, item){
								var myOption = document.createElement("option"); 
								myOption.value = item.value; 
								myOption.text = item.label; 
								$("#cityId").append(myOption); 
								//$("#city").empty();
								//$("#city").append(''+item.label+'');
							});
						}
					});
				});
			});
			
			$(function(){
				var pro=$("#provinceId").val();
				$.ajax({
					type: "POST",
					url: "getCity",
					data: {
						provinceId:pro
					},
					dataType: "json",
					success: function(data){
						$("#cityId").empty();
						$.each(data.citys, function(index, item){
							var myOption = document.createElement("option"); 
							myOption.value = item.value; 
							myOption.text = item.label; 
							$("#cityId").append(myOption); 
						});
					}
				});
			});
	</script>
</body>
</html>