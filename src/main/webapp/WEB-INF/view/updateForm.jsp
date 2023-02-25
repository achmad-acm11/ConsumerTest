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

    <title>Salt Test - Update Form</title>
</head>

<body>
<%@include file="include/navbar.jsp" %>
<div class="container mt-4">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <p class="h3">Update Consumer</p>
                <form id="update_form" method="post">
                    <div class="mb-3">
                        <label for="name" class="form-label">Nama</label>
                        <input type="text" class="form-control" id="name" name="name" value="${consumer.name}">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Alamat</label>
                        <textarea class="form-control" id="address" rows="3" name="address">${consumer.address}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="city" class="form-label">Kota</label>
                        <input type="text" class="form-control" id="city" name="city" value="${consumer.city}">
                    </div>
                    <div class="mb-3">
                        <label for="province" class="form-label">Provinsi</label>
                        <input type="text" class="form-control" id="province" name="province" value="${consumer.province}">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Status</label>
                        <div class="form-check form-switch">
                            <input class="form-check-input" type="checkbox" id="status" name="status" ${consumer.status.toString() == 'AKTIF' ? 'checked' : ''}>
                            <label class="form-check-label" for="status">Status of Consumer</label>
                        </div>
                    </div>
                    <button type="submit" data-id="${consumer.id}" onclick="update_action(this)" class="btn btn-primary">Submit</button>
                    <a href="/consumer" class="btn btn-secondary">Back</a>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="include/script.jsp" %>
<script>
    async function update_action(e) {
        event.preventDefault();
        const id = $(e).data("id");
        const url_form = `/consumer/update/\${id}`;
        const form = $('#update_form');
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
