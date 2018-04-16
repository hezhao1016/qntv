  function red(c){
	  c.css("color","red");
  }
  function green(c){
	  c.css("color","green");
  }
  function orange(c){
	  c.css("color","orange");
  }
  function checkClassName(){
	  var reg = /^.{1,10}$/;
	  var val = $("#classname").val();
	  var show = $("#classname_pram");
	  if(val==""){
		  show.html("✘ 电影类型名称不能为空");
		  red(show);
		  return false;
	  }
	  if(!reg.test(val)){
		show.html("✘ 电影类型名称长度为1-10位");
		red(show);
		return false;
	  }
	  show.html("✔");
	  green(show);
	  return true;
  }
  	$(function(){
  		$("#myform").submit(function(){
			if(checkClassName()){
				return true;
			}else{
				return false;
			}
		});
  		$("#classname").blur(checkClassName).focus(function(){
			var show = $("#classname_pram");
			show.html("  电影类型名称长度为1-10位，不能为空");
			orange(show);
		});
  		
  		$(".btn").mouseover(function(){
			$(this).css("border","2px solid red");
			$(this).css("color","red");
		}).mouseout(function(){
			$(this).css("border","1px solid  green");
			$(this).css("color","black");
		});
  		$("#classname").mouseover(function(){
  			$(this).addClass("text");
  		}).mouseout(function(){
  			$(this).removeClass("text");
  		});
  	});