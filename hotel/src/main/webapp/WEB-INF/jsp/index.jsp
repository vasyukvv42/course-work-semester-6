<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-05-31
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:base>
    <div class="jumbotron">
        <h1>
            Hello,
            <sec:authorize access="!isAuthenticated()">
                guest
            </sec:authorize>
            <sec:authorize access="isAuthenticated()"><sec:authentication property="principal.username"/></sec:authorize>
            !
        </h1>
        <sec:authorize access="hasAuthority('CLIENT')">
            <br>
            <h3>Current balance: ${balance.balance}$</h3>
            <h3>Refill balance</h3>
            <form:form class="form-inline" method="post" action="/refill" modelAttribute="balance">
                <form:errors path="amount" class="alert-danger"/>
                <div class="input-group mb-2 mr-sm-2">
                    <form:input type="number" class="form-control" path="amount" placeholder="00.00"/>
                    <div class="input-group-append">
                        <div class="input-group-text">$</div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary mb-2">Refill</button>
            </form:form>
        </sec:authorize>
    </div>
</t:base>
