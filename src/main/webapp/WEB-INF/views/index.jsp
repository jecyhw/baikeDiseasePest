<%--
  Created by IntelliJ IDEA.
  User: jecyhw
  Date: 16-8-25
  Time: 下午3:44
  To change this com.jecyhw.template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="jsonview/jsonview.css" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>害虫数据采集</title>
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col-sm-6">
            <p></p>
            <div>
                <a href="<c:url value="/all"/>" class="btn btn-primary active" role="button">查看所有病虫害数据</a>
            </div>
            <hr/>
            <form method="post" action="<c:url value="/pestSearch" />">
                <div class="form-group">
                    <label for="pestName">害虫的名称</label>
                    <input type="text" class="form-control" name="pestName" id="pestName" placeholder="例如:马尾松毛虫">
                </div>
                <button type="submit" id="pestSerach" class="btn btn-primary">提交</button>
            </form>
            <hr/>
            <form method="post" action="<c:url value="/diseaseSearch" />">
                <div class="form-group">
                    <label for="diseaseName">病害的名称</label>
                    <input type="text" class="form-control" name="diseaseName" id="diseaseName" placeholder="例如:舞毒蛾">
                </div>
                <button type="submit" id="diseaseSerach" class="btn btn-primary">提交</button>
            </form>
        </div>
    </div>
    <hr/>
    <div id="search-result">

    </div>
</div>
<script src="jquery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="jsonview/jsonview.js"></script>
<script>
    $(document).ready(function() {
        $("form").submit(function () {
            var form = $(this);
            var button = form.find("button");
            $.ajax({
                url: form.attr("action"),
                method: form.attr("method"),
                dataType: 'json',
                data: form.serialize(),
                beforeSend: function () {
                    $("#jsonView").html("正在加载中...");
                    button.addClass("disabled");
                },
                success: function (data) {
                    $("#search-result").html(data.message);
                    $(".panel-body").each(function () {
                        $(this).JSONView($(this).html());
                    });
                },
                searchError: function (xhr, textStatus) {
                },
                complete: function () {
                    button.removeClass("disabled");
                }
            })
            return false;
        });
    });
</script>
</body>
</html>
