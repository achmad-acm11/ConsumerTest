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

    <title>Salt Test - Create Form</title>
</head>

<body>
<%@include file="include/navbar.jsp" %>
<div class="container mt-4">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <p class="h3">Create Consumer</p>
                <form id="add_form" method="post">
                    <div class="mb-3">
                        <label for="name" class="form-label">Nama <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Alamat</label>
                        <textarea class="form-control" id="address" rows="3" name="address"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="city" class="form-label">Kota <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="city" name="city" required>
                    </div>
                    <div class="mb-3">
                        <label for="province" class="form-label">Provinsi <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="province" name="province" required>
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="add_action()">Submit</button>
                    <a href="/consumer" class="btn btn-secondary">Back</a>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="include/script.jsp" %>
<script>
    async function add_action() {
        event.preventDefault();
        const url_form = '/consumer/create';
        const form = $('#add_form');
        const formData = new FormData(form[0]);
        var object = {};
        formData.forEach((value, key) => object[key] = value);
        var json = JSON.stringify(object);
        const res = $.ajax({
            method: "POST",
            url: url_form,
            data: json,
            contentType: "application/json",
            dataType: 'JSON',
            async: false,
        });
        if (res.status === 200) {
            if (await alertSuccess(res.responseJSON.message)){
                window.location.href = "/consumer";
            }
        }
        await swalErrorRes(res);
    }
</script>
</body>

</html>
