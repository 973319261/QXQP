/**
 * jquery 根据json对象填充form表单
 * @author en
 * @param fromId form表单id
 * @param jsonDate json对象
 */
function selectcreate(selectId, jsonData) {
    $("#" + selectId).empty();//清空该元素
    for (k in jsonData) {
        $("#" + selectId).append('<option value="' + jsonData[k].id + '">' + jsonData[k].name + '</option>');
    }
}
function loadDatatoForm(fromId, jsonDate) {
    var obj = jsonDate;
    var key, value, tagName, type, arr;
    for (x in obj) {//循环json对象
        key = x;
        value = obj[x];
        $("#" + fromId + " [name='" + key + "'],#" + fromId + " [name='" + key + "[]']").each(function () {
            tagName = $(this)[0].tagName;
            type = $(this).attr('type');
            if (tagName == 'INPUT') {
                if (type == 'radio') {
                    $(this).attr('checked', $(this).val() == value);
                } else if (type == 'checkbox') {
                    try {
                        arr = value.split(',');
                        for (var i = 0; i < arr.length; i++) {
                            if ($(this).val() == arr[i]) {
                                $(this).prop('checked', true);
                                break;
                            }
                        }
                    } catch (e) {
                        //单个
                        $(this).prop('checked', value);
                    }
                } else {
                    $(this).val(value);
                }
            } else if (tagName == 'TEXTAREA') {
                $(this).val(value);
            } else if (tagName == 'SELECT') {
                if ($(this).hasClass("select2")) {
                    $(this).val(value).trigger("change");
                } else {
                    $(this).val(value);
                }

            }

        });
    }
}
//为下来框添加选项
function appendOption(id, url) {
    $.getJSON(url, function (data) {
        $("#" + id).empty();
        $("#" + id).append("<option value=" + 0 + ">" + "----请选择----" + "</option>"); 
        data=data.list;
        $.each(data, function (i) {
            $("#" + id).append("<option value=" + data[i].id + ">" + data[i].name + "</option>");
        });
    });
}
function selectLoadData(selectId, jsonData) {
    $("#" + selectId).empty();//清空该元素
    for (k in jsonData) {
        $("#" + selectId).append('<option value="' + jsonData[k].id + '">' + jsonData[k].name + '</option>');
    }
}

function createSelect(selectId, url) {
    $.ajax({
        type: "post",
        url: url,
        dataType: "json",
        async: false,
        success: function (data) {
            selectLoadData(selectId, data.list);
        }
    });
}


/**
 * 图片大小压缩
 * @param {} maxWidth 
 * @param {} maxHeight 
 * @param {} objImg 
 * @returns {} 
 */
function AutoResizeImage(maxWidth, maxHeight, objImg) {
    var img = new Image();
    img.src = objImg.src;
    var hRatio;
    var wRatio;
    var Ratio = 1;
    var w = img.width;
    var h = img.height;
    wRatio = maxWidth / w;
    hRatio = maxHeight / h;
    if (maxWidth == 0 && maxHeight == 0) {
        Ratio = 1;
    } else if (maxWidth == 0) {//
        if (hRatio < 1) {
            Ratio = hRatio;
        }
    } else if (maxHeight == 0) {
        if (wRatio < 1) {
            Ratio = wRatio;
        }
    } else if (wRatio < 1 || hRatio < 1) {
        Ratio = (wRatio <= hRatio ? wRatio : hRatio);
    }
    if (Ratio < 1) {
        w = w * Ratio;
        h = h * Ratio;
    }
    objImg.height = h;
    objImg.width = w;
}
//表格编辑
function tdclick(tdobject) {
    //0,获取当前的td节点
    var td = $(tdobject);
    //1,取出当前td中的文本内容保存起来
    td.attr("click", "");
    //1,取出当前td中的文本内容保存起来
    var text = td.text();
    //2，清空td里边内同
    td.html("");
    //3,建立一个文本框，也就是建一个input节点
    var input = $("<input>");
    //4,设置文本框中值是保存起来的文本内容
    input.attr("value", text);
    //4.5让文本框可以相应键盘按下的事件
    input.keyup(function (event) {
        //记牌器当前用户按下的键值
        var myEvent = event || window.event;//获取不同浏览器中的event对象
        var kcode = myEvent.keyCode;
        //判断是否是回车键按下
        if (kcode == 13) {
            var inputnode = $(this);
            //获取当前文本框的内容
            var inputext = inputnode.val();
            //清空td里边的内容,然后将内容填充到里边
            var tdNode = inputnode.parent();
            tdNode.html(inputext);
            //让td重新拥有点击事件
            tdNode.click(tdclick);
        }
    }).blur(function () {
        var inputnode = $(this);
        //获取当前文本框的内容
        var inputext = inputnode.val();
        //清空td里边的内容,然后将内容填充到里边
        var tdNode = inputnode.parent();
        tdNode.html(inputext);
        //让td重新拥有点击事件
        tdNode.click(tdclick);
    });
    //5，把文本框加入到td里边去
    td.append(input);
    //5.5让文本框里边的文章被高亮选中
    //需要将jquery的对象转换成dom对象
    var inputdom = input.get(0);
    inputdom.select();

    //6,需要清楚td上的点击事件
    td.unbind("click");
}
//切换卡
