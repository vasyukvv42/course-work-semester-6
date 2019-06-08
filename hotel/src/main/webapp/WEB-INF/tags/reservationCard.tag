<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-06-01
  Time: 07:42
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="Room request card tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@attribute name="reservation" type="edu.kpi.hotel.model.entity.Reservation"%>

<div class="card text-white bg-${reservation.invoice.paid ? "success" : "warning"} mb-3">
    <div class="card-header">
        Room #${reservation.roomNumber},
        hotel "${reservation.hotel.name}" (${reservation.hotel.city}, ${reservation.hotel.country})
    </div>
    <div class="card-body">
        <h5 class="card-title">
            Reservation period:
            <fmt:formatDate type="date" value="${reservation.reservedFrom}"/>
            –
            <fmt:formatDate type="date" value="${reservation.reservedTo}"/>
        </h5>
        <p class="card-text">
            <br>
            Total price: <b>${reservation.invoice.totalPrice}$</b>
        </p>
        <c:if test="${!reservation.invoice.paid}">
            <form:form method="post" action="/reservations/${reservation.id}/pay-invoice">
                <button type="submit" class="btn btn-primary mb-2">Pay invoice</button>
            </form:form>
        </c:if>
    </div>
</div>