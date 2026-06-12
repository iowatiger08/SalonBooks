<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/taglibInclude.jsp"%>
<head>
<title>SalonBooks: Client</title>

<jsp:include page="../includes/headTag.jsp" />
</head>
<body>
	<div class="container" id="pageBox" style="width: 800px">
		<h3>Edit Client Info</h3>

		<form:form id="person" modelAttribute="personFlowActions"
			class="form-horizontal" role="form">
			<table class="table tabled-bordered  table-condensed"
				style="width: 600px">
				<tbody>
					<tr>
						<td align="left" valign="top">
							<div class="row form-group form-group-md">
								<label for="primaryPhone" class="col-md-6 control-label"
									style="text-align: left">Primary Phone:</label>

								<div class="col-md-6">
									<form:input type="hidden" path="personId" name="personId" />
									<form:input type="hidden" path="createDate" name="createDate" />
									<form:input type="hidden" path="createDate" name="addressCreateDate" />
									<form:input id="primaryPhone" type="text" name="phoneNumber"
										path="phoneNumber" class="form-control" />
									<form:errors cssClass="error" path="phoneNumber" />
								</div>
							</div>
							<div class="row form-group form-group-md">
								<label for="prefix" class="col-md-6 control-label"
									style="text-align: left">Prefix:</label>

								<div class="col-md-6">
									<form:input id="prefix" type="text" name="prefix" path="prefix"
										class="form-control" />
								</div>
							</div>
							<div class="row form-group form-group-md">
								<label for="lastName" class="col-md-6 control-label"
									style="text-align: left">Last Name:</label>

								<div class="col-md-6">
									<form:input id="lastName" type="text" name="lastName"
										path="lastName" class="form-control" />
								</div>
							</div>
							<div class="row form-group form-group-md">
								<label for="firstName" class="col-md-6 control-label"
									style="text-align: left">First Name:</label>

								<div class="col-md-6">
									<form:input id="firstName" type="text" name="firstName"
										path="firstName" class="form-control" />
								</div>
							</div>
							<div class="row form-group form-group-md">
								<label for="birthday" class="col-md-6 control-label"
									style="text-align: left">Birthday (MM-dd):</label>

								<div class="col-md-6">
									<form:input id="birthday" type="text" name="birthday"
										path="birthday" class="form-control" />
									<form:errors cssClass="error" path="birthday" />
								</div>
							</div>
							<div class="row form-group form-group-md">
								<label for="gender" class="col-md-6 control-label"
									style="text-align: left">Gender:</label>

								<div class="col-md-6">
									<form:select id="gender" name="gender" path="gender"
										class="form-control">
										<form:options items="${genderList}" />
									</form:select>
									<form:input type="hidden" path="addreessId" name="addreessId" />
								</div>
							</div>

						</td>
						<td align="left" valign="top">
							<div class="row form-group form-group-md">
								<label for="line1" class="col-md-6 control-label"
									style="text-align: left">Address Line 1:</label>

								<div class="col-md-6">
									<form:input id="line1" type="text" name="line1" path="line1"
										class="form-control" />
								</div>
							</div>
							<div class="row form-group form-group-md">
								<label for="line2" class="col-md-6 control-label"
									style="text-align: left">Address Line 2:</label>

								<div class="col-md-6">
									<form:input id="line2" type="text" name="line2" path="line2"
										class="form-control" />
								</div>
							</div>
							<div class="row form-group form-group-md">
								<label for="city" class="col-md-6 control-label"
									style="text-align: left">City:</label>

								<div class="col-md-6 form-group-md">
									<form:input id="city" type="text" name="city" path="city"
										class="form-control" />
								</div>
							</div>
							<div class="row form-group form-group-md">
								<label for="state" class="col-md-6 control-label"
									style="text-align: left">State:</label>

								<div class="col-md-6">
									<form:input id="state" type="text" name="state" path="state"
										class="form-control" />
									<form:errors cssClass="error" path="state" />
								</div>
							</div>
							<div class="row form-group form-group-md">
								<label for="zipcode" class="col-md-6 control-label"
									style="text-align: left">Zip Code:</label>

								<div class="col-md-6">
									<form:input id="zipCode" type="text" name="zipCode"
										path="zipCode" class="form-control" />
									<form:errors cssClass="error" path="zipCode" />
								</div>
							</div>
							<div class="row form-group form-group-md">
								<label for="homePhoneNumber" class="col-md-6 control-label"
									style="text-align: left">Home Phone:</label>

								<div class="col-md-6">
									<form:input id="homePhoneNumber" type="text"
										path="homePhoneNumber" name="homePhoneNumber"
										class="form-control" />
									<form:errors cssClass="error" path="homePhoneNumber" />
								</div>
							</div>
							<div class="row form-group form-group-md">
								<label for="email" class="col-md-6 control-label"
									style="text-align: left">Email:</label>

								<div class="col-md-6">
									<form:input type="text" id="email" name="email" path="email"
										class="form-control" />
								</div>
							</div>
							<div class="row form-group form-group-md">
								<label for="twitter" class="col-md-6 control-label"
									style="text-align: left">Twitter:</label>

								<div class="col-md-6">
									<form:input type="text" id="twitter" name="twitter"
										path="twitter" class="form-control" />
								</div>
							</div>
							<div class="row form-group form-group-md">
								<label class="col-md-6 control-label"> </label>

								<div class="col-md-6">
									<input type="hidden" name="_flowExecutionKey"
										value="${flowExecutionKey}" /> <input type="submit"
										name="_eventId_addEditPerson" value="Update Client Info" />
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
		<hr />
		<a href="./${primaryPhoneNumber}/appointment/new">New Appointment</a>
		| <a href="./${primaryPhoneNumber}/order/new">New Order</a> 
		| <a href="./../home" >Home</a>
		<hr />

		<div id="appointmentList">
			<c:choose>
				<c:when test="${appointmentList.size()>0 }">
	 Appointments for <c:out
						value="Name: ${personFlowActions.firstName} ${personFlowActions.lastName}" />
					<br />
					<table id="personAppointmentsTable"
						class="table table-striped table-bordered">
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

								<tr>
									<td><joda:format value="${appt2.appointmentDate}"
											pattern="MM dd, YYYY HH:mm" /></td>
									<td><c:out value="${appt2.notes }" /></td>
									<td><c:out value="${appt2.appointmentStatusType }" /></td>
									<td><spring:url value="./../person/${ personFlowActions.phoneNumber}/appointment/${appt2.id }"
											var="apptUrl">
											<%-- <spring:param name="apptId" value="${appt.id }" /> --%>
										</spring:url> <a href="${(apptUrl)}">Open</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<br />No other appointments found for this person.
		</c:otherwise>
			</c:choose>
		</div>
<jsp:include page="../includes/footer.jsp" />
	</div>
</body>
</html>
