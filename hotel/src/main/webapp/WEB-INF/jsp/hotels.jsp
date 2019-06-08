<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-06-01
  Time: 02:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:base>
    <sec:authorize access="hasAuthority('ADMINISTRATOR')">
        <div class="jumbotron">
            <%@include file="chunks/hotelForm.jsp"%>
        </div>
    </sec:authorize>

    <c:forEach items="${hotels}" var="x">
        <t:hotelCard hotel="${x}"/>
        <br>
    </c:forEach>
</t:base>
