<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/webjars/jquery/jquery.min.js"                  var="jQueryJs"/>
<spring:url value="/webjars/bootstrap/css/bootstrap.min.css"        var="bootstrapCss"/>
<spring:url value="/webjars/bootstrap/css/bootstrap-theme.min.css"  var="bootstrapThemeCss"/>
<spring:url value="/webjars/bootstrap/js/bootstrap.min.js"          var="bootstrapJs"/>
<link href="${bootstrapCss}"      rel="stylesheet"/>
<link href="${bootstrapThemeCss}" rel="stylesheet"/>
<script src="${jQueryJs}"></script>
<script src="${bootstrapJs}"></script>
