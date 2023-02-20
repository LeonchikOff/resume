<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="panel panel-primary">
    <div class="panel-body">
        <a href="/edit">
        <img src="<c:url value="/media/avatar/1f888305-b95d-40ed-87f8-7d04488289a9.jpg"/>"
             class="img-responsive photo" alt="photo"/>
        </a>
        <h1 class="text-center"><a href="/edit" style="color: #0f0f0f;"/>${fullName}</h1>
        <h6 class="text-center"><strong>Odessa, Ukraine</strong></h6>
        <h6 class="text-center"><strong>Age: </strong>27, <strong>Birthday: </strong>Feb 26,1989</h6>
        <div class="list-group contacts">
            <a class="list-group-item" href="tel:+79981541213"><i class="fa fa-phone"></i> +79981541213</a>
            <a class="list-group-item" href="mailto:richard-hendricks@gmail.com"><i class="fa fa-envelope"></i> richard-hendricks@gmail.com</a>
            <a class="list-group-item" href="javascript:void(0);"><i class="fa fa-skype"></i> richard-hendricks</a>
            <a class="list-group-item" target="_blank" href="https://vk.com/richard-hendricks"><i class="fa fa-vk"></i> https://vk.com/richard-hendricks</a>
            <a class="list-group-item" target="_blank" href="https://facebook.com/richard-hendricks"><i class="fa fa fa-facebook"></i> https://facebook.com/richard-hendricks</a>
            <a class="list-group-item" target="_blank" href="https://linkedin.com/richard-hendricks"><i class="fa fa-linkedin"></i> https://linkedin.com/richard-hendricks</a>
            <a class="list-group-item" target="_blank" href="https://github.com/richard-hendricks"><i class="fa fa-github"></i> https://github.com/richard-hendricks</a>
            <a class="list-group-item" target="_blank" href="https://stackoverflow.com/richard-hendricks"><i class="fa fa-stack-overflow"></i> https://stackoverflow.com/richard-hendricks</a>
        </div>
    </div>
</div>