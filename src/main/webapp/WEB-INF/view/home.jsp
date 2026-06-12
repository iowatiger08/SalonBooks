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
		<h1>Welcome to Salon Books!</h1>

		<div class="col-md-6" >

			<form:form id="home" modelAttribute="homeFlowActions" method="POST"
				action="${flowExecutionUrl}" role="form">
				<input type="hidden" name="_flowExecutionKey"
					value="${flowExecutionKey}" />

				<div class="panel panel-default"  >
					<div class="panel-heading">
						<strong class="panel-title">
						Enter a client phone number to get started:
						</strong>
					</div>
					<div class="panel-body">
					
						<form:input type="text" path="phoneNumberEntered"
							id="phoneNumberEntered" />
						<form:errors cssClass="error" path="phoneNumberEntered" />
						<br /> <input type="submit" name="_eventId_lookupByPhoneNumber"
							value="Lookup" /> <input type="button" name="_eventId_cancel"
							value="Cancel" />
						
					</div>
				</div>
			</form:form>

		</div>
		<hr />
		Store Tasks: <br /> <a href="./item/list">Store Inventory List</a> <br />
		<br/>
		Order Report: <br /> <a href="./report"> </a>

		<hr />

		<div id="appointmentList" class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">List of Open Appointments</div>
			</div>

			<div class="panel-body">
				<table id="openAppointmentsTable" class="table table striped">
					<thead>
						<tr>
							<th>Person</th>
							<th>Date Time</th>
							<th>Notes</th>
							<th>Link</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${openAppointments}" var="appt">
						<c:if test="${ appt.id>0}">
							<tr>
								<td><c:out
										value="${appt.person.lastName}, ${appt.person.firstName} " /></td>
								<td><joda:format value="${appt.appointmentDate}"
										pattern="MM dd, YYYY HH:mm" /></td>
								<td><c:out value="${appt.notes }" /></td>
								<td><spring:url value="/appointment/${appt.id }"
										var="apptUrl">
										<%-- <spring:param name="apptId" value ="${appt.id }" /> --%>
									</spring:url> <a href="${(apptUrl)}">Start</a></td>
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
