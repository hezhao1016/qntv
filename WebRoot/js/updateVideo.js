function red(c){
	  c.css("color","red");
  }
  function green(c){
	  c.css("color","green");
  }
  function orange(c){
	  c.css("color","orange");
  }
	function checkVideoName(){
		var reg = /^.{1,50}$/;
		var val = $("#videoname").val();
	    var show = $("#videoname_pram");
	    if(val==""){
	 	  show.html("✘ 电影名称不能为空");
		  red(show);
		  return false;
	   }
	   if(!reg.test(val)){
		show.html("✘ 电影名称长度为1-50位");
		red(show);
		return false;
	   }
	   show.html("✔");
	   green(show);
	   return true;
	}
	function checkDescribe(){
		var reg = /^[\s || \S]{50,10000}$/;
		var val = $("#describe").val();
		var show = $("#describe_pram");
	    if(val==""){
	 	  show.html("✘ 电影描述不能为空");
		  red(show);
		  return false;
	   }
	   if(!reg.test(val)){
		show.html("✘ 电影描述长度不能少于50个字符");
		red(show);
		return false;
	   }
	   show.html("✔");
	   green(show);
	   return true;
	}
	function checkSize(){
		var reg = /^.{1,25}$/;
		var val = $("#size").val();
		var show = $("#size_pram");
	    if(val==""){
	 	  show.html("✘ 电影大小不能为空");
		  red(show);
		  return false;
	   }
	   if(!reg.test(val)){
		show.html("✘ 电影大小长度为1-25位");
		red(show);
		return false;
	   }
	   show.html("✔");
	   green(show);
	   return true;
	}
	function checkDirector(){
		var reg = /^.{1,25}$/;
		var val = $("#director").val();
		var show = $("#director_pram");
	    if(val==""){
	 	  show.html("✘ 导演姓名不能为空");
		  red(show);
		  return false;
	   }
	   if(!reg.test(val)){
		show.html("✘ 导演姓名长度为1-25位");
		red(show);
		return false;
	   }
	   show.html("✔");
	   green(show);
	   return true;
	}
	function checkActor(){
		var reg = /^.{1,50}$/;
		var val = $("#actor").val();
		var show = $("#actor_pram");
	    if(val==""){
	 	  show.html("✘  主演姓名不能为空");
		  red(show);
		  return false;
	   }
	   if(!reg.test(val)){
		show.html("✘ 主演姓名长度为1-50位");
		red(show);
		return false;
	   }
	   show.html("✔");
	   green(show);
	   return true;
	}
	function checkTitle(){
		var reg = /^.{5,25}$/;
		var val = $("#title").val();
		var show = $("#title_pram");
	    if(val==""){
	 	  show.html("✘  电影标题不能为空");
		  red(show);
		  return false;
	   }
	   if(!reg.test(val)){
		show.html("✘ 电影标题长度为5-25位");
		red(show);
		return false;
	   }
	   show.html("✔");
	   green(show);
	   return true;
	}
	$(function(){
		$("#myform").submit(function(){
			if(checkVideoName() & checkTitle() & checkDirector() & checkActor()
	  			& checkSize() & checkDescribe()){
	  				return true;
	  			}else{
	  				return false;
	  			}
		 });
		$("#videoname").blur(checkVideoName).focus(function(){
			var show = $("#videoname_pram");
			show.html("  电影名称长度为1-50位，不能为空");
			orange(show);
		});
		$("#title").blur(checkTitle).focus(function(){
			var show = $("#title_pram");
			show.html("  电影标题长度为5-25位，不能为空");
			orange(show);
		});
		$("#director").blur(checkDirector).focus(function(){
			var show = $("#director_pram");
			show.html("  导演姓名长度为1-25位，不能为空");
			orange(show);
		});
		$("#actor").blur(checkActor).focus(function(){
			var show = $("#actor_pram");
			show.html("  主演姓名长度为1-50位，不能为空");
			orange(show);
		});
		$("#size").blur(checkSize).focus(function(){
			var show = $("#size_pram");
			show.html("  电影大小长度为1-25位，不能为空");
			orange(show);
		});
		$("#describe").blur(checkDescribe).focus(function(){
			var show = $("#describe_pram");
			show.html("  电影描述不能少于50字");
			orange(show);
		});
		
		$(".btn").mouseover(function(){
			$(this).css("border","2px solid red");
			$(this).css("color","red");
		}).mouseout(function(){
			$(this).css("border","1px solid  green");
			$(this).css("color","black");
		});
		$(":input").mouseover(function(){
  			$(this).addClass("text");
  		}).mouseout(function(){
  			$(this).removeClass("text");
  		});
	});