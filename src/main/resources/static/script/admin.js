
// let modal = $('#myModal');
// let modalTitle = $('#myModalLabel');
// let modalBody = $('.modal-body');
// let modalFooter = $('.modal-footer');
let adminMenuItems = $('.sc-admin-menu-item');


buildAdminCategoryPage();
loadCategoryPage();

adminMenuItems.click(()=>{

});

$(document).on('click','.sc-admin-category-img',()=>{
$('#sc-admin-category-img').click();
});

$(document).on('change','#sc-admin-category-img',()=>{
    let file = $('#sc-admin-category-img')[0].files[0];
    let reader = new FileReader();
    reader.onloadend = function () {
        $('.sc-admin-category-img').css('background-image', 'url("' + reader.result + '")');
        $('.sc-admin-category-img').data("name", file.name.split('.')[0]);
    }
    if (file) {
        reader.readAsDataURL(file);
    } else {
    }
});

$(document).on('click','.sc-admin-delete-button',(e)=>{
    let targetElement = $(e.target.parentElement);
    let id = targetElement.data("id");
    let type = targetElement.data("type");
    console.log(id, type);
    removeFromBD(id, type, targetElement);
});

$(document).on('click','.add-button',()=>{
    let categoryInfo = {
        'name': "default",
        'color': "#000000",
        'image': "images/default.jpg"
    };
    modalTitle.text("Add category");
    $('#adminCategoryModal').tmpl(categoryInfo).appendTo(modalBody);

    modalFooter.append(`
                <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                <button data-id="" type="button" class="btn btn-danger save-category-changes">Save</button>
                `);
    modal.modal();
    editColor();
});

$(document).on('click','.sc-admin-edit-button',(e)=>{
    let parentTr = $(e.target).closest('tr');
    let categoryInfo = {
        'id' : parentTr.find('.sc-admin-category-id').html(),
        'name': parentTr.find('.sc-admin-category-name').html(),
        'color': parentTr.find('.sc-admin-category-color-indicator').css('background-color'),
        'image': parentTr.find('.sc-admin-category-image-indicator').css('background-image')
                .replace('url(','').replace(')','').replace(/\"/gi, "")
    };
    modalTitle.text("Edit category");
    $('#adminCategoryModal').tmpl(categoryInfo).appendTo(modalBody);

    modalFooter.append(`
                <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                <button data-id="${categoryInfo.id}" type="button" class="btn btn-danger save-category-changes">Save</button>
                `);

    modal.modal();
    editColor();
});

$(document).on('click','.save-category-changes', ()=>{
    editCategory();
});

$(document).on('click','.delete-product-by-category', (e)=>{
    console.log('click');
    $('#myModal').modal('hide');
    let categoryId = $(e.target).data("category");
    deleteProductByCategory(categoryId);
});

function editColor(){

    $('.sc-admin-category-color').colorpicker();

    $('.sc-admin-category-color').on('changeColor',(e)=>{
        $('.sc-admin-category-color').css('background-color',e.color.toString());
    });
}

function appendCategory(category) {
    $('#adminCategoryTemplate').tmpl(category).appendTo('.sc-admin-right-block tbody');
}

function buildAdminCategoryPage() {
    $('#adminPageHeader').tmpl().appendTo('.sc-admin-right-block');
}

function editCategory() {
    let id =$('.save-category-changes').data("id");
    var file = document.getElementById("sc-admin-category-img").files[0];
    getBase64(file).then(data => {
        //work with data as src of file
        let request = {
            name: $('#sc-admin-modal-category-name').val(),
            color: $('.sc-admin-category-color').css('background-color'),
            data: data,
            fileName: $('.sc-admin-category-img').data("name")
        };
        if (id === '') {
            $.ajax({
                url: 'http://localhost:8080/category',
                contentType: 'application/json',
                type: 'post',
                data: JSON.stringify(request),
                success: function (response) {
                    console.log('post', response);
                    modal.modal('hide');
                    location.reload();
                }
            });
        } else {
            $.ajax({
                url: 'http://localhost:8080/category?id=' + id,
                type: 'put',
                data: JSON.stringify(request),
                success: function (response) {
                    console.log('put', response);
                    modal.modal('hide');
                    location.reload();
                }
            });
        }
    });
}

function removeFromBD(id, type, targetElement) {
    if (type === "category"){
        $.ajax({
            url: 'http://localhost:8080/category?id=' + id,
            type: 'delete',
            success:function(){
                console.log(targetElement);
                targetElement.closest("tr").slideUp();
            },
            error: function(err) {
                let answer = jQuery.parseJSON(err.responseText);
                modalTitle.text('Warning');
                modalBody.text(answer.message);
                modalFooter.append(`
                <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                <button data-category="${id}" type="button" class="btn btn-danger delete-product-by-category">Delete</button>
                `);
                modal.modal();
            }
        });
    }


}

function update(id, type) {
    if (type === "category"){
        $.ajax({
            url: 'http://localhost:8080/category?id=' + id,
            type: 'delete',
            error: function(err) {
                let answer = jQuery.parseJSON(err.responseText);
                modalTitle.text('Warning');
                modalBody.text(answer.message);
                modalFooter.append(`
                <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                <button data-category="${id}" type="button" class="btn btn-danger delete-product-by-category">Delete</button>
                `);
                modal.modal();
            }
        });
    }
}

function deleteProductByCategory(id) {
    console.log(id);
    $.ajax({
        url: 'http://localhost:8080/product/deleteByCategory?id=' + id,
        type: 'delete',
        success: function () {
            clearModal();
        }
    });
}

function loadCategoryPage() {
    $.ajax({
        url: 'http://localhost:8080/category',
        type: 'get',
        success: function (response) {
            for (let category of response) {
                appendCategory(category);
            }

            $('.sc-admin-right-block tbody tr').fadeIn();
        }
    });
}

// $('#myModal').on('hidden.bs.modal', function (e) {
//     modalTitle.text("");
//     modalBody.text("");
//     modalFooter.text("");
// });

function getBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });
}


