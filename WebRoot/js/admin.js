	//删除视频
  	function delVideo(vid){
  		if(confirm("您确认删除么？")){
			location = "VideoServlet?op=delVideo&vid="+vid;
		}
  	}
  	//删除所选
  	function delCheckedVideos(){
  		var ch = document.getElementsByName("video");
		var count = 0;
  		for(var i = 0;i<ch.length;i++){
  			if(ch[i].checked == true){
				count++;
			}
		}
  		if(count == 0){
  			return;
  		}
  		if(confirm("您确认删除选中的电影么？")){
  			var myform = document.getElementById("myform");
  			myform.submit();
  			//通过超链接提交表单方法如下
  			//1、可以使用form的submit();方法
  			//2、先在表单里创建一个提交按钮，并设置display属性为none，使其隐藏
  			//超链接的href写成：javascript：按钮名称.click();即触发按钮点击事件
		}
  	}
  	//点击全选框
  	function check(){
		//复选框全选的值 = boolean
		var chAll = document.getElementById("all").checked;
		//取出数组
		var ch = document.getElementsByName("video");
		for(var i = 0;i<ch.length;i++){
			ch[i].checked = chAll;	
		}
	}
  	//如果所有的电影多选框都勾选了，则全选框选中，反之，不选中
  	function checkBox(){
  		var count = 0;//选中的数量
  		var sum = 0;//总数量
  		//全选框
  		var chAll = document.getElementById("all");
		//电影多选框数组
		var ch = document.getElementsByName("video");
		for(var i = 0;i<ch.length;i++){
			sum++;
			if(ch[i].checked == true){
				count++;
			}
		}
		//如果总数量与选中的数量一致且sum不等于0，则全选框选中
		if((count == sum) && sum != 0){
			chAll.checked = true;
  		}else{
  			chAll.checked = false;
  		}
  	}
  	//搜索---改变当前页数
  	function changePageIndexBySearch(pageIndex){
  		$("#pageIndex").val(pageIndex);
  		$("#searchform").submit();
  	}
  	//判断电影数量是否小于1,如果电影数量小于1，就把删除所选隐藏
  	$(function(){
  		var size = $("[name='video']").size();
  		if(size<1){
  			$("#delCKVideos").hide();
  			$("#empty").show();
  		}else{
  			$("#delCKVideos").show();
  			$("#empty").hide();
  		}
  		//搜索---获得第一页
  		$("#searchBtn").click(function(){
  			$("#pageIndex").val(1);
  	  		$("#searchform").submit();
  		}).mouseover(function(){
  			$(this).css("border-color","red");
  			$(this).css("color","red");
  		}).mouseout(function(){
  			$(this).css("border-color","green");
  			$(this).css("color","black");
  		});
  		$("#videoname").focus(function(){
  			if($(this).val()=="请输入电影名称"){
  				$(this).val("");
  				$(this).css("color","black");
  			}
  		}).blur(function(){
  			if($(this).val()==""){
  				$(this).val("请输入电影名称");
  				$(this).css("color","gray");
  			}
  		}).mouseover(function(){
  			$(this).css("border-color","red");
  		}).mouseout(function(){
  			$(this).css("border-color","green");
  		});
  		$("#searchform select").mouseover(function(){
  			$(this).css("border-color","red");
  		}).mouseout(function(){
  			$(this).css("border-color","green");
  		});
  	});
  	//下拉列表
  	function changeIndex(op){
   		var index = document.getElementById("SelectIndex").value;
   		//查询所有的页面
   		if(op=="all"){
	   		location="VideoServlet?op=showVideoListForAdmin&pageIndex="+index;
   		}else if(op=="search"){
		//通过搜索显示的页面
   			$("#pageIndex").val(index);
  			$("#searchform").submit();
   		}
   } 