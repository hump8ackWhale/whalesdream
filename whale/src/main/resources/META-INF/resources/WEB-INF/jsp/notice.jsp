<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>notice</title>
    <link rel="stylesheet" href="../../assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="../../assets/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="../../assets/css/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="../../assets/images/favicon.png" />
</head>
<body>
<div class="container-scroller">
      <!-- partial:../../partials/_sidebar.html -->
<!--<%@include file=헤더파일 %>-->
<!--<%@include file=nav파일 %>-->


        <!-- partial -->
        <div class="main-panel">
          <div class="content-wrapper">


            <div id="section-market" class="page-section topmargin bottommargin">
                <div class="container clearfix">
                    <div class="heading-block col_full bottommargin center nuline">
                        <h1 class="sub-h1 lotte_title_font">고객센터</h1>
                        <div class="sub-page-line"></div>
                    </div>
                    <div class="title-block marginBottom10">
                        <h3>유의 <span>사항</span></h3>
                        <span></span>
                    </div>
                    <div class="lotte-info clearfix bottommargin-lg100 border-box-1">
                        <div class="nomargin paddingTopBottom30 paddingLeftRight50">
                            <ul class="nomargin">
                                <li>고객센터입니다~
                                </li>
                                 </ul></div>
                    </div>

                    <div class="clearfix nomargin">
                        <form action="#" method="post" id="frm_market_notice" name="frm_market_notice" class="nomargin form-inline form-horizontal lotte-inline-form">
                            <div class="row clearfix">
                                <div class="col-md-6 marginBottom10">
                                    <label for="searchtype" class="sr-only">검색선택</label>
                                    <div class="col-sm-3 noleftpadding norightpadding">
                                        <select class="select-hide form-control bottommargin-xs " id="searchSelect" name="searchSelect" style="width:100%;">
                                          <option value="both">전체</option>
                                          <option value='title'>제목</option>
                                          <option value='cont'>내용</option>
                                        </select>
                                    </div>
                                    <label for="search" class="sr-only">검색</label>
                                    <div class="col-sm-6 noleftpadding norightpadding">
                                        <input type="text" id="searchKeyword" name="searchKeyword" value="" class="form-control bottommargin-xs leftmargin10-sm" placeholder="검색">
                                    </div>
                                    <div class="col-sm-3 center marginBottom10 lottebtn-box">
                                        <!-- <input type="button" class="button button-border button-border-thin noleftpadding norightpadding btn-block nomargin" id="search-submit" name="search-submit" value="검색" onClick="getListHtml()"></button> -->
                                        <input type="button" class="button button-border button-border-thin  btn-block nomargin lotte-search" id="search-submit" name="search-submit" value="검색" onClick="getListHtml()">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="clearfix marginBottom15">
                        <div class="table-responsive" id="div_notice_list">

                        </div>

                <!-- Button Group -->
                        <div class="board-buttons topmargin-sm clearfix">
                            <div class="clearfix tright">
                                <a href="#" class="button button-border-thin button-border button-reply noleftmargin lotte-btn-write">글쓰기</a>
                            </div>
                        </div><!-- Button Group end -->
                    </div>
                </div>
            </div>

          </div>
          <!-- content-wrapper ends -->



<!--<%@include file=footer파일 %>-->
        </div>
        <!-- main-panel ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
    <script src="../../assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="../../assets/js/off-canvas.js"></script>
    <script src="../../assets/js/hoverable-collapse.js"></script>
    <script src="../../assets/js/misc.js"></script>
    <script src="../../assets/js/settings.js"></script>
    <script src="../../assets/js/todolist.js"></script>
</body>
</html>


<script type="text/javascript">


</script>