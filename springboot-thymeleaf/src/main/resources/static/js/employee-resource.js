var grid_selector = "#jqGrid";
var pager_selector = "#jqGridPager";
var rowNum = 10; 			//每页显示记录数
var task = null;			//任务（新增或编辑）
var loading;
$(function () {
    $(window).resize(function () {
        $(grid_selector).setGridWidth($(window).width() * 0.95);
    });

    $(grid_selector).jqGrid({
        url: "/infoOS/employee/findByAll",
        datatype: "json",
        mtype: 'POST',
        height: window.screen.height - 550,
        colModel: [
            {label: '员工编号', name: 'id', width: 75},
            {label: '姓名', name: 'name', width: 200},
            {label: '性别', name: 'sex', width: 200},
            {label: '身份证号', name: 'idcard', width: 200},
            {label: '联系方式', name: 'tel', width: 200},
            {label: '职位', name: 'job', width: 200}
        ],
        pager: pager_selector,
        rowNum: rowNum,
        rowList: [10, 30, 45], //可调整每页显示的记录数
        viewrecords: true,//是否显示行数
        altRows: true,  //设置表格 zebra-striped 值
        gridview: true, //加速显示
        multiselect: true,//是否支持多选
        multiselectWidth: 40, //设置多选列宽度
        multiboxonly: true,
        shrinkToFit: true, //此属性用来说明当初始化列宽度时候的计算类型，如果为ture，则按比例初始化列宽度。如果为false，则列宽度使用colModel指定的宽度
        forceFit: true, //当为ture时，调整列宽度不会改变表格的宽度。当shrinkToFit为false时，此属性会被忽略
        autowidth: true,
        loadComplete: function () {
            var table = this;
            setTimeout(function () {
                updatePagerIcons(table);
            }, 0);
        },
        gridComplete: function () {
            // 防止水平方向上出现滚动条
            removeHorizontalScrollBar();
        },
        jsonReader: {//jsonReader来跟服务器端返回的数据做对应
            root: "rows",   //包含实际数据的数组
            total: "total", //总页数
            records: "records", //查询出的总记录数
            repeatitems: false //指明每行的数据是可以重复的，如果设为false，则会从返回的数据中按名字来搜索元素，这个名字就是colModel中的名字
        },
        emptyrecords: '没有记录!',
        loadtext: '正在查询服务器数据...'
    });

    //设置分页按钮组
    $(grid_selector).jqGrid('navGrid', pager_selector,
        {
            edit: false,
            // edittitle:'修改',
            // edittext:'修改',
            // editicon : 'icon-pencil blue',
            // editfunc :editUser,
            add: false,
            // addtitle:'新增',
            // addtext:'新增',
            // addicon : 'icon-plus-sign purple',
            // addfunc :addUser,
            del: false,
            // deltitle:'删除',
            // deltext:'删除',
            // delicon : 'icon-trash red',
            // delfunc:delUser,
            refresh: true,
            refreshicon: 'icon-refresh green',
            beforeRefresh: refreshData,
            search: false,
            view: false,
            alertcap: "警告",
            alerttext: "请选择需要操作的用户!"
        }
    );

    //查询点击事件
    $("#queryBtn").click(function () {
        var qryId = $("#qryId").val();
        var qryName = $("#qryName").val();
        $(grid_selector).jqGrid('setGridParam', {
            postData: {id: qryId, name: qryName},
            //search: true,
            page: 1
        }).trigger("reloadGrid");
    });

    //新增与员工信息，弹出新增窗口
    $("#addEmployeeBtn").click(function () {
        task = "saveEmployee";
        initData();
        $('#myModalLabel').text('新增员工信息');
        $("#addModal").modal({
            keyboard: false,
            show: true,
            backdrop: "static"
        });

    });

    //编辑对话框取消点击事件
    $('#cancelSave').click(function () {
        $("#addModal").modal('hide');
    });

    //保存信息
    $('#saveEmployeeBtn').click(function () {
        saveEmployee();
    });

    //修改员工信息，弹出修改窗
    $("#modifyEmployeeBtn").click(function () {
        var rows = $(grid_selector).getGridParam('selarrrow');
        if (rows == 0) {
            // $.messager.alert("温馨提示","请选择一行记录！");
            layer.msg('请选择一行记录！', {icon: 7, time: 2000}); //2秒关闭（如果不配置，默认是3秒）
            return;
        } else if (rows.length > 1) {
            // $.messager.alert("温馨提示","不能同时修改多条记录！");
            layer.msg('不能同时修改多条记录！', {icon: 7, time: 2000}); //2秒关闭（如果不配置，默认是3秒）
            return;
        } else {
            var data = $(grid_selector).jqGrid('getRowData', rows[0]);
            task = "updateEmployee";
            initData();
            $("#id").val(data.id);
            $("#name").val(data.name);
            $("#sex").val(data.sex);
            $("#idcard").val(data.idcard);
            $("#tel").val(data.tel);
            $("#job").val(data.job);
            $('#myModalLabel').text('修改员工信息');
            $("#addModal").modal({
                keyboard: false,
                show: true,
                backdrop: "static"
            });
        }
    });

    //删除员工信息方法 选择多个的话，行id用逗号隔开比如 3,4
    $("#deleteEmployeeBtn").click(function () {
        var rows = $(grid_selector).getGridParam('selarrrow');
        if (rows.length > 0) {
            $.messager.confirm("温馨提示", "是否确定删除所选记录？", function () {
                $.ajax({
                    url: "/infoOS/employee/deleteByIds",
                    cache: false,
                    type: "post",
                    data: {"ids": rows.join(",")},
                    beforeSend: function () {
                        loading = layer.load("正在删除中...");
                    },
                    success: function (result) {
                        $.messager.alert(result.message);
                        refreshData();
                    }, error: function () {
                        $.messager.alert("温馨提示", "请求错误!");
                    },
                    complete: function () {
                        layer.close(loading);
                    }
                });
            });
        } else {
            // $.messager.alert("温馨提示","至少选择一行记录！");
            layer.msg('至少选中一行记录！', {icon: 7, time: 2000}); //2秒关闭（如果不配置，默认是3秒）
        }
    })

});

function removeHorizontalScrollBar() {
    $("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width", parseInt($("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width")) + 1 + "px");
    $(grid_selector).closest(".ui-jqgrid-bdiv").css("width", parseInt($(grid_selector).closest(".ui-jqgrid-bdiv").css("width")) + 1 + "px");
}


//初始化数据
function initData() {
    $('#name').val("");
    $('#sex').val("");
    $('#idcard').val("");
    $("#tel").val("");
    $("#job").val();
}


/**
 * 保存信息（新增或修改）
 */
function saveEmployee() {
    var id = $('#id').val();
    var name = $('#name').val();
    var sex = $('#sex').val();
    var idcard = $('#idcard').val();
    var tel = $('#tel').val();
    var job = $('#job').val();
    $.ajax({
        url: "/infoOS/employee/" + task,
        cache: false,
        dataType: 'json',
        data: {
            "id": id,
            "name": name,
            "sex": sex,
            "idcard": idcard,
            "tel": tel,
            "job": job
        },
        type: 'post',
        beforeSend: function () {
            // 禁用按钮防止重复提交
            $('#saveEmployeeBtn').attr({disabled: "disabled"});
        },
        success: function (result) {
            if (result.flag === true) {
                $.messager.alert('温馨提示', result.message);
                $("#addModal").modal('hide');
                refreshData();
            } else {
                $.messager.alert('温馨提示', result.message);
            }
        },
        complete: function () {
            $('#saveEmployeeBtn').removeAttr("disabled");
        },
        error: function (data) {
            console.info("error: " + data.responseText);
        }
    });
}


function refreshData() {
    $(grid_selector).jqGrid('setGridParam', {
        postData: {name: null, sex: null, idcard: null, tel: null, job: null},
        page: 1
    }).trigger("reloadGrid");
}


//这个是分页图标，必须添加
function updatePagerIcons(table) {
    var replacement =
        {
            'ui-icon-seek-first': 'icon-double-angle-left bigger-140',
            'ui-icon-seek-prev': 'icon-angle-left bigger-140',
            'ui-icon-seek-next': 'icon-angle-right bigger-140',
            'ui-icon-seek-end': 'icon-double-angle-right bigger-140'
        };
    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
        console.info($class);
        if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
    });
}
