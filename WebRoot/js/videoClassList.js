function delVideoClass(cid){
	if(confirm("您确认删除么？")){
		location = "VideoClassServlet?op=delVideoClass&cid="+cid;
		}
  	}
  //判断电影数量是否小于1,如果电影数量小于1，就把删除所选隐藏
$(function(){
	var size = $(".videoClass").size();
	if(size<1){
		$("#empty").show();
	}else{
		$("#empty").hide();
	}
});