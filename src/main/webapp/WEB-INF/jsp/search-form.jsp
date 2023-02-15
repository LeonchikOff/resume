<%@ page import="org.slf4j.LoggerFactory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%
    LoggerFactory.getLogger("search-form.jsp").debug("Display search-form.jsp");
%>
<html>
<body>
<h2>Input name</h2>
<c:if test="${requestScope.invalid}">
    <h5 style="color: red;">Please, input correct value.</h5>
</c:if>
    <form action="/search" method="post">
        <label>
            <input name="query">
            <input type="submit" value="Search"/>
        </label>
    </form>
</body>
</html>
