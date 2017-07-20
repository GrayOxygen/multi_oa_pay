BeaconAddContactJsBridge.ready(function() {
  //判断是否关注
  BeaconAddContactJsBridge.invoke('checkAddContactStatus', {}, function(apiResult) {
    if(apiResult.err_code == 0) {
      var status = apiResult.data;
      if(status == 1) {
        alert('已关注');
      } else {
        alert('未关注');
        //跳转到关注页
        BeaconAddContactJsBridge.invoke('jumpAddContact');
      }
    } else {
      alert(apiResult.err_msg)
    }
  });
});