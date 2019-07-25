

$(document).ready(function () {
    buildAdminOrderPage(null,null,null,0,12,"createDateTime","ASC");
});

let adminMenuItems = $('.sc-admin-menu-item');


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

$(document).on('click','.sc-admin-product-img',()=>{
    $('#sc-admin-product-img').click();
});

$(document).on('change','#sc-admin-category-img',()=>{
    let file = $('#sc-admin-product-img')[0].files[0];
    let reader = new FileReader();
    reader.onloadend = function () {
        $('.sc-admin-product-img').css('background-image', 'url("' + reader.result + '")');
        $('.sc-admin-product-img').data("name", file.name.split('.')[0]);
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

$(document).on('click','.category-add-button',()=>{
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

$(document).on('click','.product-add-button',()=>{
    let product = {
        "name": "default",
        "price": "0.0",
        "info": "info",
        "image": "images/default.jpg"
    };
    modalTitle.text("Add product");
    $('#adminProductModal').tmpl(product).appendTo(modalBody);

    modalFooter.append(`
                <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                <button data-id="" type="button" class="btn btn-danger save-product-changes">Save</button>
                `);
    modal.modal();
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

$(document).on('click','.sc-admin-menu-item', (e)=>{
    $('.sc-admin-menu-item').removeClass('active');
    $(e.target).addClass('active');
    let target = $(e.target).data('target');
    if(target === "category"){
        buildAdminCategoryPage();
    } else if (target === "orders"){
        buildAdminOrderPage(null,null,null,0,12,"createDateTime","ASC");
    } else if (target === "products"){
        buildAdminProductPage(null,null,0,12,"name","ASC");
    } else if (target === "users"){
        buildAdminUserPage(null,null,null,0,12,"name","ASC");
    }else if (target === "ingredients"){
        buildAdminIngredientPage(null,0,12,"name","ASC");
    }
});


function buildAdminCategoryPage() {
    clearRightBlock();
    $('#adminPageCategoryHeader').tmpl().appendTo('.sc-admin-right-block');
    $.ajax({
        url: 'http://localhost:8080/category',
        type: 'get',
        success: function (response) {
            for (let category of response) {
                $('#adminCategoryTemplate').tmpl(category).appendTo('.sc-admin-right-block tbody');
            }

            $('.sc-admin-right-block tbody tr').fadeIn();
        }
    });
}
function buildAdminOrderPage(name, date, status,page, size, sortingField, direction) {
    clearRightBlock();
    $('#adminPageOrderHeader').tmpl().appendTo('.sc-admin-right-block');

    let paginationRequest = {
        "direction": direction,
        "field": sortingField,
        "page": page,
        "size": size
    };

    let request = {
        "date": date,
        "name": name,
        "paginationRequest": paginationRequest,
        "status": status

    };

    $.ajax({
        url: 'http://localhost:8080/order/find-by-filter',
        contentType: 'application/json',
        type: 'post',
        data: JSON.stringify(request),
        success: function (response) {
            for (let order of response) {

                checkStatus(order);

                $('#adminOrderTemplate').tmpl(order).appendTo('.sc-admin-right-block tbody');
            }

            $('.sc-admin-right-block tbody tr').fadeIn();
        }
    });

}
function buildAdminProductPage(name, categoryId, page, size, sortingField, direction) {
    clearRightBlock();
    $('#adminPageProductsHeader').tmpl().appendTo('.sc-admin-right-block');

    let paginationRequest = {
        "direction": direction,
        "field": sortingField,
        "page": page,
        "size": size
    };

    let request = {
        "categoryId": categoryId,
        "name": name,
        "paginationRequest": paginationRequest

    };

    $.ajax({
        url: 'http://localhost:8080/product/find-by-filter',
        contentType: 'application/json',
        type: 'post',
        data: JSON.stringify(request),
        success: function (response) {
            for (let product of response) {

                $('#adminProductsTemplate').tmpl(product).appendTo('.sc-admin-right-block tbody');
            }

            $('.sc-admin-right-block tbody tr').fadeIn();
        }
    });

}
function buildAdminUserPage(name, phone, role, page, size, sortingField, direction) {
    clearRightBlock();
    $('#adminPageUserHeader').tmpl().appendTo('.sc-admin-right-block');

    let paginationRequest = {
        "direction": direction,
        "field": sortingField,
        "page": page,
        "size": size
    };

    let request = {
        "phoneNumber": phone,
        "name": name,
        "role": role,
        "paginationRequest": paginationRequest
    };

    $.ajax({
        url: 'http://localhost:8080/user/admin/findByFilter',
        contentType: 'application/json',
        type: 'post',
        data: JSON.stringify(request),
        success: function (response) {
            for (let user of response) {
                checkActive(user);
                $('#adminUserTemplate').tmpl(user).appendTo('.sc-admin-right-block tbody');
            }

            $('.sc-admin-right-block tbody tr').fadeIn();
        }
    });

}
function buildAdminIngredientPage(name, page, size, sortingField, direction) {
    clearRightBlock();
    $('#adminPageIngredientHeader').tmpl().appendTo('.sc-admin-right-block');

    let paginationRequest = {
        "direction": direction,
        "field": sortingField,
        "page": page,
        "size": size
    };

    let request = {
        "name": name,
        "paginationRequest": paginationRequest
    };

    $.ajax({
        url: 'http://localhost:8080/ingredient',
        contentType: 'application/json',
        type: 'post',
        data: JSON.stringify(request),
        success: function (response) {
            for (let ingredient of response) {
                $('#adminIngredientTemplate').tmpl(ingredient).appendTo('.sc-admin-right-block tbody');
            }

            $('.sc-admin-right-block tbody tr').fadeIn();
        }
    });

}
function checkStatus(order) {
    if (order.status){
        order.status = "finished";
        order.color = "limegreen";
    } else {
        order.status = "unfinished";
        order.color = "#f44336";
    }

}
function checkActive(user) {
    if (user.active){
        user.color = "limegreen";
    } else {
        user.color = "#f44336";
    }

}

function clearRightBlock() {
    $('.sc-admin-right-block').empty();
}

function editColor(){

    $('.sc-admin-category-color').colorpicker();

    $('.sc-admin-category-color').on('changeColor',(e)=>{
        $('.sc-admin-category-color').css('background-color',e.color.toString());
    });
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


function getBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });
}


