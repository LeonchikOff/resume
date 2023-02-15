<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%
    LoggerFactory.getLogger("search-result.jsp").debug("Display search-result.jsp");
%>
<html>
<body>
    <h2>Your name: ${requestScope.name}</h2>
    <br/>
    <a href="/search">Try again</a>
</body>
</html>
