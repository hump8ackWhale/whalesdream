/* nav */
let username = $("#username_nav").text();

$("#menu_btn").on("click", function() {
    $("#nav").animate({width:'toggle'},350);
});

$("#btn-close").on("click", function() {
    $("#nav").css("display", "none");
});

/* nav a click event */
$("#myInfo_nav").click(function(){
    location.href="/myInfo/myForm?username=" +username;
});

$("#myPost_nav").click(function(){
    location.href="/post/myPostList?username=" +username;
});

$("#logout_nav").click(function(){

    $.ajax({
        type: "POST",
        url: "/account/logout",
        success: function () {
            window.location.href = "/";
        },
        error: function () {

        }
    });

});