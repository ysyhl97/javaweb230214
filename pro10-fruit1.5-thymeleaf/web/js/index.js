//删除按钮
function deleteButton(fid) {
    if (confirm("您确定要删除这条数据吗?")) {
        window.location.href = "/pro10/delete.do?fid=" + fid;
    }

}