<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>noticeWrite</title>
</head>
<body>
<!--<%@include file=헤더파일 %>-->
<!--<%@include file=nav파일 %>-->
    <div class="main-panel">
        <div class="content-wrapper">
                    <div id="section-customer-contact-form" class="page-section topmargin bottommargin">
                        <div class="container clearfix">
                            <div class="heading-block col_full bottommargin center nuline">
                                <h1 class="sub-h1 lotte_title_font">고객센터</h1>
                                <div class="sub-page-line"></div>
                            </div>
                            <div class="clearfix bottommargin">
                                <form id="Frm" name="Frm" class="nobottommargin notopmargin form-horizontal lotte-write-form" action="#" method="post" enctype="multipart/form-data">

                                    <div class="form-group">
                                        <label for="contact-subject" class="col-sm-2 control-label">제목
                                        </label>
                                        <div class="col-sm-10">
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input type="text" id="title" name="title" value="" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="contact-content" class="col-sm-2 control-label">내용
                                        </label>
                                        <div class="col-sm-10">
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <textarea class="form-control" rows="5" id="content" name="content"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="col_full center topmargin-sm marginBottom10">
                                            <a href="#" onClick="btnSave();" class="button button-circle button-border button-whiteorange marginRight10 paddingLeftRight50">등록</a> <a href="#" class="button button-circle button-border button-white paddingLeftRight50" onCLick="btnCancelClick(event)">취소</a>
                                        </div>


                                        <input type="hidden" id="vp_name" name="vp_name" value="" />

                                    </div>
                                </form>

                            </div>

                        </div>


                    </div>

        </div>
    </div>
</body>
</html>

<script type="text/javascript">


</script>
<!--<%@include file=푸터파일 %>-->l>