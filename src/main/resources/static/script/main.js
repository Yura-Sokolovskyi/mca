let modal = $('#myModal');
let modalTitle = $('#myModalLabel');
let modalBody = $('.modal-body');
let modalFooter = $('.modal-footer');


$('#myModal').on('hidden.bs.modal', function (e) {
    modalTitle.text("");
    modalBody.text("");
    modalFooter.text("");
});