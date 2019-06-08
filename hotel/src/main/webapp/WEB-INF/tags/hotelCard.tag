<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-06-01
  Time: 02:30
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="Hotel card tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@attribute name="hotel" type="edu.kpi.hotel.model.entity.Hotel"%>

<div class="card">
    <h5 class="card-header">
        <c:forEach begin="1" end="${hotel.rating}" step="1">★</c:forEach><c:forEach begin="${hotel.rating}" end="4" step="1">☆</c:forEach>
        ${hotel.name}
    </h5>
    <div class="card-body">
        <h5 class="card-title">${hotel.city}, ${hotel.country}</h5>
        <h6 class="card-subtitle mb-2 text-muted">${hotel.address}</h6>
        <p class="card-text">${hotel.rooms.size()} rooms</p>
        <sec:authorize access="hasAuthority('ADMINISTRATOR')">
            <form:form class="form-inline" method="post" action="/admin/${hotel.id}/create-room" modelAttribute="room">
                <form:input type="number" class="form-control mb-2 mr-sm-2" path="number" placeholder="402" />
                <form:errors path="number" class="alert-danger"/>

                <div class="input-group mb-2 mr-sm-2">
                    <form:input type="number" class="form-control" path="cost" placeholder="00.00" />
                    <div class="input-group-append">
                        <div class="input-group-text">$/day</div>
                    </div>
                    <form:errors path="cost" class="alert-danger"/>
                </div>

                <form:input type="number" class="form-control mb-2 mr-sm-2" path="maxPeople" placeholder="Max people" />

                <button type="submit" class="btn btn-primary mb-2">Add room</button>
            </form:form>
        </sec:authorize>

        <sec:authorize access="hasAuthority('CLIENT')">
            <form:form class="form-inline" method="post" action="/${hotel.id}/create-request" modelAttribute="request">
                <form:input type="number" class="form-control mb-2 mr-sm-2" path="people" placeholder="People" />

                <div class="input-group mb-2 mr-sm-2">
                    <form:input type="number" class="form-control" path="maxCost" placeholder="00.00" />
                    <div class="input-group-append">
                        <div class="input-group-text">$/day</div>
                    </div>
                </div>

                <form:input type="date" class="form-control mb-2 mr-sm-2" path="reserveFrom" />
                <form:input type="date" class="form-control mb-2 mr-sm-2" path="reserveTo" />

                <button type="submit" class="btn btn-primary mb-2">Request a room</button>
            </form:form>
        </sec:authorize>
    </div>
</div>