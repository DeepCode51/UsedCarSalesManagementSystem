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
            <h2> 添加员工信息 </h2>
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
                  action="${pageContext.request.contextPath}/system/insertEmployee.action" method="post">
                <input type="hidden" name="adminEmpId" value="${emp.empId}"/>
                <input type="hidden" name="empPassword" value="123456"/>
                <input type="hidden" name="companyId" value="${emp.companyId}"/>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="employeeName_add">员工姓名
                        <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" id="employeeName_add" name="empName" required="required"
                               onblur="verName2(this)" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="employeePhone_add">员工电话
                        <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" id="employeePhone_add" name="empPhone"
                               onblur="verifyTel2(this)" required="required" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">
                        员工职务&nbsp;<span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <select name="positionId" class="select2_single form-control" tabindex="-1">
                            <option value="3">销售员</option>
                            <option value="2">用户</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="employeeSalery_add">员工工资
                        <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" id="employeeSalery_add" name="empSalery"
                               required="required" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>
                <div class="ln_solid"></div>
                <div class="form-group">
                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-4">
                        <button type="submit" id="empolyeeAddSubmit" class="btn btn-success">提交</button>
                        <button type="reset" class="btn btn-primary">重置</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

<script>
    function verifyTel2(e) {
        var customerAddSubmit = document.getElementById('empolyeeAddSubmit');
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

    function verName2(e) {
        var customerAddSubmit = document.getElementById('empolyeeAddSubmit');
        var projectName = $('#projectName').attr('project');
        var customerName = e.value;
        $.getJSON(
            projectName + '/system/isExistEmpName.action',
            {name: customerName},
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
