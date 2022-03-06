$("#menu_btn").on("click", function() {
    $("#nav").animate({width:'toggle'},350);
});

$("#btn-close").on("click", function() {
    $("#nav").css("display", "none");
});