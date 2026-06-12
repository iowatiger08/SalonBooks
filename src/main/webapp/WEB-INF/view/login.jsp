<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <title>SalonBooks – Login</title>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
</head>
<body>
<div class="container" style="max-width:420px; margin-top:60px;">
  <h3>SalonBooks</h3>

<%
  if (request.getParameter("error") != null) {
%>
  <div class="alert alert-danger">Invalid username or password.</div>
<%
  }
  if (request.getParameter("logout") != null) {
%>
  <div class="alert alert-success">You have been logged out.</div>
<%
  }
%>

  <form method="POST" action="/login">
    <div class="form-group">
      <label>Username</label>
      <input class="form-control" type="text" name="username" autofocus/>
    </div>
    <div class="form-group">
      <label>Password</label>
      <input class="form-control" type="password" name="password"/>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button class="btn btn-primary" type="submit">Login</button>
  </form>
</div>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
