<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/dataTables.bootstrap5.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script src="https://momentjs.com/downloads/moment-with-locales.js"></script>
<script>
    async function alertSuccess(text) {
        try {
            let result = await swal({
                title: 'Success!',
                text: text,
                icon: "success",
                buttonsStyling: false,
                customClass: {
                    confirmButton: "btn fw-bold btn-primary"
                },
            });
            return !!(result && result === true);
        } catch (error) {
            console.error(error);
        }
    }
    async function swalErrorRes(res) {
        if (res.status >= 400 && res.status <= 499) {
            switch (res.status) {
                case 400:
                    await alertError(
                        `Client Error, Sorry, looks like there are some errors detected, please try again.`)
                    break;

                default:
                    const msg = res.responseJSON.message ?? "please check !";
                    await alertError(`Client Error, \${msg}`)
                    break;
            }
        }
        if ((res.status >= 500 && res.status <= 599)) {
            await alertError(
                `Something went wrong,Looks like something went wrong, whatever it was it might be our fault. please try again or contact technical team.`
            )
        }
    }
    async function alertError(text) {
        try {
            let result = await swal({
                text: text,
                icon: "error",
                buttonsStyling: false,
                confirmButtonText: "Ok, got it!",
                customClass: {
                    confirmButton: "btn fw-bold btn-primary"
                },
            });
            return !!(result && result === true);
        } catch (error) {
            console.error(error);
        }
    }
    async function alertConfirm(text, confirmText) {
        try {
            let result = await swal({
                text: text,
                icon: "warning",
                buttons: {
                    cancel: {
                        text: "Cancel",
                        value: false,
                        visible: true,
                        className: "",
                        closeModal: true,
                    },
                    confirm: {
                        text: confirmText,
                        value: true,
                        visible: true,
                        className: "",
                        closeModal: true,
                    },
                },
            });
            return !!(result && result === true);
        } catch (error) {
            console.error(error);
        }
    }
</script>