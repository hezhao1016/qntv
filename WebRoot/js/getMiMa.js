	function red_1(c){
  	  c.css("color","red");
    }
    function green_1(c){
  	  c.css("color","green");
    }
     function orange_1(c){
	  c.css("color","orange");
   }
	function checkUserName(){
		  var val = $("#username").val();
	   	  var show = $("#username_prams");
	   	  if(val==""){
	   		  show.html("✘ 用户名不能为空");
	   		 red_1(show);
	   		  return false;
	   	  }
	   	  show.html("✔");
	      green_1(show);
	   	  return true;
    }
	function checkAnswer(){
		var val = $("#answer").val();
	   	  var show = $("#answer_prams");
	   	  if(val==""){
	   		  show.html("✘ 密保答案不能为空");
	   		 red_1(show);
	   		  return false;
	   	  }
	   	  show.html("✔");
	   	  green_1(show);
	   	  return true;
	}
	function checkGetMiMa(){
		if(checkUserName() & checkAnswer()){
				return true;
			}else{
				return false;
			}
	}
	$(function(){
			$("#username").blur(checkUserName).focus(function(){
				var show = $("#username_prams");
				show.html("  请输入您要找回的账号");
				orange_1(show);
			});
			$("#answer").blur(checkAnswer).focus(function(){
				var show = $("#answer_prams");
				show.html("  请选择您的密保答案");
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