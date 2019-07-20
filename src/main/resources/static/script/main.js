let modal = $('#Modal');
let modalTitle = $('#Modal #ModalLabel');
let modalBody = $('#Modal .modal-body');
let modalFooter = $('#Modal .modal-footer');

let infoModal = $('#infoModal');
let infoModalTitle = $('#infoModal .modal-title');
let infoModalBody = $('#infoModal .modal-body p');
let infoModalIco = $('#infoModal .material-icons');
let infoModalButton = $('#infoModal .modal-confirm .btn');
let infoModalIcoBackground = $('#infoModal .modal-confirm .icon-box');


function showModal(type,title,text){
    infoModalTitle.text(title);
    infoModalBody.text(text);
    if(type === "success"){
        infoModalIco.html("&#xE876;");
        infoModalButton.css('background','#82ce34');
        infoModalIcoBackground.css('background','#82ce34');

    } else if (type === "error"){
        infoModalIco.html("&#xE5CD;");
        infoModalButton.css('background','#ef513a');
        infoModalIcoBackground.css('background','#ef513a');

    } else if (type === "info"){
        infoModalIco.html("&#xE001;");
        infoModalButton.css('background','blue');
        infoModalIcoBackground.css('background','blue');
    }
    infoModal.modal();
}



$('#Modal, #infoModal').on('hidden.bs.modal', function (e) {
    modalTitle.text("");
    modalBody.text("");
    modalFooter.text("");
});