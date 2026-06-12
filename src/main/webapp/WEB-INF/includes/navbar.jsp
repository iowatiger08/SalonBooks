<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav class="navbar navbar-default navbar-static-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/home">SalonBooks</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/home">Home</a></li>
      <li><a href="/report">Reports</a></li>
      <li><a href="/item/list">Items</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li>
        <form method="POST" action="/logout" style="margin:8px 0;">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <button type="submit" class="btn btn-default btn-sm">Logout</button>
        </form>
      </li>
    </ul>
  </div>
</nav>
