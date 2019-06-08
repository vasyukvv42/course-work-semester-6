<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-06-01
  Time: 04:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:base>
    <c:forEach items="${requests}" var="x">
        <t:roomRequestCard roomRequest="${x}" suitableRooms="${suitableRooms}"/>
        <br>
    </c:forEach>
</t:base>
