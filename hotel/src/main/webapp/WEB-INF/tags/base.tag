<%@tag description="Base page tag" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Java Hotel</title>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.slim.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/popper.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/custom.css">
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="header">
        <t:navbar/>
    </div>
    <main role="main" class="container">
        <c:if test="${error != null}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>

        <jsp:doBody/>
    </main>
</body>
</html>
