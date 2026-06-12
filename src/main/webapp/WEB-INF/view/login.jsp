<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
  <meta charset="utf-8">
  <title>Welcome to SalonBooks</title>
  <jsp:include page="../includes/headTag.jsp"/>
</head>
<body>
<div class="container" id="pageBox" style="width:500px">
  <div>Welcome to SalonBooks!!</div>
  <br/>

  <c:if test="${param.error != null}">
    <div style="color:red">Invalid username or password.</div>
  </c:if>
  <c:if test="${param.logout != null}">
    <div style="color:green">You have been logged out.</div>
  </c:if>

  <form method="POST" action="<c:url value='/login'/>">
    User: <br/><input type="text" name="username"/>
    <br/>
    Passcode: <br/><input type="password" name="password"/>
    <br/>
    <input type="submit" value="Login"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>
</div>
</body>
</html>
