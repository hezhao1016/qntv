$(function(){
		//得到焦点
		$("#password").focus(function(){
			$("#left_hand").animate({
				left: "150",
				top: " -38"
			},{step: function(){
				if(parseInt($("#left_hand").css("left"))>140){
					$("#left_hand").attr("class","left_hand");
				}
			}}, 2000);
			$("#right_hand").animate({
				right: "-64",
				top: "-38px"
			},{step: function(){
				if(parseInt($("#right_hand").css("right"))> -70){
					$("#right_hand").attr("class","right_hand");
				}
			}}, 2000);
		});
		//失去焦点
		$("#password").blur(function(){
			$("#left_hand").attr("class","initial_left_hand");
			$("#left_hand").attr("style","left:100px;top:-12px;");
			$("#right_hand").attr("class","initial_right_hand");
			$("#right_hand").attr("style","right:-112px;top:-12px");
		});
		//更换验证码
		$("#code_pic").click(function(){
			$("#code_pic").attr("src","images.jsp");
			$("#code_pic").attr("src","images.jsp");
		});
	});
	//Login事件
	function Login(){
		var myform = $("#myform");
		if ($("#username").val().length<=0) {
			alert("请输入用户名！");
			return;
		}
		if ($("#password").val().length<=0) {
			alert("请输入用户密码！");
			return;
		}
		if ($("#code").val().length<=0) {
			alert("请输入验证码！");
			return;
		}
		myform.submit();
	}
	//获取密码
	function getMi(){
		var name = document.getElementById("username").value;
		location = "PasswordClassServlet?username="+name;
	}
	$(function(){
		$("#username,#password,#code").keydown(function(event){
			if(event.keyCode == "13"){//按Enter键提交表单
				Login();
			}
		});
	});