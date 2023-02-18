<%--suppress XmlUnboundNsPrefix --%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<!doctype html>
<html lang="en">
<head>
    <title>Resume</title>
    <jsp:include page="../jsp/sections/meta.jsp"/>
    <jsp:include page="../jsp/sections/css.jsp"/>
</head>
<body>
<jsp:include page="../jsp/sections/header.jsp"/>
<jsp:include page="../jsp/sections/navigation.jsp"/>
<section class="main">
    <sitemesh:write property="body"/>
</section>
<jsp:include page="../jsp/sections/footer.jsp"/>
<jsp:include page="../jsp/sections/js.jsp"/>
</body>
</html>