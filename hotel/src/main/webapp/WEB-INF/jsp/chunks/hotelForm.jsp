<%--
  Created by IntelliJ IDEA.
  User: vvasyuk42
  Date: 2019-06-01
  Time: 02:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="post" action="/admin/create-hotel" modelAttribute="hotel">
    <h1>Add a new hotel</h1>
    <div class="form-group">
        <form:label path="name">Name</form:label>
        <form:errors path="name" class="alert-danger"/>
        <form:input type="text" class="form-control" path="name" placeholder="Enter name"/>
    </div>
    <div class="form-group">
        <form:label path="country">Country</form:label>
        <form:errors path="country" class="alert-danger"/>
        <form:input type="text" class="form-control" path="country" placeholder="Enter country"/>
    </div>
    <div class="form-group">
        <form:label path="city">City</form:label>
        <form:errors path="city" class="alert-danger"/>
        <form:input type="text" class="form-control" path="city" placeholder="Enter city"/>
    </div>
    <div class="form-group">
        <form:label path="address">Address</form:label>
        <form:errors path="address" class="alert-danger"/>
        <form:input type="text" class="form-control" path="address" placeholder="Enter address"/>
    </div>
    <div class="form-group">
        <form:label path="rating">Star rating (0-5)</form:label>
        <form:errors path="rating" class="alert-danger"/>
        <form:input type="number" class="form-control" path="rating" placeholder="Enter rating"/>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form:form>