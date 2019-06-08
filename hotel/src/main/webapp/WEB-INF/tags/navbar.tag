<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-05-31
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="Navbar tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Java Hotel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/"/>">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/hotels"/>">Hotels</a>
            </li>
            <sec:authorize access="hasAuthority('CLIENT')">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/requests"/>">My Requests</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/reservations"/>">My Reservations</a>
                </li>
            </sec:authorize>

            <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/admin/requests"/>">Pending Requests</a>
                </li>
            </sec:authorize>
        </ul>

        <sec:authorize access="!isAuthenticated()">
            <a class="btn btn-outline-secondary my-2 my-sm-0" href="<c:url value="/signup"/>">Sign up</a>
            <a class="btn btn-outline-success my-2 my-sm-0" href="<c:url value="/login"/>">Log in</a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a class="btn btn-outline-danger my-2 my-sm-0" href="<c:url value="/logout"/>">Log out</a>
        </sec:authorize>
    </div>
</nav>

