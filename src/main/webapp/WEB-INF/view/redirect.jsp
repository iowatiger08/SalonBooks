<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/includes/taglibInclude.jsp" %>

<form:actionURL var="url">
	<input type="hidden" name="execution" value="${flowExecutionKey}" />
	<input type="hidden" name="_eventId" value="redirect" />
</form:actionURL>

<script type="text/javascript">
	var url = '${url}';
	$(function() {
		var $body = $('.modal-body');
		addAjaxSpinner($body, 'medium', 'center');
		document.location.href = url;
	});
</script>