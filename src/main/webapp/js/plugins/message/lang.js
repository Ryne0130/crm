$.messager.model = {
    ok:{ text: "确定" },
    cancel: { text: "取消" }
};

function reload() {
    $.messager.alert("温馨提示", "操作成功,1.5S后自动刷新");
    //1.5S后自动刷新界面
    setTimeout(function () {
        location.reload();
    }, 1500);
}
