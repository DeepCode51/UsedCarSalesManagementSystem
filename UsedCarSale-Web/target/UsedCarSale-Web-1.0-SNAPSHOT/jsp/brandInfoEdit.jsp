<%--
  Created by IntelliJ IDEA.
  User: bahjan
  Date: 2021-05-05
  Time: 21:33
  修改客户窗口
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>

<div class="col-md-6 col-sm-6 col-xs-6">
    <div class="x_panel">
        <div class="x_title">
            <h2> 修改品牌信息 </h2>
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
                  action="${pageContext.request.contextPath}/brand/updateBrand.action" method="post">
                <input type="hidden" name="empId" value="${emp.empId}"/>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="id">ID
                        <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" id="id" name="id" required="required"
                               class="form-control col-md-7 col-xs-12" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="old_id">旧名称
                        <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" id="old_id" required="required"
                               class="form-control col-md-7 col-xs-12" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="new_id">新名称
                        <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" id="new_id" name="name" required="required"
                               onblur="verName4(this)" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>
                <div class="ln_solid"></div>
                <div class="form-group">
                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-4">
                        <button type="submit" id="customerEditSubmit2" class="btn btn-success">提交</button>
                        <button type="reset" class="btn btn-primary">重置</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

<script>
    function verName4(e) {
        var customerAddSubmit = document.getElementById('customerEditSubmit2');
        var projectName = $('#projectName').attr('project');
        var customerName = e.value;
        $.getJSON(
            projectName + '/brand/existCarBrandName.action',
            {name: customerName},
            function (data) {
                if (data.exist === 'false') {
                    //如果是true代表手机号码格式正确
                    //解除按钮禁止
                    customerAddSubmit.setAttribute('class', 'btn btn-success');
                } else {
                    //如果是false代表手机号码格式不正确
                    //禁止提交按钮点击
                    alert("品牌重复");
                    customerAddSubmit.setAttribute('class', 'btn btn-danger disabled');
                }
            });
    }
</script>
</body>
</html>
