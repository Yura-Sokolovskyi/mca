/**
 * Created by Yura on 25.06.2019.
 */
$(document).ready(function(){
    appendCategoryToMenu();
    getUsersForSelect();
});


$(document).on('click', '.sc-order-product-item-quantity-button.minus',(e)=>{
    let request = {
        "count": 1,
        "productId": $(e.target).closest('.sc-order-product-item-container').data("id"),
        "userId": $('.sc-client-select-title').data("id"),
        "sign": "-"
    };
    $.ajax({
        url: 'http://localhost:8080/product-count',
        contentType: 'application/json',
        type: 'put',
        data: JSON.stringify(request),
        success: function (response) {
            console.log(response);
            addProductToCart(response);

        }
    });

    //
    // changeItemQuantity("minus", $(e.target).closest('.sc-order-product-item-container').data("id"));
});

$(document).on('click', '.sc-order-product-item-quantity-button.plus',(e)=>{

    let request = {
        "count": 1,
        "productId": $(e.target).closest('.sc-order-product-item-container').data("id"),
        "userId": $('.sc-client-select-title').data("id"),
        "sign": "+"

    };

    $.ajax({
        url: 'http://localhost:8080/product-count',
        contentType: 'application/json',
        type: 'put',
        data: JSON.stringify(request),
        success: function (response) {
            addProductToCart(response);
        }
    });

    //changeItemQuantity("plus", $(e.target).closest('.sc-order-product-item-container').data("id"));
});

$(document).on('click', '.sc-order-product-item-remove-button',(e)=>{
    let userId = $('.sc-client-select-title').data("id");
    let productId = $(e.target).closest('.sc-order-product-item-container').data("id");

    $.ajax({
        url: 'http://localhost:8080/product-count?productId=' + productId + '&userId=' + userId,
        type: 'DELETE',
        success: function () {
            $(e.target).closest('.sc-order-product-item-container').remove();
            calculateOrderSum();
        }
    });
});

$(document).on('click', '.sc-nav-back-button',()=>{
    $('.nav-info-container').css('background-color','#ff000000');
    $('.sc-category-info').text("SELECT CATEGORY");
    productMenuClose();
    appendCategoryToMenu();
});

$(document).on('click', '.sc-client-select',()=>{
    if( $( ".sc-client-select-option-container" ).css('display') === 'none') {
        openSelect();
    } else {
        closeSelect();
    }
});

$(document).on("click", ".sc-client-select-option", function(){
    $('.sc-client-select-title').val($(this).text());
    $('.sc-client-select-title').data("id",$(this).data("id"));

    closeSelect();
});

$(document).on("click", ".product-menu-item", function(e){
    console.log("product:" + $(e.target).data("id"));
    console.log("user:" + $('.sc-client-select-title').data("id"));
    let request = {
        "count": 1,
        "productId": $(e.target).closest(".product-menu-item").data("id"),
        "sign": "+",
        "userId": $('.sc-client-select-title').data("id")
    };

    $.ajax({
        url: 'http://localhost:8080/product-count',
        contentType: 'application/json',
        type: 'put',
        data: JSON.stringify(request),
        success: function (response) {
            addProductToCart(response);
            console.log('post', response);
        }
    });
});




function getUsersForSelect() {

    let paginationRequest = {
        direction: "ASC",
        field: "name",
        page: 0,
        size: 20
    };
    let request = {
        role: "CLIENT",
        "paginationRequest": paginationRequest
    };

        $.ajax({
            url: 'http://localhost:8080/user/findByFilter',
            contentType: 'application/json',
            type: 'post',
            data: JSON.stringify(request),
            success: function (response) {
                console.log('post', response);
                for (let user of response){
                    addOptionToUserSelect(user);
                }
            }
        })

}

function appendCategoryToMenu() {
    $('.sc-category-menu-item').remove();
    $.ajax({
        url: 'http://localhost:8080/category',
        type: 'get',
        success: function (response) {
            for (let category of response) {
                createCategory(category);
            }
            appendProductByCategory();
            gridViewChanger();
        }
    });

}

function createCategory(category) {
    $('.sc-category-menu-item-container').append(`<div class=" col-md-4 col-sm-6 sc-category-menu-item" ">
                <div data-id="${category.id}" class="sc-category-menu-item-content sc-flex-centred"  style="background-image: url(${"images/" + category.image})">
                 <div class="sc-item-title sc-flex-centred" style="background-color:${category.color}">${category.name}</div>

                   </div>
             </div>
        `);

}

function appendProductByCategory() {
   $('.sc-category-menu-item').click((e)=>{
       let $categoryContainer = $(e.target).closest('.sc-category-menu-item-content');
       let $id = $categoryContainer.data("id");
       console.log($id);
       let $categoryColor = $categoryContainer.children().css( "background-color");
       let $categoryName = $categoryContainer.text();
       $('.nav-info-container').css("background-color",$categoryColor);
       $('.sc-category-info').text($categoryName);
       productMenuOpen();

       console.log($categoryName);
       
       $('.sc-category-menu-item').remove();
       $.ajax({
                   url: 'http://localhost:8080/product?id=' + $id,
                   type: 'get',
                   success: function (response) {
                       for (let product of response) {
                           appendProductToMenu(product);
             }

             gridViewChanger();
            }
       });


    });
}

function appendProductToMenu(product) {
    $('.sc-category-menu-item-container').append(`
            <div class=" col-md-4 col-sm-6 sc-category-menu-item ">
                        <div class="sc-category-menu-item-content sc-flex-centred product-menu-item" data-id="${product.id}" style="background-image: url(${"images/" + product.image})">
                        <div class="sc-item-title sc-flex-centred" style="background-color: #8D6235">${product.name}  </div> <div class="sc-item-price sc-flex-centred"> ${product.price} <br> грн/шт</div>
                  </div>
            </div>
        `);
}

function addProductToCart(product) {
    let $cart = $('.sc-order-products-container');
    let $cartItemTemplate = $('#cartItem');

        let $product = {
            id: product.id,
            name: product.name ,
            priceDouble: product.price,
            count: product.count,
            price: doubleToStringsByDot(product.price)[0],
            priceCoin: doubleToStringsByDot(product.price)[1]
        };
        if(checkItemsInCart(product.id)){
            changeItemQuantity(product);
        } else {
            $($cartItemTemplate).tmpl($product).appendTo($cart);

        }
    calculateOrderSum();

}

function changeItemQuantity(product) {
    let existedItem = $(`.sc-order-products-container .sc-order-product-item-container[data-id=${product.id}]`);
    existedItem.data("price",`${product.price}`);
    existedItem.find(".sc-order-product-item-price-info span")
        .text(doubleToStringsByDot(`${product.price}`)[0])
        .append("<sup>"+ doubleToStringsByDot(`${product.price}`)[1] +"</sup>");
    existedItem.find(".sc-order-product-item-quantity-input").val(`${product.count}`);

}

function checkItemsInCart(id) {
    let flag = 0;
    let exist = false;
    $('.sc-order-product-item-container').each(function() {
         if( $(this).data("id") === id){
             flag++;
         }
    });
    if (flag>0){exist = true;}
    return exist;
}

function calculateOrderSum() {
    let sum=0;
    $('.sc-order-product-item-container').each(function() {
        sum += parseFloat($(this).data("price"));
    });
    console.log(sum);
    $('.sc-order-info-item-info').text(doubleToStringsByDot(sum)[0])
                                    .append("<sup>"+ doubleToStringsByDot(sum)[1] +"</sup>");

}

function doubleToStringsByDot($double) {
    let $strings = $double.toString().split(".")
     if ($strings[1] != null && $strings[1].length === 1){
         $strings[1] += "0";
    } else if ($strings[1] == null) {
         $strings[1] = "00";
    }
    return $strings;
}

/*--------- Category menu view ----------- */

function gridViewChanger() {
    var gridViewButton, listViewButton, categoryItemContainer, categoryItemTitle, categoryItemPrice;

    gridViewButton = $('.sc-view-button-grid');
    listViewButton = $('.sc-view-button-list');
    categoryItemContainer = $('.sc-category-menu-item-content');
    categoryItemTitle = $('.sc-item-title');
    categoryItemPrice  = $('.sc-item-price');


    gridViewButton.click(function () {
        gridViewButton.css('display','none');
        listViewButton.css('display','block');

        categoryItemContainer.css({
            height: 'auto',
            borderRadius: '0px'
        });

        categoryItemTitle.css({
            width: '100%',
            height: '60px'
        });

        categoryItemPrice.css({
            height: '60px'
        });
    });

    listViewButton.click(function () {
        listViewButton.css('display','none');
        gridViewButton.css('display','block');

        categoryItemContainer.css({
            height: '150px',
            borderRadius: '10px'
        });

        categoryItemTitle.css({
            width: '50%',
            height: '40%'
        });

        categoryItemPrice.css({
            height: '40%'
        });
    });
}

/*--------- Category menu view end ----------- */

function productMenuOpen() {
    $({deg: 0}).animate({deg: 180}, {
        duration: 300,
        step: function(now) {
            $(".sc-nav-back-button svg" ).css({
                transform: 'rotate(' + now + 'deg)'
            });
        }
    });
}

function productMenuClose() {
    $({deg: 180}).animate({deg: 0}, {
        duration: 300,
        step: function(now) {
            $(".sc-nav-back-button svg" ).css({
                transform: 'rotate(' + now + 'deg)'
            });
        }
    });
}

function addOptionToUserSelect(user) {
    $( ".sc-client-select-option-container" ).append(`<div class='sc-client-select-option' data-id="${user.id}">${user.name}</div>`);

}

function openSelect() {
    $(".sc-client-select-option-container").css({
        display: 'block'
    });
    $({deg: 0}).animate({deg: 180}, {
        duration: 300,
        step: function(now) {
            $(".sc-client-select svg" ).css({
                transform: 'rotate(' + now + 'deg)'
            });
        }
    });
}

function closeSelect() {
    $(".sc-client-select-option-container").css({
        display: 'none'
    });
    getUserCart();
    $({deg: 180}).animate({deg: 0}, {
        duration: 300,
        step: function(now) {
            $(".sc-client-select svg" ).css({
                transform: 'rotate(' + now + 'deg)'
            });
        }
    });
}

function getUserCart() {
    $('.sc-order-products-container .sc-order-product-item-container').remove();
    let userId = $('.sc-client-select-title').data("id");
    $.ajax({
        url: 'http://localhost:8080/cart/get-user-cart?id=' + userId,
        type: 'get',
        success: function (response) {
            for (let product of response) {
                addProductToCart(product);
            }
        }
    });

}

