<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/manager/include.jsp"%>
            <div class="col-sm-2 col-lg-2">
                <div class="sidebar-nav">
                    <div class="nav-canvas">
                        <div class="nav-sm nav nav-stacked"></div>
                        <c:choose>
							<c:when test="${sessionScope.UVO.admin == 'on'}">
								<c:if test="${sessionScope.UVO.position == '管理员'}">
									<ul class="nav nav-pills nav-stacked main-menu">
										<li class="nav-header">菜单</li>
										<li class="accordion">
				                            <a href="#"><i class="glyphicon glyphicon-plus"></i><span> 日常办公</span></a>
				                            <ul class="nav nav-pills nav-stacked">
				                                <li><a href="#">通知（未）</a></li>
				                                <li><a href="initMessage">留言板</a></li>
				                                <li><a href="initEditUser">修改个人信息</a></li>
				                            </ul>
				                        </li>
				                        <li class="accordion">
				                            <a href="#"><i class="glyphicon glyphicon-plus"></i><span> 基础管理</span></a>
				                            <ul class="nav nav-pills nav-stacked">
				                            	<li><a href="initAddCity">添加城市</a></li>
				                            	<li><a href="initCommodityType">商品类型维护</a></li>
				                                <li><a href="initSupplier">供应商管理</a></li>
				                                <li><a href="initCommodity">产品管理</a></li>
				                                <li><a href="initSpecial">活动管理</a></li>
				                                <li><a href="initStock">库存管理</a></li>
				                                <li><a href="initExpress">快递商管理</a></li>
				                                <li><a href="initExpressList">快递单号管理</a></li>
				                                <li><a href="initDistributor">分销商管理</a></li>
				                            </ul>
				                        </li>
				                        <li class="accordion">
				                            <a href="#"><i class="glyphicon glyphicon-plus"></i><span> 订单管理</span></a>
				                            <ul class="nav nav-pills nav-stacked">
				                                <li><a href="initDelivery">发货</a></li>
				                                <li><a href="initChargeConfirm">充值审批</a></li>
				                                <li><a href="initRebate">分销返点</a></li>
				                                <li><a href="#">价格清单（未）</a></li>
				                                <li><a href="#">快递费计算（未）</a></li>
				                            </ul>
				                        </li>
				                        <li class="accordion">
				                            <a href="#"><i class="glyphicon glyphicon-plus"></i><span> 统计汇总</span></a>
				                            <ul class="nav nav-pills nav-stacked">
				                            	<li><a href="initAlipayReport">支付宝对账单</a></li>
				                                <li><a href="#">提交报表（未）</a></li>
				                                <li><a href="#">查看报表（未）</a></li>
				                            </ul>
				                        </li>
				                        <li class="accordion">
				                            <a href="#"><i class="glyphicon glyphicon-plus"></i><span> 系统管理</span></a>
				                            <ul class="nav nav-pills nav-stacked">
				                                <li><a href="#">组织职员管理（未）</a></li>
				                                <li><a href="#">职位管理（未）</a></li>
				                                <li><a href="#">菜单管理（未）</a></li>
				                                <li><a href="#">参数设置（未）</a></li>
				                                <li><a href="#">日志管理（未）</a></li>
				                                <li><a href="#">二级目录（未）</a></li>
				                            </ul>
				                        </li>
									</ul>
								</c:if>
		                        <c:if test="${sessionScope.UVO.position == '操作员'}">
		                        	<ul class="nav nav-pills nav-stacked main-menu">
										<li class="nav-header">菜单</li>
										<li class="accordion">
				                            <a href="#"><i class="glyphicon glyphicon-plus"></i><span> 日常办公</span></a>
				                            <ul class="nav nav-pills nav-stacked">
				                                <li><a href="#">通知（未）</a></li>
				                                <li><a href="initEditUser">修改个人信息</a></li>
				                            </ul>
				                        </li>
				                        <li class="accordion">
				                            <a href="#"><i class="glyphicon glyphicon-plus"></i><span> 基础管理</span></a>
				                            <ul class="nav nav-pills nav-stacked">
				                            	<li><a href="initAddCity">添加城市</a></li>
				                                <li><a href="initSupplier">供应商管理</a></li>
				                                <li><a href="initCommodity">产品管理</a></li>
				                                <li><a href="initStock">库存管理</a></li>
				                                <li><a href="initExpress">快递商管理</a></li>
				                                <li><a href="initExpressList">快递单号管理</a></li>
				                                <li><a href="initDistributor">分销商管理</a></li>
				                            </ul>
				                        </li>
									</ul>
		                        </c:if>
							</c:when>
							<c:otherwise>
								<ul class="nav nav-pills nav-stacked main-menu">
									<li class="nav-header">菜单</li>
									<li class="accordion">
			                            <a href="#"><i class="glyphicon glyphicon-plus"></i><span> 日常办公</span></a>
			                            <ul class="nav nav-pills nav-stacked">
			                            	<li><a href="initMessage">留言板</a></li>
			                                <li><a href="initEditDistributorPassword">修改个人密码</a></li>
			                            </ul>
			                        </li>
			                        <li class="accordion">
			                            <a href="#"><i class="glyphicon glyphicon-plus"></i><span> 订单管理</span></a>
			                            <ul class="nav nav-pills nav-stacked">
			                                <li><a href="initCharge">充值管理</a></li>
			                                <li><a href="initPlaceOrder">下单</a></li>
			                                <li><a href="initDistributorPriceEdit">价格维护</a></li>
			                                <li><a href="initQrCode">二维码推广</a></li>
			                            </ul>
			                        </li>
								</ul>
							</c:otherwise>
						</c:choose>
                    </div>
                </div>
            </div>
