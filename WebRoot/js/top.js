//搜索框特效
	function searchTextBoxFocus(){
		var textbox = document.getElementById("videoname");
		if(textbox.value =="请输入搜索内容"){
			textbox.value="";
		}
	}
	function searchTextBoxBlur(){
		var textbox = document.getElementById("videoname");
		if(textbox.value ==""){
			textbox.value="请输入搜索内容";
		}
	}
	function CheckSearchPost(){
		var textbox = document.getElementById("videoname");
		if(textbox.value =="请输入搜索内容"){
			return false;
		}
		return true;
	}
	//顶栏特效
	window.onscroll = function(){
		var top = document.body.scrollTop
		var header = document.getElementById("header");
		header.style.top = top;//顶栏停留
	};
	function headListOver(i){
		var lis = document.getElementsByClassName("hli");
		lis[i].style.backgroundColor = "gray";
	}
	function checkOutOver(){
		var ck = document.getElementById("checkout");
		ck.style.backgroundColor = "red";
	}
	function headListOut(i){
		var lis = document.getElementsByClassName("hli");
		lis[i].style.backgroundColor = "black";
	}
	function headListClick(i){
		var lis = document.getElementsByClassName("hli");
		location.href = lis[i].getElementsByTagName("a")[0].href;
	}
	
//--------------------------地区特效------------------------
	function onzone(zone){
		var esy = document.getElementById(zone);
		var esq = document.getElementById("全部");
		var esn = document.getElementById("内地");
		var esx = document.getElementById("香港");
		var est = document.getElementById("台湾");
		var esr = document.getElementById("日本");
		var esh = document.getElementById("韩国");
		var esm = document.getElementById("美国");
		var eso = document.getElementById("欧洲");
		if(esq.innerHTML == esy.innerHTML){
			esn.removeAttribute("style");
			esx.removeAttribute("style");
			est.removeAttribute("style");
			esr.removeAttribute("style");
			esh.removeAttribute("style");
			esm.removeAttribute("style");
			eso.removeAttribute("style");
		}
		if(esn.innerHTML == esy.innerHTML){
			esq.removeAttribute("style");
			esx.removeAttribute("style");
			est.removeAttribute("style");
			esr.removeAttribute("style");
			esh.removeAttribute("style");
			esm.removeAttribute("style");
			eso.removeAttribute("style");
		}
		if(esx.innerHTML == esy.innerHTML){
			esn.removeAttribute("style");
			esq.removeAttribute("style");
			est.removeAttribute("style");
			esr.removeAttribute("style");
			esh.removeAttribute("style");
			esm.removeAttribute("style");
			eso.removeAttribute("style");
		}
		if(est.innerHTML == esy.innerHTML){
			esn.removeAttribute("style");
			esx.removeAttribute("style");
			esq.removeAttribute("style");
			esr.removeAttribute("style");
			esh.removeAttribute("style");
			esm.removeAttribute("style");
			eso.removeAttribute("style");
		}
		if(esr.innerHTML == esy.innerHTML){
			esn.removeAttribute("style");
			esx.removeAttribute("style");
			est.removeAttribute("style");
			esq.removeAttribute("style");
			esh.removeAttribute("style");
			esm.removeAttribute("style");
			eso.removeAttribute("style");
		}
		if(esh.innerHTML == esy.innerHTML){
			esn.removeAttribute("style");
			esx.removeAttribute("style");
			est.removeAttribute("style");
			esr.removeAttribute("style");
			esq.removeAttribute("style");
			esm.removeAttribute("style");
			eso.removeAttribute("style");
		}
		if(esm.innerHTML == esy.innerHTML){
			esn.removeAttribute("style");
			esx.removeAttribute("style");
			est.removeAttribute("style");
			esr.removeAttribute("style");
			esh.removeAttribute("style");
			esq.removeAttribute("style");
			eso.removeAttribute("style");
		}
		if(eso.innerHTML == esy.innerHTML){
			esn.removeAttribute("style");
			esx.removeAttribute("style");
			est.removeAttribute("style");
			esr.removeAttribute("style");
			esh.removeAttribute("style");
			esm.removeAttribute("style");
			esq.removeAttribute("style");
		}
		location = "VideoServlet?op=top50&zone="+esy.innerHTML+"&order=playcount desc";
		esy.style.color = "#fff";
		esy.style.background = "#40b8ec";	
	}