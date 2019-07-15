/**
 * Created by Yura on 25.06.2019.
 */
$(document).ready(function(){
    appendCategoryToMenu();
});


$(document).on('click', '.sc-order-product-item-quantity-button.minus',(e)=>{
    changeItemQuantity("minus", $(e.target).closest('.sc-order-product-item-container').data("id"));
});

$(document).on('click', '.sc-order-product-item-quantity-button.plus',(e)=>{
    changeItemQuantity("plus", $(e.target).closest('.sc-order-product-item-container').data("id"));
});

$(document).on('click', '.sc-order-product-item-remove-button',(e)=>{
    $(e.target).closest('.sc-order-product-item-container').remove();
    calculateOrderSum();
});

$(document).on('click', '.sc-nav-back-button',()=>{
    $('.nav-info-container').css('background-color','#ff000000');
    $('.sc-category-info').text("SELECT CATEGORY");
    productMenuClose();
    appendCategoryToMenu();
});

$.ajax({
    url: 'http://localhost:8080/user/select-all',
    type: 'get',
    success: function (response) {
        for (let user of response) {
            addUserToSelect(user);
        }
    }
});


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

             addProductToCart();
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

function addProductToCart() {
    let $cart = $('.sc-order-products-container');
    let $cartItemTemplate = $('#cartItem');

    $('.product-menu-item').click((e)=>{
        let $productTarget = $(e.target).closest(".product-menu-item");
        let $priceFloat = parseFloat($productTarget.children('.sc-item-price').text());
        let $productId = $productTarget.data("id");
        let $product = {
            id: $productId,
            name: $productTarget.children('.sc-item-title').text() ,
            priceDouble: $priceFloat,
            allPrice:$priceFloat,
            price: doubleToStringsByDot($priceFloat)[0],
            priceCoin: doubleToStringsByDot($priceFloat)[1]
    }

        if(checkItemsInCart($productId)){
            changeItemQuantity("plus", $productId);
        } else {
            $($cartItemTemplate).tmpl($product).appendTo($cart);
            calculateOrderSum();
        }

    });
}

function changeItemQuantity(type, id) {
    let existedItem = $(`.sc-order-products-container .sc-order-product-item-container[data-id=${id}]`);
    let quantityInput = existedItem.find(".sc-order-product-item-quantity-input");
    if (type === "minus" && quantityInput.val() > 1){
        quantityInput.val(parseInt(quantityInput.val())-1);
    } else if (type === "minus" && quantityInput.val() <= 1){
        console.log('remove');
        existedItem.remove();
    } else if (type === "plus"){
        quantityInput.val(parseInt(quantityInput.val())+1);
    }
    let newPrice = existedItem.data('price')*quantityInput.val();
    existedItem.data('all-price',newPrice)
    existedItem.find(".sc-order-product-item-price-info span")
        .text(doubleToStringsByDot(newPrice)[0])
        .append("<sup>"+ doubleToStringsByDot(newPrice)[1] +"</sup>");
    calculateOrderSum();
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
        console.log($(this));
        sum += $(this).data("all-price");
    });
    $('.sc-order-info-item-info').text(doubleToStringsByDot(sum)[0])
                                    .append("<sup>"+ doubleToStringsByDot(sum)[1] +"</sup>");

}

function doubleToStringsByDot($double) {
    let $strings = $double.toFixed(2).toString().split(".")
     if ($strings[1] != null && $strings[1].length === 1){
         $strings[1] += "0";
    } else if ($strings[1] == null) {
         $strings[1] = "00";
    }
    return $strings;
}

function addUserToSelect(user) {

    $('.sc-client-select-option-container').append(`<div class="sc-client-select-option">${user.name}</div>`)

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

