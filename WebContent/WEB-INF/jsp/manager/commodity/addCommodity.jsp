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
                        <li><a href="#">添加商品</a></li>
                    </ul>
                </div>

                <div class="row">
                    <div class="box col-md-12">
                        <div class="box-inner">
                            <div class="box-header well" data-original-title="">
                                <h2>
                                    <i class="glyphicon glyphicon-edit"></i> 商品信息
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
                                <form:form modelAttribute="commodityForm" action="addCommodity" method="post" enctype="multipart/form-data">
                                	<div class="alert alert-info">${message}<form:errors path="*"></form:errors></div>
                                    <table class="table table-bordered responsive">
                                        <tr>
											<td style="background-color: #f9f9f9;">商品类型</td>
											<td>
												<div class="controls">
													<form:select path="commodityTypeId" itemValue="${commodityForm.commodityTypeId}">
														<form:options id="commodityTypeId" items="${commodityType}" itemLabel="label" itemValue="value" />
													</form:select>
												</div>
											</td>
											<td style="background-color: #f9f9f9;">供应商</td>
											<td>
												<div class="controls">
													<form:select path="supplierId">
														<form:options id="supplierId" items="${supplierList}" itemLabel="label" itemValue="value" />
													</form:select>
												</div>
											</td>
											<td style="background-color: #f9f9f9;">品牌</td>
											<td>
												<div class="controls">
													<form:select path="brandId">
														<form:option value="">等待上一级选择</form:option>
														<form:options id="brandId" />
													</form:select>
												</div>
											</td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">名称<span style="color:red">*</span></td>
											<td><form:input path="commodityName" cssClass="form-control" cssErrorClass="form-control error" value="${commodityForm.commodityName}"/></td>
											<td style="background-color: #f9f9f9;">单品重量<span style="color:red">*</span></td>
											<td><form:input path="weight" cssClass="form-control" cssErrorClass="form-control error" value="${commodityForm.weight}"/></td>
											<td style="background-color: #f9f9f9;">是否礼盒</td>
											<td>
												<form:select path="isGift">
													<form:option value="是">是</form:option>
													<form:option value="否">否</form:option>
												</form:select>
											</td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">规格<span style="color:red">*</span></td>
											<td><form:input path="specifications" cssClass="form-control" cssErrorClass="form-control error" value="${commodityForm.specifications}"/></td>
											<td style="background-color: #f9f9f9;">单位</td>
											<td><form:select path="unit">
													<form:option value="箱">箱</form:option>
													<form:option value="袋">袋</form:option>
													<form:option value="盒">盒</form:option>
													<form:option value="瓶">瓶</form:option>
													<form:option value="饼">饼</form:option>
													<form:option value="桶">桶</form:option>
													<form:option value="个">个</form:option>
												</form:select></td>
											<td style="background-color: #f9f9f9;">基准价格<span style="color:red">*</span></td>
											<td><form:input path="benchmarkPrice" cssClass="form-control" value="${commodityForm.benchmarkPrice}" cssErrorClass="form-control error"/></td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">销售指导价格<span style="color:red">*</span></td>
											<td><form:input path="guidePrice" cssClass="form-control" value="${commodityForm.guidePrice}" cssErrorClass="form-control error"/></td>
											<td style="background-color: #f9f9f9;">终端零售价格<span style="color:red">*</span></td>
											<td><form:input path="retailPrice" cssClass="form-control" value="${commodityForm.retailPrice}" cssErrorClass="form-control error"/></td>
											<td style="background-color: #f9f9f9;">竞争级别</td>
											<td>
												<form:select path="competitionLevel">
													<form:option value="低">低</form:option>
													<form:option value="中">中</form:option>
													<form:option value="高">高</form:option>
												</form:select>
											</td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">商品图片</td>
                                            <td colspan="5"><input type="file" name="file">
                                            <input type="file" name="file1">
                                            <input type="file" name="file2">
                                            <input type="file" name="file3">
                                            <input type="file" name="file4">
                                            <input type="file" name="file5">
                                            <input type="file" name="file6">
                                            
                                            </td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">商品附件(.zip)</td>
                                            <td colspan="5"><input type="file" name="attachments" accept=".zip"></td>
										</tr>
										<tr>
											<td style="background-color: #f9f9f9;">备注</td>
											<td colspan="5"><input name="note" class="form-control" /></td>
										</tr>
                                    </table>
                                    <div class="input-group has-success col-md-12" align="right">
                                        <button type="submit" class="btn btn-success">提 交</button>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="initCommodity"><button type="button" class="btn btn-danger">取 消</button></a>
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
				$("#supplierId").change(function(){
					var pro=$("#supplierId").val();
					$.ajax({
						type: "POST",
						url: "getBrand",
						data: {
							supplierId:pro
						},
						dataType: "json",
						success: function(data){
							$("#brandId").empty();
							$.each(data.brands, function(index, item){
								var myOption = document.createElement("option"); 
								myOption.value = item.value; 
								myOption.text = item.label; 
								$("#brandId").append(myOption);
							});
						}
					});
				});
			});
			
			$(function(){
				var pro=$("#supplierId").val();
				$.ajax({
					type: "POST",
					url: "getBrand",
					data: {
						supplierId:pro
					},
					dataType: "json",
					success: function(data){
						$("#brandId").empty();
						$.each(data.brands, function(index, item){
							var myOption = document.createElement("option"); 
							myOption.value = item.value; 
							myOption.text = item.label; 
							$("#brandId").append(myOption); 
						});
					}
				});
			});
	</script>
</body>
</html>