/**
 * jquery 根据json对象填充form表单
 * @author en
 * @param fromId form表单id
 * @param jsonDate json对象
 */
function selectcreate(selectId, jsonData) {
    $("#" + selectId).empty();//清空该元素
    for (k in jsonData) {
        $("#" + selectId).append('<option value="' + jsonData[k].id + '">' + jsonData[k].text + '</option>');
    }
}
function loadDatatoForm(fromId, jsonDate) {
    var obj = jsonDate;
    var key, value, tagName, type, arr;
    for (x in obj) {//循环json对象
        key = x;
        value = obj[x];
        //$("[name='"+key+"'],[name='"+key+"[]']").each(function(){
        //更加form表单id 和 json对象中的key查找 表单控件
        $("#" + fromId + " [name='" + key + "'],#" + fromId + " [name='" + key + "[]']").each(function () {
            tagName = $(this)[0].tagName;
            type = $(this).attr('type');
            if (tagName == 'INPUT') {
                if (type == 'radio') {
                    $(this).attr('checked', $(this).val() == value);
                } else if (type == 'checkbox') {
                    try {
                        //数组
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
                //console.log($(this).hasClass("select2"));
                if ($(this).hasClass("select2")) {
                    //select2 插件的赋值方法
                    $(this).val(value).trigger("change");
                } else {
                    $(this).val(value);
                }

            }

        });
    }
}

function selectLoadData(selectId, jsonData) {
    $("#" + selectId).empty();//清空该元素
    for (k in jsonData) {
        $("#" + selectId).append('<option value="' + jsonData[k].id + '">' + jsonData[k].text + '</option>');
    }
}

function createSelect(selectId, url) {
    $.ajax({
        type: "post",
        url: url,
        dataType: "json",
        async: false,
        success: function (data) {
            selectLoadData(selectId, data);
        }
    });
    //$.post(url, function (data) {
    //    selectLoadData(selectId, data);        
    //});
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
function tdclick(obj) {
    //1,获取当前的td节点
    var td = $(obj);
    //2,取出当前td中的文本内容保存起来
    var text = td.text();
    //3，清空td里的文本
    td.html("");
    //4,建立一个文本框，也就是建一个input节点
    var input = $("<input>");
    //5,设置文本框中值是保存起来的文本内容
    input.attr("value", text);
    //6,让文本框可以相应键盘按下的事件
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
            var tdNode = inputnode.parent();//获取父节点td
            tdNode.html(inputext);//赋值给td
            //让td重新拥有点击事件
            tdNode.click(tdclick);
        }
    }).blur(function () {//当输入域失去焦点 (blur) 时
        var inputnode = $(this);
        //获取当前文本框的内容
        var inputext = inputnode.val();
        //清空td里边的内容,然后将内容填充到里边
        var tdNode = inputnode.parent();//获取父节点td
        tdNode.html(inputext);//赋值给td
        //让td重新拥有点击事件
        tdNode.click(tdclick);
    });
    //7，把文本框加入到td里边去
    td.append(input);
    //8,让文本框里边的文章被高亮选中
    //需要将jquery的对象转换成dom对象
    var inputdom = input.get(0);
    inputdom.select();
}
