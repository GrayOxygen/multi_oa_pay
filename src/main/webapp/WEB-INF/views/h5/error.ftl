<!DOCTYPE html>
<html>
<head>
  <title>出错了</title>
  <meta charset="utf-8">
  <meta name="author" content="error">
  <meta name="keywords" content="error">
  <meta name="description" content="error">
  <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="format-detection" content="telephone=no">
  <style>
    html, body, div {
      padding: 0;
      margin: 0;
    }

    #wrap {
      width: 100%;
    }

    .bg img {
      width: 100%;
    }

    .error-msg {
      position: absolute;
      z-index: 100;
      top: 15%;
      width: 100%;
      text-align: center;
      font-weight: bolder;
      font-size: 210%;
      color: #fff;
    }

  </style>
</head>
<body>
<div id="wrap">
  <div class="bg">
    <img src="${app_static}/h5/images/one_key_sub_bg.jpg">
  </div>
  <div class="error-msg">
    <p class="msg">
	${errMsg!}
    </p>
  <#--<img src="${app_static}/h5/images/outdate_error.png" style="width: 80%">-->
  </div>
</div>
</body>
</html>