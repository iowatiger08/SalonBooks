<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<head>
<title>Salon Books</title>
<jsp:include page="../includes/headTag.jsp" />
</head>

<body>
	<div class="container"  style="width:500px">
		<h1>Salon Books Order Reports</h1>

		<div class="col-md-6" >



		</div>
		<hr />
		Store Tasks: <br /> <a href="./item/list">Store Inventory List</a> <br />
		<br/>
		Order Report: <br /> <a href="./report"> </a>

		<hr />

		<div id="orderList" class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">List of Orders</div>
			</div>

			<div class="panel-body">
				<table id="OrderTable" class="table table striped">
					<thead>
						<tr>
							<th>Person</th>
							<th>Date Time</th>
							<th>Order Total</th>
							<th>Order Item Count</th>
							<th>Order Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders}" var="order">
						<c:if test="${ order.id>0}">
							<tr>
								<td><c:out
										value="${order.person.lastName}, ${order.person.firstName} " /></td>
								<td><joda:format value="${order.updateDate}"
										pattern="MM dd, YYYY HH:mm" /></td>
								<td><c:out value="${order.total }" /></td>
								<td>
									<c:out value ="${order.numOfItems }" />
									</td>
								<td><spring:url value="/person/${order.person.primaryPhoneNumber }/order/${order.id }"
										var="orderUrl">
										<%-- <spring:param name="apptId" value ="${appt.id }" /> --%>
									</spring:url> <a href="${(orderUrl)}"><c:out value="${order.status }" /></a>
									</td>	
							</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

<jsp:include page="../includes/footer.jsp" />
</body>
</html>
