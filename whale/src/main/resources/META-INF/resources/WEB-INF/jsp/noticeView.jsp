<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>noticeView</title>
    <link rel="stylesheet" href="../../assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="../../assets/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="../../assets/css/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="../../assets/images/favicon.png" />
</head>
<body>
<!--<%@include file=헤더파일 %>-->

		<div id="main-panel">
			<div class="content-wrapper">

				<div id="section-view-club" class="page-section topmargin bottommargin board-view">
					<div class="container clearfix">
						<div class="heading-block col_full bottommargin center nuline">
							<h1 class="sub-h1 lotte_title_font">고객 센터</h1>
							<div class="sub-page-line"></div>
						</div>
						<!-- Post Content -->
						<div class="single-post nobottommargin">
						<!-- Single Post -->
							<div class="clearfix border-single-entry">
								<!-- Entry Title -->
								<div class="entry-title bgf7f7f7 paddingLeftRight50 paddingTopBottom20">
									<h2>${rvo.nt_title}</h2>
								</div><!-- .entry-title end -->
								<!-- Entry Meta -->
								<ul class="entry-meta paddingLeftRight50 clearfix">
									<li><strong>작성자:</strong> ${rvo.nt_nickname}</li>
									<!--
									<li><strong>진행일:</strong> 2018.01.30 ~ 2018.01.30</li>
									-->
								</ul><!-- .entry-meta end -->
								<!-- Entry Content -->
								<div class="entry-content paddingLeftRight50 notopmargin" style="white-space:pre-line;">
									<c:out value="${rvo.nt_content}" escapeXml="true" />

								</div>



							</div><!-- Post Single - Content End -->
						</div><!-- .entry end -->
						<!-- Comments -->
						<div id="comments" class="clearfix">
							<div class="entry_comments clearfix" id="entry_comments">


								<form method="post" name="frm_reply_save" id="frm_reply_save" autocomplete="off">
									<input type="hidden" name="i_sNtCode" value="US"/>
									<input type="hidden" name="i_sGroupId" value="${rvo.nt_idx}"/>
									<input type="hidden" name="i_sReplyIdx"/>
									<input type="hidden" name="i_sFlagAction" value="${reqVo.i_sFlagAction}"/>
									<div class="entry_comments_form">
										<h3><span>댓글</span> ${replyList.size()}</h3>
										<div class="entry_comments_form_body clearfix">
											<div class="col-sm-11 col-xs-10 nopadding">
												<textarea class="form-control" id="reply_content" name="reply_content" rows="3"></textarea>
											</div>
											<div class="col-sm-1 col-xs-2 nopadding center">
												<a href="#" class="button button button-xlarge btn-block nomargin noleftpadding norightpadding button-replysubmit" id="btn_replySave">등록</a>
											</div>
										</div>
									</div>
								</form>
								<form name="reply_form" id="reply_form">
									<div class="reply_comments_form topmargin-sm">
										<div class="comment-list">
											<c:choose>
												<c:when test="${!empty replyList}">
													<c:forEach items="${replyList}" var="replyResult" varStatus="status">
														<div class="comment-1depth-item clearfix">
															<input type="hidden" name="arrReplyIdx" value="${replyResult.nt_idx}"/>
															<input type="hidden" name="arrReplyContent" value="${replyResult.nt_content}"/>
															<strong>${cfn:getChangeUserName(fn:trim(replyResult.row_input_emp_no))}</strong>
															<span class="reply-date">${replyResult.row_input_date}</span>
															<div class="reply-content">
																${replyResult.nt_content}
															</div>
															<c:if test="${reqVo.userId == fn:trim(replyResult.row_input_emp_no)}">
															<div class="comment-btn-group bottommargin-sm">
																<a href="#" class="button button-border-thin button-mini button-border button-reply noleftmargin btn_replyModify" >수정</a>
																<a href="#" class="button button-border-thin button-mini button-border button-reply btn_replyDelete">삭제</a>
																<a href="#" class="button button-border-thin button-mini button-border button-reply btn_replyCancel" style="display: none">취소</a>
															</div>

															</c:if>
														</div>

													</c:forEach>
												</c:when>
												<c:otherwise>
													<div class="comment-1depth-item clearfix">등록된 게시물이 없습니다.</div>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</form>
							</div>
						</div><!-- #comments end -->

						<!-- Post Navigation -->
						<div class="post-navigation topmargin clearfix">
							<div class="prev clearfix">
								<div class="col-sm-1 center bgf7f7f7 paddingTopBottom10">
									<i class="xi-angle-up-min"></i> <strong>이전</strong>
								</div>
								<div class="col-sm-11 paddingTopBottom10">
									<a href="" title="" id="${rvoPrev.nt_idx}">${! empty rvoPrev.nt_idx ? rvoPrev.nt_title : '이전글이 없습니다.'}</a>
								</div>
							</div>
							<div class="next clearfix">
								<div class="col-sm-1 center bgf7f7f7 paddingTopBottom10">
									<i class="xi-angle-down-min"></i> <strong>다음</strong>
								</div>
								<div class="col-sm-11 paddingTopBottom10">
									<a href="" title="" id="${rvoNext.nt_idx}">${! empty rvoNext.nt_idx ? rvoNext.nt_title : '다음글이 없습니다.'}</a>
								</div>
							</div>
						</div><!-- .post-navigation end -->

						<!-- Button Group -->
						<div class="board-buttons topmargin clearfix">
							<div class="clearfix tright">
								<a href="#" id="btn_list" class="button button-border-thin button-border button-reply noleftmargin lotte-btn-list">목록보기</a>
								<a href="elyesareaMarketWrite.do" class="button button-border-thin button-border button-reply noleftmargin lotte-btn-write">글쓰기</a>
								<c:if test="${reqVo.userId eq fn:trim(rvo.row_input_emp_no)}">
								<a href="#" id="btn_modify" class="button button-border-thin button-border button-reply noleftmargin lotte-btn-write">수정</a>
								<a href="#" id="btn_delete" class="button button-border-thin button-border button-reply noleftmargin lotte-btn-write">삭제</a>
								</c:if>
							</div>
						</div><!-- Button Group end -->
					</div>
				</div>

			</div>
</body>
</html>

<script type="text/javascript">
//<![CDATA[
	$( document ).ready(function() {

		$("#btn_list").click(function (event) {
			//목록가기
		});

		$("#btn_modify").click(function (event) {
			// 수정하기

		});

		$("#btn_delete").click(function (event) {
		    // 삭제하기
		});

		$(".prev, .next").click(function (event) {

			var id = $(this).children("a").attr("id");
			if(id != ""){
				$("#frm > input[name='i_sNtIdx']").val(id);
				$("#frm").attr("action", "<c:url value="/mypage/elyesareaMarketView.do"/>").submit();
			}

		});

		$("#btn_replySave").click(function (event) {
		 // 댓글 저장
			var replyContent = $("#reply_content").val();

			if(replyContent == "" || replyContent == null){
				alert("내용을 입력해주세요");
				return;
			}


		});

		$(".btn_replyModify").click(function (event) {
			// 댓글 수정
			var replyIdx = $(this).parent().prevAll("input[name='arrReplyIdx']").val();
			var replyContent = $(this).parent().prevAll("input[name='arrReplyContent']").val();

			$(this).nextAll(".btn_replyDelete").hide();
			$(this).nextAll(".btn_replyCancel").show();
			$("#frm_reply_save > input[name='i_sReplyIdx']").val(replyIdx);
			$("#reply_content").val(replyContent);

		});

		$(".btn_replyCancel").click(function (event) {

        // 댓글 취소
			$(this).prevAll(".btn_replyDelete").show();
			$(this).hide();
			$("#frm_reply_save > input[name='i_sReplyIdx']").val("");
			$("#reply_content").val("");

		});

		$(".btn_replyDelete").click(function (event) {
			// 댓글 삭제
			if (confirm("삭제하시겠습니까?")) {
				var replyIdx = $(this).parent().prevAll("input[name='arrReplyIdx']").val();

				$("#frm_reply_save > input[name='i_sReplyIdx']").val(replyIdx);

			}
		});

	});

	function fn_replySave(mode){
		var idx = $("#frm_reply_save > input[name='i_sReplyIdx']").val();

		if(mode == "R"){
			if(idx != ""){
				$("#frm_reply_save > input[name='i_sFlagAction']").val("M");
			}else{
				$("#frm_reply_save > input[name='i_sFlagAction']").val("R");
			}
		}else if(mode == "D"){
			$("#frm_reply_save > input[name='i_sFlagAction']").val("D");
		}else{
			return;
		}
        // 댓글 저장 에이작스
	}


//]]>
</script>

<!--<%@include file=푸터파일 %>-->