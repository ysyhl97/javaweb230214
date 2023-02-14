//删除按钮
function deleteButton(fid,operate) {
    if (confirm("您确定要删除这条数据吗?")) {
        window.location.href = "index?fid=" + fid+"&operate="+operate;
    }

}

//分页
function page(pageNumber){
    window.location.href = "index?pageNumber="+pageNumber;
}
