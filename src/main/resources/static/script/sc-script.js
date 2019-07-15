$(document).ready(function(){

    /*/!*--------- Category menu view ----------- *!/

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

    /!*--------- Category menu view end ----------- *!/*/

    

   /* // Custom select with search
    var selectOptionContainer, selectOptionArr;

    selectOptionContainer = $( ".sc-client-select-option-container" );
    selectOptionArr = $('.sc-client-select option');

    addOptionsToSelect();*/

    /*$('.sc-client-select').click(function () {
        if( selectOptionContainer.css('display') === 'none') {
            openSelect();
        } else {
            closeSelect();
        }
    });*/

   /* $('.client-search-input input').keyup(function () {
        $('.sc-client-select-option').remove();
        for (var i = 0; i < selectOptionArr.length ; i++) {
            if (selectOptionArr[i].value.toLowerCase().indexOf(this.value) === 0 ){
                selectOptionContainer.append("<div class='sc-client-select-option'>" + selectOptionArr[i].value + "</div>");
            }
        }
    });

    $("body").on("click", ".sc-client-select-option", function(){
        console.log('klick');
        $('.sc-client-select-title').val($(this).text());
        closeSelect();
    });*/
});


function addOptionsToSelect() {
    var selectOption, selectOptionArr;
    selectOptionArr = $('.sc-client-select option');
    $('.sc-client-select-option').remove();
    for (var i = 0; i < selectOptionArr.length; i++) {
        selectOption = selectOptionArr[i].value;
        $( ".sc-client-select-option-container" ).append("<div class='sc-client-select-option'>" + selectOption + "</div>");
    }
}



