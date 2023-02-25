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
                <a href="/consumer/add" class="btn btn-primary mb-3">Create New</a>
                <table class="table table-striped" id="example">
                    <thead>
                        <tr>
                            <th scope="col">Nama</th>
                            <th scope="col">Alamat</th>
                            <th scope="col">Kota</th>
                            <th scope="col">Provinsi</th>
                            <th scope="col">Tgl Registrasi</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
<%--                    <tbody>--%>
<%--                        <tr>--%>
<%--                            <th scope="row">1</th>--%>
<%--                            <td>Mark</td>--%>
<%--                            <td>Otto</td>--%>
<%--                            <td>@mdo</td>--%>
<%--                            <td>@mdo</td>--%>
<%--                            <td>@mdo</td>--%>
<%--                            <td>@mdo</td>--%>
<%--                            <td>--%>
<%--                                <div class="btn-group" role="group">--%>
<%--                                    <button id="btnGroupDrop1" type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">--%>
<%--                                        Action--%>
<%--                                    </button>--%>
<%--                                    <ul class="dropdown-menu" aria-labelledby="btnGroupDrop1">--%>
<%--                                        <li><a class="dropdown-item" href="/consumer/edit">Update</a></li>--%>
<%--                                        <li><button class="dropdown-item"  data-id="1" onclick="delete_action(this)">Delete</button></li>--%>
<%--                                    </ul>--%>
<%--                                </div>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                    </tbody>--%>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="include/script.jsp" %>
<script>
    $(document).ready(function () {
        tableData = $('#example').DataTable({
            responsive: true,
            searchDelay: 500,
            serverSide: true,
            ajax: {
                url: `/consumer/datatable`,
                type: 'POST',
                data: {
                },
                error: async function(xhr, error, code) {
                    await swalErrorRes(error);
                }
            },
            columns: [
                {
                    data: "name",
                    orderable: false,
                    render: function(data, type, row) {
                        return data;
                    }
                },
                {
                    data: "address",
                    orderable: false,
                    render: function(data, type, row) {
                        return data;
                    }
                },
                {
                    data: "city",
                    orderable: false,
                    render: function(data, type, row) {
                        return data;
                    }
                },
                {
                    data: "province",
                    orderable: false,
                    render: function(data, type, row) {
                        return data;
                    }
                },
                {
                    data: "registration_date",
                    orderable: false,
                    render: function(data, type, row) {
                        moment.locale('id');
                        return moment(data).format('LLL');
                    }
                },
                {
                    data: "status",
                    orderable: false,
                    render: function(data, type, row) {
                        let result = "";
                        if (data === "AKTIF"){
                            result = `<span class="badge rounded-pill bg-success">Active</span>`;
                        }else{
                            result = `<span class="badge rounded-pill bg-secondary">Non Active</span>`;
                        }
                        return result;
                    }
                },
                {
                    data: 'id',
                    responsivePriority: -1
                },
            ],
            columnDefs: [{
                targets: -1,
                className: "text-end",
                title: 'Actions',
                orderable: false,
                render: function(data, type, row) {
                    return `
                        <div class="btn-group" role="group">
                                    <button id="btnGroupDrop1" type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                        Action
                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                                        <li><a class="dropdown-item" href="/consumer/edit/\${data}">Update</a></li>
                                        <li><button class="dropdown-item"  data-id="\${data}" onclick="delete_action(this)">Delete</button></li>
                                    </ul>
                                </div>
                    `;
                },
            }],
        });
    });
    async function delete_action(e) {
        const id = $(e).data("id");
        const url_form = `/consumer/delete/\${id}`;
        const resConfirm = await alertConfirm("Are you sure for delete this data ?", "Yes, delete !");
        if (resConfirm){
            const res = $.ajax({
                method: "POST",
                data: {
                },
                processData: false,
                contentType: false,
                dataType: 'JSON',
                url: url_form,
                async: false,
            });
            if (res.status === 200) {
                if (await alertSuccess(res.responseJSON.message)){
                    window.location.href = "/consumer";
                }
            }
            await swalErrorRes(res);
        }
    }
    const staff_table = document.querySelector("#kt_staff_table");
</script>
</body>

</html>