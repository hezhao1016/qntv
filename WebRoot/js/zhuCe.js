//失去焦点
	function checkUser(){
		var reg = /^[a-zA-Z][a-zA-Z0-9]{3,15}$/;
		var u = document.getElementById("userid").value;
		var s = document.getElementById("user_prompt");
		red(s);
		if(u == ""){
			s.innerHTML = "✘ 用户名不能为空";
			return false;
		}
		if(!reg.test(u)){
			s.innerHTML = "✘ 用户名输入格式不正确";
			return false;
		}
		green(s);
		s.innerHTML = "✔";
		return true;
	}
	function checkPwd(){
		var reg = /^[a-zA-Z0-9]{4,10}$/;
		var u = document.getElementById("pwd").value;
		var s = document.getElementById("pwd_prompt");
		red(s);
		if(u == ""){
			s.innerHTML = "✘ 密码不能为空";
			return false;
		}
		if(!reg.test(u)){
			s.innerHTML = "✘ 密码输入格式不正确";
			return false;
		}
		green(s);
		s.innerHTML = "✔";
		return true;
	}
	function checkNickName(){
		var u = document.getElementById("nickname").value;
		var s = document.getElementById("nickname_prompt");
		red(s);
		if(u == ""){
			s.innerHTML = "✘ 昵称不能为空";
			return false;
		}
		if(u.length>10){
			s.innerHTML = "✘ 昵称长度不能超过10位";
			return false;
		}
		green(s);
		s.innerHTML = "✔";
		return true;
	}
	function checkAnswer(){
		var u = document.getElementById("answer").value;
		var s = document.getElementById("answer_prompt");
		red(s);
		if(u == ""){
			s.innerHTML = "✘ 密保答案不能为空";
			return false;
		}
		if(u.length>100){
			s.innerHTML = "✘ 密保答案长度不能超过100位";
			return false;
		}
		green(s);
		s.innerHTML = "✔";
		return true;
	}
	function checkAdress(){
		var u = document.getElementById("adress").value;
		var s = document.getElementById("adress_prompt");
		red(s);
		if(u == ""){
			s.innerHTML = "✘ 地址不能为空";
			return false;
		}
		if(u.length>100){
			s.innerHTML = "✘ 地址长度不能超过100位";
			return false;
		}
		green(s);
		s.innerHTML = "✔";
		return true;
	}
	function checkRepwd(){
		var u = document.getElementById("repwd").value;
		var s = document.getElementById("repwd_prompt");
		var pwd = document.getElementById("pwd").value;
		red(s);
		if(u == ""){
			s.innerHTML = "✘ 确认密码不能为空";
			return false;
		}
		if(u != pwd){
			s.innerHTML = "✘ 两次密码输入不一致";
			return false;
		}
		green(s);
		s.innerHTML = "✔";
		return true;
	}
	function checkMobile(){
		var reg = /^1\d{10}$/;
		var u = document.getElementById("mobile").value;
		var s = document.getElementById("mobile_prompt");
		red(s);
		if(u == ""){
			s.innerHTML = "✘ 手机号码不能为空";
			return false;
		}
		if(!reg.test(u)){
			s.innerHTML = "✘ 手机号码输入格式不正确";
			return false;
		}
		green(s);
		s.innerHTML = "✔";
		return true;
	}
	function checkBirth(){
		var reg = /^((19\d\d)||20(0\d||1[0-5]))-(0?[1-9]||1[0-2])-(0?[1-9]||1\d||2\d||3[0-1])$/;
		var u = document.getElementById("birth").value;
		var s = document.getElementById("birth_prompt");
		red(s);
		if(u == ""){
			s.innerHTML = "✘ 生日不能为空";
			return false;
		}
		if(!reg.test(u)){
			s.innerHTML = "✘ 生日输入格式不正确";
			return false;
		}
		green(s);
		s.innerHTML = "✔";
		return true;
	}
	//获的焦点
	function showUser(){
		var s = document.getElementById("user_prompt");
		s.innerHTML = "用户名由英文字母和数字组成的4-16位字符，以字母开头";
		orange(s);
	}
	function showPwd(){
		var s = document.getElementById("pwd_prompt");
		s.innerHTML = "密码由英文字母和数字组成的4-10位字符";
		orange(s);
	}
	function showRePwd(){
		var s = document.getElementById("repwd_prompt");
		s.innerHTML = "两次密码输入要一致";
		orange(s);
	}
	function showMoblie(){
		var s = document.getElementById("mobile_prompt");
		s.innerHTML = "手机号码，如15878167654";
		orange(s);
	}
	function showBirth(){
		var s = document.getElementById("birth_prompt");
		s.innerHTML = "出生日期，如1996-10-16";
		orange(s);
	}
	function showNickName(){
		var s = document.getElementById("nickname_prompt");
		s.innerHTML = "昵称由英文字母和汉字组成，不大于10位";
		orange(s);
	}
	function showAdress(){
		var s = document.getElementById("adress_prompt");
		s.innerHTML = "请输入您的常用地址";
		orange(s);
	}
	function showAnswer(){
		var s = document.getElementById("answer_prompt");
		s.innerHTML = "请输入您的密保答案";
		orange(s);
	}
	//改变字体颜色
	function red(d){
		d.style.color = "red";
	}
	function green(d){
		d.style.color = "green";
	}
	 function orange(d){
	 	d.style.color = "orange";
	 }
	//提交
	function check(){
		if(checkUser() & checkPwd() & checkRepwd() & checkBirth() & checkNickName() & checkMobile()  & checkAdress() & checkAnswer()){
			return true;
		}
		return false;
	}
	$(function(){
		$(":input").mouseover(function(){
			$(this).addClass("text");
		}).mouseout(function(){
			$(this).removeClass("text");
		});
		$(".btn").mouseover(function(){
			$(this).css("border","2px solid red");
			$(this).css("color","red");
		}).mouseout(function(){
			$(this).css("border","1px solid  green");
			$(this).css("color","black");
		});
	});