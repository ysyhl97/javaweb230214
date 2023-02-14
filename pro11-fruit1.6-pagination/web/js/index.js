//删除按钮
function deleteButton(fid) {
    if (confirm("您确定要删除这条数据吗?")) {
        window.location.href = "delete.do?fid=" + fid;
    }

}

//分页
function page(pageNumber){
    console.log(pageNumber);
    window.location.href = "index?pageNumber="+pageNumber;
}
