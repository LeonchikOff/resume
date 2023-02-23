<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div class="panel panel-default">
    <div class="panel-body">
        <h4 class="data-header">Technical skills in the field of framework and technology</h4>
        <hr/>
        <div class="row" style="margin-bottom: 20px;">
            <div class="col-xs-5 col-sm-4 col-md-2 text-center"><strong>Category</strong></div>
            <div class="col-xs-7 col-sm-8 col-md-10 text-center"><strong>Framework and Technology</strong></div>
        </div>
        <form:form action="/edit/skills" method="post" commandName="skillForm">
            <div id="ui-block-container">
                <c:forEach var="skill" items="${skillForm.items}" varStatus="status">
                    <resume:edit-skill-block index="${status.index}" skill="${skill}"/>
                </c:forEach>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <a href="javascript:void(0);">+ Add skill</a>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-xs-12 text-center">
                    <input type="submit" class="btn btn-primary" value="Save"/>
                </div>
            </div>
        </form:form>
    </div>
</div>

