//删除一个记录
  	function delNote(nid){
  		if(confirm("您确认删除么？")){
			location = "VideoNoteServlet?op=delVideoNote&noteid="+nid;
		}
  	}
  	//清空记录
  	function delAllNotes(){
  		var ch = document.getElementsByName("note");
  		if(ch.length<1){
  			return;
  		}
  		if(confirm("您确认清空观看记录么？")){
  			location ="VideoNoteServlet?op=delAllVideoNote";
  		}
  	}
  //删除所选
  	function delCheckedNotes(){
  		var ch = document.getElementsByName("note");
		var count = 0;
  		for(var i = 0;i<ch.length;i++){
  			if(ch[i].checked == true){
				count++;
			}
		}
  		if(count == 0){
  			return;
  		}
  		if(confirm("您确认删除选中的观看记录么？")){
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
		var ch = document.getElementsByName("note");
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
		var ch = document.getElementsByName("note");
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
  //判断电影数量是否小于1,如果电影数量小于1，就把删除所选隐藏
  	$(function(){
  		var size = $("[name='note']").size();
  		var path = location.href;//获得当前地址
  		var isFalst = true;
  		//是否是当前第一次打开这个页面
  		if(path.indexOf("pageIndex") != -1){
  			isFalst = false; 
  		}
  		//如果大于10
  		if(size>10 && isFalst){
  			$("#open").show();
  			$("#ck_td").show();
  			$("#clearAll_td").show();
  			$("#pageZone").hide();
  			$(".movie_ul:gt(9)").hide();
  			$("#empty").hide();
  		}else if(size>0){
  			$("#open").hide();
  			$("#ck_td").show();
  			$("#clearAll_td").show();
  			$("#pageZone").show();
  			$(".movie_ul:gt(9)").show();
  			$("#empty").hide();
  		}else if(size<=0){
  			$("#open").hide();
  			$("#pageZone").hide();
  			$("#ck_td").hide();
  			$("#clearAll_td").hide();
  			$("#empty").show();
  		}
  		$(".movie_ul").mouseover(function(){
			$(this).find(".m_del").show();
			$(this).find(".m_time").hide();
		}).mouseout(function(){
			$(this).find(".m_del").hide();
			$(this).find(".m_time").show();
		});	
		//删除所选
		$("#delCKNotes").toggle(function(){
			$(".note").show();
			$(this).attr("title","确认删除");
			$(this).html("确认删除");
			$("#quxiao").show();
			$("#all_td").show();
			$(".movie_ul").css("height","343");
			$(".show").css("height","1580");
			open();
			$(".pic_a").unbind();
			$(".pic_a").bind("click",function(){
				if($(this).prev().attr("checked")=="checked"){
					$(this).prev().attr("checked",false);
				}else{
					$(this).prev().attr("checked",true);
				}
				checkBox();
			});
 		},function(){
			var checkBoxs = $(".note:checked");
			if(checkBoxs.length == 0){
					return;
			}
			if(confirm("您确认删除选中的观看记录么？")){
	  			$("#myform").submit();
	  			//通过超链接提交表单方法如下
	  			//1、可以使用form的submit();方法
	  			//2、先在表单里创建一个提交按钮，并设置display属性为none，使其隐藏
	  			//超链接的href写成：javascript：按钮名称.click();即触发按钮点击事件
			}
		});
		$("#quxiao a").click(function(){
			$(".note").hide();
			$(".note").attr("checked",false);
			$("#delCKNotes").attr("title","删除多个");
			$("#delCKNotes").html("删除多个");
			$("#quxiao").hide();
			$("#all_td").hide();
			$("#all").attr("checked",false);
			$(".movie_ul").css("height","325");
			$(".show").css("height","1510");
			$(".pic_a").unbind();
			$(".pic_a").bind("click",function(){
				var vid = $(this).next().val();
				location = "VideoServlet?videoid="+vid+"&op=playVideo";
			});
		});
		//点击图片播放视频
		$(".pic_a").bind("click",function(){
			var vid = $(this).next().val();
			location = "VideoServlet?videoid="+vid+"&op=playVideo";
		});
  	});
  	//“展示全部”点击事件
  	function open(){
  		$("#open").hide();
		$("#pageZone").show();
		$(".movie_ul:gt(9)").show();
  	};
  	//下拉列表
  	function changeIndex(){
   		var index = document.getElementById("SelectIndex").value;
	   	location="VideoNoteServlet?op=showVideoNoteList&pageIndex="+index;
   } 