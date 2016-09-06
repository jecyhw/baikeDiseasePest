<%--
  Created by IntelliJ IDEA.
  User: jecyhw
  Date: 16-8-28
  Time: 下午5:03
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
    <title>害虫数据</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="page-header">
            <h2>所有病虫害数据  <span class="label label-info">${diseasePests.size()}</span></h2>
        </div>
        <div>
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <c:forEach items="${diseasePests}" var="diseasePest" varStatus="status">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading<c:out value="${status.count}"/>">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse<c:out value="${status.count}"/>" aria-expanded="true" aria-controls="collapse<c:out value="${status.count}"/>">
                                    <c:out value="${diseasePest.name}"/>
                                </a>
                            </h4>
                        </div>
                        <div id="collapse<c:out value="${status.count}"/>" class="panel-collapse collapse <c:if test="${status.first}">in</c:if>" role="tabpanel" aria-labelledby="heading<c:out value="${status.count}"/>">
                            <div class="panel-body" style="overflow: auto; max-height: 500px;">
                                <c:out value="${diseasePest}"/>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <%--<div style="position: fixed;">--%>
            <%--<c:forEach items="${diseasePests}" var="diseasePest" varStatus="status">--%>
                <%--<a class="label label-primary" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse<c:out value="${status.count}"/>" aria-expanded="true" aria-controls="collapse<c:out value="${status.count}"/>">--%>
                    <%--${diseasePest.name}--%>
                <%--</a>--%>
            <%--</c:forEach>--%>
        <%--</div>--%>
    </div>
</div>
<script src="jquery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="jsonview/jsonview.js"></script>
<script>
    $(".panel-body").each(function () {
        $(this).JSONView($(this).html());
    });
</script>
</body>
</html>
