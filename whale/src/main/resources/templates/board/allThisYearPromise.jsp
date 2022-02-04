<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Corona Admin</title>
    <!-- Layout styles -->
    <link rel="stylesheet" href="../../resources/assets/assets/common2.css">
    <link rel="stylesheet" href="../../resources/assets/assets/reset2.css">
    <!-- End layout styles -->

    <style>

    .menu_bg { width: 100%; height: 2000px; position: fixed; top:0px; left:0px; overflow: hidden; display: none; }
    .menu { position:absolute; top:0px; left:0px; width:100%; height: 100%; background-color:#DDD; }
    .menu > div { padding: 2%; }

    </style>
  </head>

  <script>
    var giMenuDuration = 700;

    // 전체 메뉴를 오른쪽으로 슬라이드하여서 보여준다.
    function ShowMenu(){
    $('.menu_bg' ).css( { 'display' : 'block' } );
    $('.menu' ).css( { 'left' : '-100%' } );
    $('.menu' ).animate( { left: '0px' }, { duration: giMenuDuration } );
    }

    // 전체 메뉴를 왼쪽으로 슬라이드하여서 닫는다.
    function HideMenu(){
    $('.menu' ).animate( { left: '-100%' }, { duration: giMenuDuration, complete:function(){ $('.menu_bg' ).css( { 'display' : 'none' } ); } } );
    }

    // 확장 메뉴를 보여주거나 닫는다.
    function ShowSubMenu( strId ){
    var lySubMenu = $( strId );

    if( lySubMenu.first().is( ":hidden" ) ){
    $( strId ).slideDown( 300 );
    }
    else{
    $( strId ).slideUp( 300 );
    }
    }

    $( document ).ready( function()
    {
    $('.menu_2' ).hide();
    } );

  </script>
  <body>

    <div class="totalWrap upload" id="totalWrap">


      <div class="totalHeader">
        <div class="logo" style="text-align: center;">
          <img src="../../resources/assets/assets/images/logo.png" alt="" width="120px" height="60px">
        </div>
        <button onclick="ShowMenu();" class="" type="button">
            <span class=""><img src="../../resources/assets/assets/images/ham.png" width="30px" height="30px"></span>
        </button>
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
      </div>
      <div class="subIndex"> ~~~~~~~~~~ 모두 올해 다짐 어쩌구 ~~~~~~~~</div>
      <div class="inner" style="width:375px; height: 407px; text-align: center;">
        <div class="innerList" style="display:inline-block;">

        </div>
      </div>
  </body>
</html>