<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda" %>
  <head><title>Salon Books</title></head>

  <body>
	<h2>Welcome to Salon Books! </h2>
	<div>Enter a customer phone number, orderId, or Appointment Id to get started</div>

	<form:form 	id="lookupByPhoneNumber" modelAttribute="homeFlowActions" action="${flowExecutionUrl}" >
			<!-- 
      <input type="hidden" name="_flowExecutionKey" 
             value="${flowExecutionKey}"/>
             -->
		Phone: <form:input type="text" name="phoneNumberEntered" path="phoneNumberEntered" />
		<form:errors cssClass="error" path="phoneNumberEntered" />
		<input type="submit" name="_eventId_phoneNumberEntered"
			value="Lookup Customer By Phone" />
		<br />
	</form:form>
		<hr />
	<form:form 	id="startAppointment" modelAttribute="homeFlowActions" action="${flowExecutionUrl}" >
			Appointment Id: <form:input type="text" name="appointmentId" path="appointmentId"/> |
     	 <input type="submit" name="_eventId_startAppointment"
			value="Start Appointment" />
		<br />
	</form:form>
		<hr />
	<form:form 	id="startOrder" modelAttribute="homeFlowActions" action="${flowExecutionUrl}" >
			Order Id: <form:input type="text" name="orderId" path="orderId" /> |
      <input type="submit" name="_eventId_startOrder"	value="Start Order" />
		<br />
	</form:form>
		<hr />

	<form:form 	id="lookupByLastName"  modelAttribute="homeFlowActions" action="${flowExecutionUrl}" >
		<input type="hidden" name="_flowExecutionKey"
			value="${flowExecutionKey}" />
		<form:input type="text" name="lastName" path="lastName"/> |
		<input type="submit" name="_eventId_lastNameEntered"
			value="Lookup Customer By Last Name" />
	</form:form>

<div id="appointentList">
List of Open Appointments
<table id="openAppointmentsTable" >
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
<tr>
<td><c:out value="${appt.name}" /></td>
<td><joda:format value="${appt.date}" pattern="MM dd, YYYY HH:mm"/></td>
<td><c:out value="${appt.notes }" /></td>
<td><a href="_eventId=startApointment" id="${appt.id}" >Start</a></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
	
</body>
</html>
