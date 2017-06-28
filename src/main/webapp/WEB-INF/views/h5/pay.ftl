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
    <script type="text/javascript">
        wx.config({
            debug: false,
            appId: '${(wx.appId)!}',
            timestamp: '${(wx.timestamp)!}',
            nonceStr: '${(wx.nonceStr)!}',
            signature: '${(wx.signature)!}',
            jsApiList: [
                'checkJsApi',
                'onMenuShareTimeline',
                'onMenuShareAppMessage',
                'onMenuShareQQ',
                'onMenuShareWeibo',
                'chooseWXPay'
            ]
        });
        function callPay(data) {
            wx.chooseWXPay({
                timestamp: data.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                nonceStr: data.nonceStr, // 支付签名随机串，不长于 32 位
                package: data.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
                signType: data.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                paySign: data.paySign, // 支付签名
                success: function (res) {
                alert("支付成功！")
                    // 支付成功后的回调函数
                    if (res.errMsg == "chooseWXPay:ok") {
                        //支付成功
                        //根据卡券是否投放决定是否跳转卡券页面
					    if("${isDispatch?string('true','false')}"=="true"){
					    	window.location.href = "${ctx}/wx/card/getCardPage?oaId=${(oa.id)!}&cardId=${(cardId)!}";
					    }
                        
                    } else {
                        alert('支付失败！' + res.errMsg);
                    }
                },
                cancel: function (res) {
                    alert('支付取消');
                }
            });
        }
    </script>


    <#--<script type="text/javascript">-->
        <#--function callPay(data) {-->
            <#--WeixinJSBridge.invoke('getBrandWCPayRequest', {-->
                        <#--"appId": data.appId,-->
                        <#--"timeStamp": data.timeStamp,-->
                        <#--"nonceStr": data.nonceStr,-->
                        <#--"package": data.package,-->
                        <#--"signType": data.signType,-->
                        <#--"paySign": data.paySign-->
                    <#--},-->
                    <#--function (res) {-->
                        <#--alert(res.err_msg);-->
                        <#--//wxPayCallBack('031000060499', data)-->
                    <#--}-->
            <#--);-->
        <#--}-->
    <#--</script>-->
    <style type="text/css">
        html,
        body {
            height: 100%;
            background-color: #efefef;
        }

        .pay {
            overflow: hidden;
            width: 85%;
            height: 4rem;
            padding-right: 5%;
            margin: 0 auto;
            line-height: 4rem;
            border: 1px solid #a8a7ac;
            border-radius: 5px;
            background-color: #fff;
        }

        .pay em {
            float: left;
            width: 20%;
            text-align: center;
            color: #6b6b6b;
            font-size: 1.13rem;
        }

        .pay strong {
            float: right;
            text-align: right;
            font-size: 1.75rem;
            font-weight: bold;
        }

        .pay span {
            overflow: hidden;
            max-width: 70%;
            float: right;
            text-indent: .1em;
            text-align: right;
            color: #31343b;
            font-size: 1.75rem;
            font-weight: bold;
        }

        .banner {
            position: relative;
            margin: 3rem auto;
            width: 90%;
        }

        .banner em {
            position: absolute;
            top: -12px;
            left: 20%;
            display: block;
            width: 60%;
            line-height: 2rem;
            text-align: center;
            margin: 0 auto;
            font-size: 1rem;
            z-index: 20;
            background-color: #efefef;
        }

        .banner span {
            position: absolute;
            left: 0;
            top: 50%;
            width: 100%;
            height: 1px;
            background-color: #c6c6c6;
            z-index: 10;
            color: #c6c6c6;
            letter-spacing: 1px;
        }

        .keyboard {
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #fff;
        }

        .keyboard span {
            overflow: hidden;
            float: left;
            width: 25%;
            height: 5rem;
            line-height:5rem;
            font-size: 1.5rem;
            text-align: center;
            background-color: #fff;
            box-shadow: 1px 1px 0 0 #c6c6c6 inset;
        }

        #backspace {
            background: url("${app_static}/h5/pay/images/backspace.jpg") center center no-repeat;
            background-size: 50%;
        }

        #payment {
            float: right;
            width: 15%;
            height: 5rem;
            line-height: 2rem;
            padding: 5rem 5%;
            /*background-color: #ff9b21;*/
            background-color: #ccc;
            color: #fff;
            font-size: 1.5rem;
        }

        #zero {
            width: 50%;
        }

        #point {
            font-weight: bold;
        }

        .wrapper-container {
            width: 100%;
            text-align: center;
            padding:2em 0;
        }

        .wrapper-container .img-container {
            display: inline-block;
            height: 4rem;
            width: 4rem;

        }

        .wrapper-container .img-container img {
            width: 4rem;
            height: 4rem;
            display: block;
            border:none;
            -webkit-border-radius:50%;
            -moz-border-radius:50%;
            border-radius: 50%;
        }

        .wrapper-container .shop-title {
            display: inline-block;
            height: 4rem;
            padding: 0 10% 0 5%;
            line-height: 2.1rem;
            text-align: left;
        }

        .wrapper-container .shop-title h1 {
            overflow: hidden;
            color: #6b6b6b;
            height: 2rem;
            letter-spacing: 1px;
            white-space: nowrap;
            text-overflow: ellipsis;
        }

        .wrapper-container .shop-title h2 {
            overflow: hidden;
            color: #c6c6c6;
            height: 2rem;
            letter-spacing: 1px;
        }
    </style>
</head>
<body>
<div class="wrap">
    <div class="wrapper-container">
        <div class="img-container">
        <!--
            <img src="${(shop.company.logo)!}" alt=""/>
        -->
        
        <img src="http://og3nodshf.bkt.clouddn.com/6f097cb4gw1dtm3unkv1aj.jpg" alt=""/>
        </div>
        <div class="shop-title">
            <h1>${(shop.name)!}</h1>
            <h2>王辉阳开发</h2>
        </div>
    </div>
    <div class="pay clearfix">
        <em>金额</em>
        <span id="money"></span>
        <strong id="symbol">￥</strong>
    </div>
    <div class="banner"><em>王辉阳 | 到店支付so easy</em><span></span></div>
    <div class="keyboard clearfix">
        <span id='first'>1</span>
        <span id='second'>2</span>
        <span id='third'>3</span>
        <span id='backspace'></span>
        <span id='four'>4</span>
        <span id='five'>5</span>
        <span id='six'>6</span>
        <span id='payment'>确认支付</span>
        <span id='seven'>7</span>
        <span id='eight'>8</span>
        <span id='nine'>9</span>
        <span id='zero'>0</span>
        <span id='point'>.</span>
    </div>
</div>
<script type="text/javascript">
    // 初始化
    function initial(){
        howMuch = $('#money');
        keyboardBtnList = $(".keyboard>span");
        // 将按键集委托到body上
        $("body").on("touchstart",keyboardBtnList,keyboardBtnList_touchstart);
        $("body").on("touchend",keyboardBtnList,keyboardBtnList_touchend);
    }
    function keyboardBtnList_touchstart(){
        $(this).css("background-color","#ccc");
    }
    // 按钮事件
    function keyboardBtnList_touchend(){
        // 如果是确认支付按钮
        if($(this).attr("id") == "payment"){
            confirmPay_tap();
        }else if($(this).attr("id") =="backspace"){
            $(this).css("background-color","#fff");
            backspace_tap();
        }else{
            $(this).css("background-color","#fff");
            number_tap($(this));
        }
    }
    // 数字按下事件
    function number_tap(self){
        // 要求1、如果是初始单击了“0”，“.”则显示“0.”
        // 要求2、输入数字往后增加值
        // 要求3、小数点后保留两位数字
        var currentMoney = howMuch.text();
        if(currentMoney=="" && (self.attr("id") == "zero" || self.attr("id") == "point")){
            howMuch.text("0.");
        }else if(currentMoney == "" && self.attr("id") != "zero" && self.attr("id") != "point"){
            howMuch.text(currentMoney+self.text());
        }else {
            if(currentMoney.indexOf(".")>0){
                if(self.attr("id") == "point"){
                    return ;
                }else{
                    if(currentMoney.split(".")[1].toString().length>1){
                        howMuch.text(currentMoney);
                    }else{
                        howMuch.text(currentMoney+self.text());
                    }
                }

            }else{
                howMuch.text(currentMoney+self.text());
            }
        }
        payMentBg();

    }

    // 启用确定支付按钮
    function payMentBg(){
        if(howMuch.text()!="" && howMuch.text() != "0."){
            $("#payment").css("background-color","#ff9b21");
        }else{
            $("#payment").css("background-color","#ccc");
        }
    }
    // 退格按钮事件
    function backspace_tap(){
        var currentMoney = howMuch.text();
        if(currentMoney =="" || currentMoney.length == 0){
            return;
        }else{
            currentMoney = currentMoney.substring(0,currentMoney.length-1);
            howMuch.text(currentMoney);
            payMentBg();
        }
    }
    // 显示页面
    function show() {
        var money = howMuch.html();
        var a = (money.match(/./g) || []).length;
        if (a >= 14) {
            howMuch.css({
                fontSize: '1rem'
            });
            $('#symbol').css({
                fontSize: '1rem'
            });
        }
        if (a <= 15) {
            howMuch.css({
                fontSize: '1.75rem'
            });
            $('#symbol').css({
                fontSize: '1.75rem'
            });
        }
    }
    // 确认支付按钮
    function confirmPay_tap(){
        //校验支付金额范围
        var money = ($("#money").text() * 100).toFixed(0);
        if(money<=0){
            alert("不可小于最小金额 : 1分");
            return ;
        }
        $.ajax({
            url: '${ctx}/wx/genOrder.json',
            type: 'post',
            dataType: 'json',
            data: {
                'shopId': '${(shop.id)!}',
                'amount': money,
                'cashierId':'${(cashierId)!}'
            },
            success: function (data) {
                //onBridgeReady(data);
                callPay(data);
            },
            error: function (e) {
                alert('网络连接异常，请稍后重试');
            }
        });
    }
    window.addEventListener("load",initial,false);
</script>


</body>
</html>