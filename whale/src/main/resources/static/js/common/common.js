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

    const defaultArticlePaginationSize = 5;
    const userId =  $("#userid_nav").text();
    const lastPostId = parseInt(Number.MAX_SAFE_INTEGER, 10);
    let   flag = "my";

    location.href="/post/myPostList?lastPostId=" +lastPostId + "&size=" +defaultArticlePaginationSize+ "&userId=" +userId + "&flag=" + flag;

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

function equalUsername(param) {

 if(username != param) {
    $(".user_equal").css("display", "none");
 }

}

function toggleAnimationPlayState() {
  document.querySelector("#walking_turtle").classList.toggle("paused");
}