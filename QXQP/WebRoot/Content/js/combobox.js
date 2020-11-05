//点击table的行选择复选框
function Check(selector, index) {
    if ($(selector).eq(index).find("input[type='checkbox']").attr("checked")) {
        $(selector).eq(index).find("input[type='checkbox']").get(0).removeAttribute("checked");
    } else {
        $(selector).eq(index).find("input[type='checkbox']").attr("checked", "checked");
    }
}


