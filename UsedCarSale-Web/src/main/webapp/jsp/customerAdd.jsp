<%--
  Created by IntelliJ IDEA.
  User: bahjan
  Date: 2021-05-05
  Time: 21:33
  添加客户窗口
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>

<div class="col-md-6 col-sm-6 col-xs-6">
    <div class="x_panel">
        <div class="x_title">
            <h2> 添加客户 </h2>
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        <i class="fa fa-wrench"></i></a>
                </li>
                <li><a class="close-link"><i class="fa fa-close"></i></a>
                </li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <br/>
            <form id="addCustomer" data-parsley-validate class="form-horizontal form-label-left"
                  action="${pageContext.request.contextPath}/customer/insertCustomer.action" method="post">
                <input type="hidden" name="empId" value="${emp.empId}"/>
                <input type="hidden" name="companyId" value="${emp.companyId}"/>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="customerName_add">客户姓名
                        <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" id="customerName_add" name="customerName" required="required"
                               onblur="verName(this)" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="customerPhone_add">客户电话
                        <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" id="customerPhone_add" name="customerPhone"
                               onblur="verifyTel1(this)" required="required" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">客户性别
                        <span class="required">*</span>
                    </label>
                    <p style="margin-bottom: 0px;border-left-width: 0px;padding-left: 130px;padding-top: 5px;">
                        男:
                        <input type="radio" class="flat" name="customerSex" value="男" checked="" required/>
                        女:
                        <input type="radio" class="flat" name="customerSex" value="女"/>
                    </p>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">
                        客户类型&nbsp;<span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <select name="customerType" class="select2_single form-control" tabindex="-1">
                            <option value="1">销售</option>
                            <option value="2">收购</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">
                        客户生日<span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="form-group">
                            <div class='input-group date customerBirthday_format'>
                                <input id='customerBirthday_add' name="customerBirthday" type='text'
                                       class="form-control"/>
                                <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ln_solid"></div>
                <div class="form-group">
                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-4">
                        <button id="customerAddSubmit" type="submit" class="btn btn-success">提交</button>
                        <button type="reset" class="btn btn-primary">重置</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="../vendors/jquery/dist/jquery.min.js"></script>

<script>
    function verifyTel1(e) {
        var customerAddSubmit = document.getElementById('customerAddSubmit');
        var telphoneVerify = /^1[3|4|5|7|8][0-9]{9}$/;
        var empPhone = e.value;
        console.log(empPhone);
        //判断是否为空
        if (empPhone === "") {
            alert("电话号码不能为空");
        } else {
            //按照手机号码正则测试
            if (telphoneVerify.test(empPhone)) {
                //如果是true代表手机号码格式正确
                //解除按钮禁止
                customerAddSubmit.setAttribute('class', 'btn btn-success');
            } else {
                //如果是false代表手机号码格式不正确
                //禁止提交按钮点击
                customerAddSubmit.setAttribute('class', 'btn btn-danger disabled');
            }
        }
    }

    function verName(e) {
        var customerAddSubmit = document.getElementById('customerAddSubmit');
        var projectName = $('#projectName').attr('project');
        var customerName = e.value;
        $.getJSON(
            projectName + '/customer/isExistCustomerName.action',
            {customerName: customerName},
            function (data) {
                if (data.exist === 'false') {
                    //如果是true代表手机号码格式正确
                    //解除按钮禁止
                    customerAddSubmit.setAttribute('class', 'btn btn-success');
                } else {
                    //如果是false代表手机号码格式不正确
                    //禁止提交按钮点击
                    alert("姓名重复");
                    customerAddSubmit.setAttribute('class', 'btn btn-danger disabled');
                }
            });
    }
</script>
</body>
</html>
