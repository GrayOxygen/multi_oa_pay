<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <title>pay</title>
    <link rel="stylesheet" type="text/css" href="${app_static}/h5/pay/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${app_static}/h5/pay/css/weui.min.css"/>
    <script src="${app_static}/h5/pay/js/zepto.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${app_static}/h5/pay/js/touch.js" type="text/javascript" charset="utf-8"></script>
    <script src="${app_static}/h5/pay/js/fx.js" type="text/javascript" charset="utf-8"></script>
    <script src="${app_static}/h5/pay/js/fx_methods.js" type="text/javascript" charset="utf-8"></script>
    <script src="${app_static}/h5/pay/js/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
    <style type="text/css">
        html,
        body {
            height: 100%;
            background-color: #efefef;
			background-image:url('${app_static}/admin/images/bg.png');
			font-family: 'Oleo Script', cursive;
        }
        
        .lg-container{
			width:275px;
			margin:100px auto;
			padding:20px 40px;
			border:1px solid #f4f4f4;
			background:rgba(255,255,255,.5);
			-webkit-border-radius:10px;
			-moz-border-radius:10px;
		
			-webkit-box-shadow: 0 0 2px #aaa;
			-moz-box-shadow: 0 0 2px #aaa;
		}
		.lg-container h1{
			font-size:40px;
			text-align:center;
		}
		#lg-form > div {
			margin:10px 5px;
			padding:5px 0;
			
		}
		#lg-form label{
			display: none;
			font-size: 20px;
			line-height: 25px;
		}
		#lg-form div:nth-child(3) {
			text-align:center;
		}
		#lg-form button:hover{
			background:#f4f4f4;
		}
		#message{width:100%;text-align:center}
		.success {
			color: green;
		}
		.error {
			color: red;
		}

    </style>
</head>
<body>

	<div class="lg-container">
		<#if certResult==true>
        	<h1>授权成功</h1>
        </#if>
 		<#if certResult==false>
 			<h1>授权失败</h1>
	    </#if>
		<div  id="lg-form" name="lg-form"  >
			<div>
				<#if certResult==true>
					 <span class="success"  >恭喜您，【${(cashier.name)!}】,您已获取门店【${(cashier.shop.name)!}】的收银员身份授权!</span>
		        </#if>
				<#if certResult==false>
					 <span class="error" >请检查所扫二维码是否为指定的凭证授权二维码!</span>
		        </#if>
			</div>
		</div>
	</div>
</body>
</html>