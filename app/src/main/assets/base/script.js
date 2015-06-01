var currentPage;
var pageCount;
var log_id;
var toWidth;
var toPage;
const LOAD_SIZE = 1;

function addAllPages(){
    for (var i = 1; i <= 8; i++){
        addImageFrame();
    }
    rmBottomMargin();
    goToPage(1);
}

function fitToWidth() {
    $('.pic_div').css('width', '100%');
    $('.page_img').css('width', '100%');
    $('.page_img').css('height', 'auto');
    $('.pic_div').css('height', $('.page_img').css('height'));
    toWidth = true;
    toPage = false;
}

function fitToPage(){
    $('.page_img').css('height', $(window).height());
    $('.page_img').css('width', 'auto');
    $('.pic_div').css('width', $('.page_img').css('width'));
    $('.pic_div').css('height', $('.page_img').css('height'));
    $('.pic_div').css('margin-left', 'auto');
    $('.pic_div').css('margin-right', 'auto');
    toPage = true;
    toWidth = false;
}

function rmBackground(pageNumber){
    var image_div = $('#page_' + pageNumber);
    image_div.css('background-image', 'none');
}

function rmBottomMargin(){
    $( "#page_" + pageCount ).css('margin-bottom', '0');
}

function onScroll(){
    if (!currentPage){
        currentPage = 1;
    }
    $('.pic_div').each(function(){
        var visible = $(this).visible(true);
        if (visible){
            var pageString = this.id;
            var pageNumber = parseInt(pageString.replace('page_', ''));
            if (pageNumber !== currentPage){
                updatePageNumber(pageNumber);
                if (toPage){
                    fitToPage();
                }
                if (toWidth){
                    fitToWidth();
                }
            }
        }
    });
}

function goToPage(pageNumber){
    if (pageNumber >= 1 && pageNumber <= pageCount){
        $('html, body').animate({
            scrollTop: ($('#page_' + pageNumber).offset().top)
        }, 500);
        updatePageNumber(pageNumber);
    }
}

function updatePageNumber(pageNumber){
    if (currentPage !== pageNumber){
        currentPage = pageNumber;
        loadMorePages();
    }
}

function changeImage(pageNumber){
    var image_div = $('#page_' + pageNumber);
    var img = $('#page_' + pageNumber + ' .page_img');
    if (img.attr('src') == ''){
        var prefix = 'ikea-manuals-';
        setTimeout(function(){
            image_div.css('width', 'auto');
            image_div.css('height', 'auto');
            image_div.css('background-image', 'none');
            img.attr('src', 'images/'+ prefix + pageNumber + '.jpg');
        }, 1500);
        if (toWidth){
            fitToWidth();
        }
        if (toPage){
            fitToPage();
        }
    }
}

function addImageFrame(){
    if (!pageCount) {
            pageCount = 0;
    }
    pageCount++;
    var element = '<div class="pic_div" id="page_' + pageCount +'">' + '<img class="page_img" src=""/>' + '</div>';
    $('#frame').append(element);
    $('.pic_div').css('height', $(window).height());
}

function loadMorePages(){
    var start = currentPage - LOAD_SIZE;
    var end = currentPage + LOAD_SIZE;
    if (start < 1){
        start = 1;
    }
    if (end > pageCount){
        end = pageCount;
    }
    for (var i = start; i <= end; i++){
        changeImage(i);
    }
}