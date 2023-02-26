<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<div class="panel panel-info small-center-block">
    <div class="panel-heading">
        <h3 class="panel-title">
            <i class="fa fa-sign-in"></i> Login to your personal account</h3>
    </div>
    <div class="panel-body">
        <form action="/sign-in-handler" method="post">
            <c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION != null}">
                <div class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                        ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
                    <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
                </div>
            </c:if>
            <div class="help-block">You can use your uid, email or phone number as a login</div>
            <div class="form-group">
                <label for="uid">Login</label>
                <input id="uid" name="uid" class="form-control" placeholder="UID, Email or Phone number" required
                       autofocus/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" class="form-control" type="password" placeholder="Password"
                       required/>
            </div>
            <div class="form-group">
                <label>
                    <input type="checkbox" name="remember-me" value="true">Remember me</label>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary pull-left">Login</button>
                <a href="#" class="pull-right">Restore access</a>
            </div>
        </form>
    </div>
</div>
