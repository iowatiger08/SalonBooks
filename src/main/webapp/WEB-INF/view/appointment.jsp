<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/taglibInclude.jsp" %>
  <head>
  <title>SalonBooks : Appointment</title>
<jsp:include page="../includes/headTag.jsp"/>
</head>

  <body class="container 400px">
	<h3>Appointment Info</h3>
	<div id="personBanner">
	<h3><c:out value="Name: ${person.firstName} ${person.lastName}"/> <small><c:out value="Phone: ${person.primaryPhoneNumber} "/></small></h3>
	</div>
	<div id="appointmentForm">
		<form:form id="appointment" modelAttribute="appointmentFlowActions" class="form-inline" role="form">
			<input type="hidden" name="_flowExecutionKey"
				value="${flowExecutionKey}" />
		<form:input type="hidden" path="createDate" name="createDate" />
				
		<div class="row form-group form-group-md" >
		<label class="col-md-6 control-label" for="apptDate">Appointment Date</label>
		<div class="col-md-8" id="apptDate">
		<form:select name="entityDate" path="entityDate" class="form-control" style="width:75px">
				<form:options items="${DATES}" />
			</form:select> 
		 <form:select name="entityMonth" path="entityMonth" class="form-control" style="width:75px">
				<form:options items="${MONTHS}" />
			</form:select> 
		 <form:select name="entityYear" path="entityYear" class="form-control" style="width:100px">
				<form:options items="${YEARS}" />
			</form:select>
			<form:hidden path="appointmentDate" />
			</div>
			<%-- <form:hidden path="localeTZ" />--%>
			</div>
			<br/>
		<div class="row form-group form-group-md">
		<label class="col-md-6 control-label" for="apptTime">Appointment Time</label>
		<div class="col-md-8" id="apptTime">
		 <form:select name="entityHour" path="entityHour" class="form-control"  style="width:75px">
				<form:options items="${HOURS}" />
			</form:select>
		 : <form:select name="entityMinute" path="entityMinute" class="form-control"  style="width:75px">
				<form:options items="${MINUTES}" />
			</form:select>
			</div>
			</div>
			<br/>
			<div class="row form-group form-group-md">

		 <label class="col-md-6 control-label" for="appointmentStatusType">Status ::</label>
		 <form:select id="appointmentStatusType" name="status" path="appointmentStatusType" class="form-control input-sm col-md-1" >
				<form:options items="${statusTypeList}" />
			</form:select>
			</div>
			<br />

			<div class="row form-group form-group-md">
		  <label class="col-md-6 control-label" for="notes" style="vertical-align:top">Notes for Current:</label>
		   <form:textarea id="notes" name="notes" path="notes" rows="3"
				cols="80" class="form-control"/>
			<form:errors path="notes" cssClass="fieldError" />
			</div>
			<br/>
			<div class="row form-group form-group-md">
			<form:hidden path="appointmentId" />
			<form:hidden path="personId" />
		<label class="col-md-6 control-label"for="addOrdertoAppointment" >Add Order to Appointment:</label> 
		<form:checkbox id="addOrdertoAppointment" 
		path="addOrdertoAppointment" 
		value="addOrdertoAppointment" 
		class="form-control" /> 
		</div>
		<div class="row form-group form-group-md">
		<label class="col-md-7 control-label" for="apptButton" > </label> 
           <input type="submit" name="_eventId_updateAppointment" id="apptButton"
				value="Update Appointment Info" class="btn btn-primary" />
				</div>
			
		</form:form>
	</div>
				<hr />
				<spring:url value="../../../person/${person.primaryPhoneNumber}" var="personUrl" />
			<a href="${personUrl}">Edit Person </a> | <a href="../../../home" >Home</a>
				<br />
<hr/>

	<div id="appointmentList">
		<c:choose>
		<c:when test="${appointmentList.size()>1 }">
		Other Appointments for <c:out value="Name: ${person.firstName} ${person.lastName}" />
<br/>
		<table id="personAppointmentsTable" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Date Time</th>
					<th>Notes</th>
					<th>Status</th>
					<th>Link</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${appointmentList}" var="appt2">
					<c:if test="${appt.id!= appt2.id}">
					<tr>
						<td><joda:format value="${appt2.appointmentDate}"
								pattern="MM dd, YYYY HH:mm" /></td>
						<td><c:out value="${appt2.notes }" /></td>
						<td><c:out value="${appt2.appointmentStatusType }" /></td>
						<td><spring:url value="/person/${person.primaryPhoneNumber}/appointment/${appt2.id }"
								var="apptUrl">
								<%-- <spring:param name="apptId" value="${appt.id }" /> --%>
							</spring:url> <a href="${(apptUrl)}">Start</a></td>
					</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
		</c:when>
		<c:otherwise>
		<br/>No other appointments found for this person.
		</c:otherwise>
		</c:choose>
	</div>
<jsp:include page="../includes/footer.jsp" />

</body>
</html>
