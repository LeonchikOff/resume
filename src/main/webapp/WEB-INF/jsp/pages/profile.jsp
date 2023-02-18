<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags" %>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-sm-6">
            <resume:profile-main/>
            <resume:profile-languages/>
            <resume:profile-hobbies/>
            <resume:profile-info/>
        </div>
        <div class="col-md-8 col-sm-6">
            <resume:profile-objectives/>
            <resume:profile-skills/>
            <resume:profile-practices/>
            <resume:profile-certificates/>
            <resume:profile-courses/>
            <resume:profile-educations/>
        </div>
    </div>
</div>

