<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Dream of Whale</title>
    <!-- Layout styles -->
    <link rel="stylesheet" href="../../resources/assets/assets/common2.css">
    <link rel="stylesheet" href="../../resources/assets/assets/reset2.css">
    <!-- End layout styles -->

    <style>
    /* nav */
      #nav {
        position: fixed;
        z-index: 10;
        top : 60px;
        right : -100%;
        width : 75%;
        max-width : 300px;
        height : calc(100% - 60px);
        padding : 50px 0;
        transition: all 500ms ease;
      }
      #nav.act {
        right : 0;
      }
      span {display:block;}

    </style>
  </head>

  <script>
//     var giMenuDuration = 700;

// // 전체 메뉴를 오른쪽으로 슬라이드하여서 보여준다.
// function ShowMenu(){
// $('.menu_bg' ).css( { 'display' : 'block' } );
// $('.menu' ).css( { 'left' : '-100%' } );
// $('.menu' ).animate( { left: '0px' }, { duration: giMenuDuration } );
// }

// // 전체 메뉴를 왼쪽으로 슬라이드하여서 닫는다.
// function HideMenu(){
// $('.menu' ).animate( { left: '-100%' }, { duration: giMenuDuration, complete:function(){ $('.menu_bg' ).css( { 'display' : 'none' } ); } } );
// }

// // 확장 메뉴를 보여주거나 닫는다.
// function ShowSubMenu( strId ){
// var lySubMenu = $( strId );

// if( lySubMenu.first().is( ":hidden" ) ){
// $( strId ).slideDown( 300 );
// }
// else{
// $( strId ).slideUp( 300 );
// }
// }

// $( document ).ready( function()
// {
// $('.menu_2' ).hide();
// } );

ShowMenu = function(){
  var nav = $('#nav');
  nav.addClass('act');
}

  </script>
  <body>

    <div class="totalWrap upload" id="totalWrap">
      <!-- nav -->
      <nav id="nav">
        <div class="menu_bg">
          <div class="menu">
            <ul>
              <li><a href="#">메뉴1</a></li>
              <li><a href="#">메뉴2</a></li>
              <li><a href="#">메뉴3</a></li>
              <li><a href="#">메뉴4</a></li>
            </ul>
          </div>
        </div>
      </nav>
      <!-- content -->
      <jsp:include page="common/header.jsp" />
      <div class="subIndex"> ~~~~~~~~~~ 모두 올해 다짐 어쩌구 ~~~~~~~~</div>
      <div class="inner" style="width:375px; height: 407px; text-align: center;">
        <div class="innerList" style="display:inline-block;">
            올해다짐 목록~~~~~~~~~~~~~~~~
        </div>
      </div>
      <a href="testPage">servleletTestPage</a>

      <footer>
        <div style="width:auto; height: 50px; text-align: center;" >
          <span> 제작 </span>
          <span> 김웨일 & 여웨일 & 백웨일 & 하웨일</span>
        </div>
      </footer>
  </body>
</html>