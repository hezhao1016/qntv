	function red_1(c){
  	  c.css("color","red");
    }
    function green_1(c){
  	  c.css("color","green");
    }
   function orange_1(c){
	  c.css("color","orange");
   }
    function checkOldPwd(){
      var val = $("#oldpwd").val();
   	  var show = $("#oldpwd_prams");
   	  if(val==""){
   		  show.html("✘ 原密码不能为空");
   		 red_1(show);
   		  return false;
   	  }
   	  if(val.length<4){
   		show.html("✘ 原密码长度不能少于4位");
   		red_1(show);
   		return false;
   	  }
   	  show.html("✔");
   	  green_1(show);
   	  return true;
    }
	function checkNewPwd(){
		  var val = $("#newpwd").val();
	   	  var show = $("#newpwd_prams");
	   	  if(val==""){
	   		  show.html("✘ 新密码不能为空");
	   		 red_1(show);
	   		  return false;
	   	  }
	   	  if(val.length<4){
	   		show.html("✘ 新密码长度不能少于4位");
	   		red_1(show);
	   		return false;
	   	  }
	   	  show.html("✔");
	      green_1(show);
	   	  return true;
    }
	function checkRePwd(){
		var val = $("#repassword").val();
	   	  var show = $("#repassword_prams");
	   	  if(val==""){
	   		  show.html("✘ 确认密码不能为空");
	   		 red_1(show);
	   		  return false;
	   	  }
	   	  if(val.length<4){
	   		show.html("✘ 确认密码长度不能少于4位");
	   		red_1(show);
	   		return false;
	   	  }
	    	if(val!=$("#newpwd").val()){
	   		show.html("✘ 两次密码输入不一致");
	   		red_1(show);
	   		return false;
	   	  }
	   	  show.html("✔");
	   	  green_1(show);
	   	  return true;
	}
	function checkUpdatePwd(){
		if(checkRePwd() & checkNewPwd() & checkOldPwd()){
				return true;
			}else{
				return false;
			}
	}
	$(function(){
		$("#oldpwd").blur(checkOldPwd).focus(function(){
			var show = $("#oldpwd_prams");
			show.html("  请输入您原来的密码");
			orange_1(show);
		});
		$("#newpwd").blur(checkNewPwd).focus(function(){
			var show = $("#newpwd_prams");
			show.html("  请输入您的新密码");
			orange_1(show);
		});
		$("#repassword").blur(checkRePwd).focus(function(){
			var show = $("#repassword_prams");
			show.html("  请再次输入您的新密码");
			orange_1(show);
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