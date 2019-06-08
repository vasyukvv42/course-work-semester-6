<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-06-01
  Time: 00:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form action="/signup" class="form-signup" method="post" modelAttribute="user">
    <h1 class="h3 mb-3 font-weight-normal">Sign up</h1>

    <form:label class="sr-only" path="username">Username</form:label>
    <form:input class="form-control" placeholder="Username" path="username"/>
    <form:errors path="username" class="alert-danger"/>

    <form:label class="sr-only" path="email">Email address</form:label>
    <form:input class="form-control" placeholder="Email address" path="email"/>
    <form:errors path="email" class="alert-danger"/>

    <form:label class="sr-only" path="password">Password</form:label>
    <form:input type="password" class="form-control" placeholder="Password" path="password"/>
    <form:errors path="password" class="alert-danger"/>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
</form:form>
