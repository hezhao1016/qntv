$(function(){
	$("#header li").mouseover(function(){
  		$(this).css("background-color","gray");
  	}).mouseout(function(){
  		$(this).css("background-color","black");
  	}).click(function(){
  		location = $(this).find("a").attr("href");
  	});
	$("#header #checkout").mouseover(function(){
  		$(this).css("background-color","red");
  	}).mouseout(function(){
  		$(this).css("background-color","black");
  	});
  	//顶部导航栏悬停
	$(window).scroll(function(){
		var top = $(this).scrollTop();
		$("#header").css("top",top);
	});
});