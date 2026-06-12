<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/taglibInclude.jsp"%>
<head>

<title>Store Item List</title>
<jsp:include page="../includes/headTag.jsp" />
</head>

<body>
	<div class="container" id="pageBox" style="width: 600px">
		<% pageContext.setAttribute("now", new org.joda.time.DateTime()); %>
		<h2>
			Store Item List | Date:
			<joda:format value="${now}" pattern="MM dd, YYYY" />
			<br />
		</h2>

		<form:form id="item" modelAttribute="itemFlowActions">

			<div id="itemForm">
				<input type="hidden" name="_flowExecutionKey"
					value="${flowExecutionKey}" />

				<table>
					<thead>
						<tr>
							<th>Label</th>
							<th>Sku</th>
							<th>UnitCost</th>
							<th>Price</th>
							<th>Is Service</th>
							<th>Description</th>
						</tr>
					</thead>
					<tbody id="itemListContainer">
						<c:forEach items="${itemFlowActions.itemList}" var="item"
							varStatus="i" begin="0">
							<tr class="item">
								<td><form:hidden path="itemList[${i.index}].id"
										id="id${i.index}" /> <form:input
										path="itemList[${i.index}].label" id="label${i.index}" /></td>
								<td><form:input path="itemList[${i.index}].sku"
										id="sku${i.index}" /></td>
								<td><form:input path="itemList[${i.index}].unitCost"
										id="unitCost${i.index}" /></td>
								<td><form:input path="itemList[${i.index}].price"
										id="price${i.index}" /></td>
								<td><form:input path="itemList[${i.index}].isService"
										id="isService${i.index}" /></td>
								<td><form:input path="itemList[${i.index}].description"
										id="description${i.index}" /></td>

								<td></td>
							</tr>
						</c:forEach>

						<c:if test="${items.size() == 0}">
							<tr class="item defaultRow">
								<td><input type="text" name="items[].label" value="" /></td>
								<td><input type="text" name="items[].sku" value="" /></td>
								<td><input type="text" name="items[].unitCost" value="0" /></td>
								<td><input type="text" name="items[].price" value="0" /></td>
								<td><input type="text" name="items[].isService" value="N" /></td>
								<td><input type="text" name="items[].description" value="N" /></td>

								<td></td>
							</tr>
						</c:if>
					</tbody>
				</table>


				<input type="submit" name="_eventId_updateItemList"
					value="Update Item" />
			</div>
			<br />
			<hr />
			<a href="../home">Home</a>
		</form:form>
		<hr />
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">Add an Item to the Store:</div>
			</div>
			<div class="panel-body">
				<form:form id="addItemForm" modelAttribute="addItemActions">
					<form:hidden path="addItem" />
     	
     	Label :: <form:input path="label" />
					<br />  
     	Sku :: <form:input path="sku" />
					<br />  
     	Is Service :: <form:input path="isService" />
					<br />  
     	Description :: <form:input path="description" />
					<br />  
     	Price :: <form:input path="price" />
					<br />  
     	Unit Cost :: <form:input path="unitCost" />
					<br />
					<small>for inventory only</small>
					<input type="submit" name="_eventId_addItem" value="Add Item" />
				</form:form>
				<br />
			</div>
		</div>
<jsp:include page="../includes/footer.jsp" />
	</div>
	<script type="text/javascript">
            function rowAdded(rowElement) {
                //clear the imput fields for the row
                $(rowElement).find("input").val('');
                //may want to reset <select> options etc

                //in fact you may want to submit the form
                saveNeeded();
            }
            function rowRemoved(rowElement) {
                saveNeeded();
                alert( "Removed Row HTML:\n" + $(rowElement).html() );
            }

            function saveNeeded() {
                $('#submit').css('color','red');
                $('#submit').css('font-weight','bold');
                if( $('#submit').val().indexOf('!') != 0 ) {
                    $('#submit').val( '!' + $('#submit').val() );
                }
            }

            function beforeSubmit() {
                alert('submitting....');
                return true;
            }

            $(document).ready( function() {
                var config = {
                    rowClass : 'item',
                    addRowId : 'addItem',
                    removeRowClass : 'removeItem',
                    formId : 'order',
                    rowContainerId : 'itemListContainer',
                    indexedPropertyName : 'orderItem',
                    indexedPropertyMemberNames : 'label,sku,price,quantity',
                    rowAddedListener : rowAdded,
                    rowRemovedListener : rowRemoved,
                    beforeSubmit : beforeSubmit
                };
                new DynamicListHelper(config);
            });
        </script>
</body>
</html>
