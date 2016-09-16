<%--
  Created by IntelliJ IDEA.
  User: jecyhw
  Date: 16-9-12
  Time: 下午6:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang=en>
<head>
    <title>虫害数据展示</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link href="<c:url value="/pivotviewer/content/pivotviewer-0.9.23.min.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/pivotviewer/content/pivotviewer-0.9.23.templates.min.html"/>" rel="template" id="pv-templates" />
    <script type="text/javascript" src="<c:url value="/pivotviewer/scripts/jquery-1.8.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/pivotviewer/scripts/jquery-ui-1.10.0.custom.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/pivotviewer/scripts/modernizr.custom.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/pivotviewer/scripts/easing.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/pivotviewer/scripts/jquery.mousewheel.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/pivotviewer/scripts/mobiscroll-2.0.2.custom.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/pivotviewer/scripts/mustache.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/pivotviewer/scripts/ICanHaz-no-mustache.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/pivotviewer/scripts/pubsub.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/pivotviewer/scripts/jquery.nouislider.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/pivotviewer/scripts/pivotviewer-0.9.23.1.min.js"/>"></script>
</head>
<body>
<div id="pivotviewer"></div>
<script type="text/javascript">
    var debug = true;
    $(document).ready(function () {
        $("#pivotviewer").PivotViewer({
            Loader: new PivotViewer.Models.Loaders.CXMLLoader("/ForestryDiseaseAndInsectPest-1.0/pestPivotViewer.cxml"),
            AnimateBlank: false,
            ViewerState: "$view$=1&$facet0$=Category",
            ItemAdornerTemplate: "tile_link_adorner",
            ItemSelectedTemplate: "tile_link_adorner",
            AllowItemsCheck: false,
            CopyToClipboard: true,
            DisplayFeedback: false
//      , ZoomHelperMaxLevel: 7
        });
    });
</script>
<script type="text/html" id="tile_link_adorner">
    <ul class="pv-adorner-list">
        <li>
            <a href="{{#Item.Facets.Session URL}}{{Href}}{{/Item.Facets.Session URL}}" target="_blank" class="pv-adorner-basiclink">{{#Item.Facets.Session URL}}{{Value}}{{/Item.Facets.Session URL}}</a>
        </li>
        {{#Item.Facets.Speaker}}
        <li>
            <a href="{{Href}}" target="_blank" class="pv-adorner-basiclink">{{Value}}</a>
        </li>
        {{/Item.Facets.Speaker}}
    </ul>
</script>
</body>
</html>
