<%--
  Created by IntelliJ IDEA.
  User: macbookair
  Date: 24/02/23
  Time: 15.48
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%@include file="include/css.jsp" %>

    <title>Salt Test</title>
</head>

<body>
<%@include file="include/navbar.jsp" %>
<div class="container mt-4">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <div class="text-center">
                    <h1 class="display-1 fw-bold">404</h1>
                    <p class="fs-3"> <span class="text-danger">Opps!</span> Page not found.</p>
                    <p class="lead">
                        The page you're looking for doesn't exist.
                    </p>
                    <a href="/consumer" class="btn btn-primary">Go Home</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="include/script.jsp" %>
</body>

</html>