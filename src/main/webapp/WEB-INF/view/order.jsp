<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/taglibInclude.jsp" %>
  <head>
  
  <title>Edit Order Info</title>

<jsp:include page="../includes/headTag.jsp"/>
  </head>

  <body>
  <div class="container" id="pageBox" style="width:500px" >
	<h2>Order Info</h2>
	
		<form:form id="order" modelAttribute="orderFlowActions" role="form">

          <div id="orderForm">
		 <input type="hidden" name="_flowExecutionKey" 
             value="${flowExecutionKey}"/>
             <form:hidden path="personId" name="personId"/>
             <form:hidden path="appointmentId" name="appointmentId"/>   
						<form:hidden path="paymentMethodId" name="paymentMethodId" />
						<form:hidden path="shipperId" name="shipperId" />
						<form:input type="hidden" path="createDate" name="createDate" />
						        <hr/>
      <a href="../../../person/${person.primaryPhoneNumber}" >Edit Person </a> | <a href="../../../home" >Home</a> | <a href="../../../person/${person.primaryPhoneNumber}/appointment/${orderFlowActions.appointmentId}" >Edit Appointment</a>
             
			<div id="formHeaderBanner">
				<table class="table tabled-bordered  table-condensed" style="width:400px">
					<thead>
						<tr>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody id="clientInfo">
						<tr>
							<td>Client Name:</td>
							<td><c:out value="${person.firstName } ${person.lastName }" /><br /></td>
						</tr>
						<tr>
							<td>Primary Phone Number:</td>
							<td><c:out value="${person.primaryPhoneNumber }" /><br /></td>
						</tr>
						<c:choose>
						<c:when test="${address!=null }" >
						<tr>
							<td style="vertical-align:top">Billing Address:</td>
							<td><c:out value="${address.line1 }" /><br /> <c:out
									value="${address.city}" />, <c:out value="${address.state}" />
								<c:out value="${address.zip}" /><br /></td>
						</tr>
						</c:when>
						<c:otherwise>
						<tr>
							<td>Billing Address:</td>
							<td>No billing address found</td>
						</tr>
						</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			Order Id: <c:out value="${orderId}" /> | Date: <joda:format value="${order.updateDate}" pattern="MM dd, YYYY"/><br/>
			<small>** Note, if you happen to add an incorrect item to the order, for now, simply re-enter the item marking the quantity as zero. 
			The item will still show but not count in the order totals.</small>
               <table  class="table table-striped">
                <thead>
                    <tr>
                        <th>Label</th>
                        <th>Sku</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="itemListContainer">
                    <c:forEach items="${orderFlowActions.orderItems}" var="orderItem" varStatus="i" begin="0" >
                        <tr class="orderItem">   
                       
							 <td><form:hidden path="orderItems[${i.index}].item.id" id="id${i.index}" />
							 <form:input path="orderItems[${i.index}].item.label" id="label${i.index}" /></td>
                            <td><form:input path="orderItems[${i.index}].item.sku" id="sku${i.index}" /></td>
                            <td><form:input path="orderItems[${i.index}].item.price" id="price${i.index}" /></td>
                            <td><form:input path="orderItems[${i.index}].quantity" id="quantity${i.index}" /></td>
                       
                            <td></td>
                        </tr>
                    </c:forEach>
                    
                    <c:if test="${order.orderItems.size() == 0}">
                        <tr class="item defaultRow">    
                            <td>                                                                            
							 <input type="text" name="orderItems[].label" value="" />
                            </td>
                            <td><input type="text" name="orderItems[].sku" value="" /></td>
                            <td><input type="text" name="orderItems[].price" value="0" /></td>
                            <td><input type="text" name="orderItems[].quantity" value="0" /></td>
 
                            <td></td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
			<table class="table table-bordered table-condensed" style="width:300px">
				<thead>
					<tr>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody id="orderSummary">
					<tr>
						<td>Shipping:</td>
						<td>${order.shippingCost }<br /></td>
						<form:hidden path="shipping" name="shipping" />
					</tr>
					<tr>
						<td>SubTotal:</td>
						<td>${order.subTotal } <br /></td>
						<form:hidden path="subTotal" name="subTotal" />
					</tr>
					<tr>
						<td>Tax:</td>
						<td>${order.tax } <br /></td>
						<form:hidden path="tax" name="tax" />
					</tr>
					<tr>
						<td>Total:</td>
						<td>${order.total } <br /></td>
						<form:hidden path="total" name="total" />
						<form:hidden path="numOfItems" name="numOfItems" />
					</tr>

				</tbody>
			</table>
			<form:select path="saveAndClose" items="${OrderStatusType }"/>
                         
           <input type="submit" name="_eventId_updateOrder" value="Update Order" /> 
           </div>
     	</form:form>
     	<hr />
     	<c:if test ="${!orderStatusClosed }">
     	<div class="panel panel-default" >
     	<div class ="panel-heading">
     	<div class="panel-title">
     	<strong>Select Item to Add ::</strong></div>
     	</div>
     	<div class ="panel-body">
     	<form:form id="orderItemForm" modelAttribute="addOrderItemActions" role="form">
             <form:hidden path="orderId" name="orderId"/>
             <form:hidden path="addItemtoOrder"/>
     	 
     	<form:select path="itemSelect" class="form-control">
    
     	     	<c:forEach  items="${itemList}" var="itemValue">
				    <form:option value="${itemValue.id}">
				        ${itemValue.label} | ${itemValue.sku}
				    </form:option>
		     	</c:forEach>
     	
		</form:select><br />
     	Quantity :: <form:input path="quantity"  class="form-control"/>
     	<form:errors cssClass="error" path="quantity" /><br />     	
        <input type="submit" name="_eventId_addItemtoOrder" value="Add Item to Order" /> 
     	</form:form>
     	</div>
           </div>
           </c:if>
     	<div id="appointentBanner" >
     	<h4>Appointment Notes:
             <small><c:out value="${appointment.notes }" /></small></h4>
     	
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
