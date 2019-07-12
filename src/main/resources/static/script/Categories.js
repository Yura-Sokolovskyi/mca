/**
 * Created by Yura on 25.06.2019.
 */

$.ajax({
    url: 'http://localhost:8080/category',
    type: 'get',
    success: function (response) {
        for (let category of response) {
            appendCategory(category);
        }
        appendProductByCategory();
    }
});

function appendCategory(category) {
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
       let flag = 0;
       $('.nav-info-container').css("background-color",$categoryColor);
       $('.sc-category-info').text($categoryName);

       console.log($categoryName);

       $('.sc-category-menu-item').fadeOut("slow", ()=>{

           if (flag == 0){
               $.ajax({
                   url: 'http://localhost:8080/product?id=' + $id,
                   type: 'get',
                   success: function (response) {
                       for (let product of response) {
                           appendProduct(product);
                       }
                       addProductToCart();
                   }
               });
              flag++;
           }
       });

    });



    function appendProduct(product) {
        $('.sc-category-menu-item-container').append(`
            <div class=" col-md-4 col-sm-6 sc-category-menu-item">
                        <div class="sc-category-menu-item-content sc-flex-centred product-menu-item" data-id="${product.id}" style="background-image: url(${"images/" + product.image + ".jpg"})">
                        <div class="sc-item-title sc-flex-centred" style="background-color: #8D6235">${product.name}  </div> <div class="sc-item-price sc-flex-centred"> ${product.price} <br> грн/шт</div>
                  </div>
            </div>
        `);
    }





}

function addProductToCart() {

    let $cart = $('.sc-order-products-container');
    let $cartItemTemplate = $('#cartItem');



    $('.product-menu-item').click((e)=>{
        let $productTarget = $(e.target);
        let $price = $productTarget.children('.sc-item-price').text().split(".");
        console.log($productTarget);

        let $product = {
            name: $productTarget.children('.sc-item-title').text() ,
            price: $price[0],
            priceCoin: parseInt($price[1])
        }

        $($cartItemTemplate).tmpl($product).appendTo($cart);

    });


}