<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"><meta content="width=device-width, initial-scale=1.0" name="viewport">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Playfair+Display:wght@700;900&display=swap" rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="/resources/lib/animate/animate.min.css" rel="stylesheet">
<link href="/resources/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

<!-- Bootstrap Stylesheet -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

<!-- Template Stylesheet -->
<link href="/resources/css/style.css" rel="stylesheet">

<!-- css 적용 -->
<link href="/resources/css/t_list.css" rel="stylesheet">

<title></title>
</head>
<body>

	<!-- Navbar Start -->
	<header>
		<c:import url="./header.jsp"></c:import>
	</header>
	<!-- Navbar End -->


	<!-- Carousel Start -->
	<div class="dd"></div>


	<main class="container-fluid px-0 mb-5" id="main">
	
		<h5 class="present_location"><img alt="" src="/resources/img/home.png" class="where"> > 여행정보 > 볼거리</h5>
		<br>
		<div class="main_div">
			<div class="ct_div">
				<c:choose>
					<c:when test="${ct_d eq 9}">
						<a href="/t_list.traveldo?ct_u=1&ct_d=9" class="ct_active">전체</a>
						<a href="/t_list.traveldo?ct_u=1&ct_d=3" class="ct_detail">명소</a>
						<a href="/t_list.traveldo?ct_u=1&ct_d=4" class="ct_detail">자연&경관</a>
						<a href="/t_list.traveldo?ct_u=1&ct_d=5" class="ct_detail">엔터테인먼트</a>
					</c:when>
					<c:when test="${ct_d eq 3}">
						<a href="/t_list.traveldo?ct_u=1&ct_d=9" class="ct_detail">전체</a>
						<a href="/t_list.traveldo?ct_u=1&ct_d=3" class="ct_active">명소</a>
						<a href="/t_list.traveldo?ct_u=1&ct_d=4" class="ct_detail">자연&경관</a>
						<a href="/t_list.traveldo?ct_u=1&ct_d=5" class="ct_detail">엔터테인먼트</a>
					</c:when>
					<c:when test="${ct_d eq 4}">
						<a href="/t_list.traveldo?ct_u=1&ct_d=9" class="ct_detail">전체</a>
						<a href="/t_list.traveldo?ct_u=1&ct_d=3" class="ct_detail">명소</a>
						<a href="/t_list.traveldo?ct_u=1&ct_d=4" class="ct_active">자연&경관</a>
						<a href="/t_list.traveldo?ct_u=1&ct_d=5" class="ct_detail">엔터테인먼트</a>
					</c:when>
					<c:otherwise>
						<a href="/t_list.traveldo?ct_u=1&ct_d=9" class="ct_detail">전체</a>
						<a href="/t_list.traveldo?ct_u=1&ct_d=3" class="ct_detail">명소</a>
						<a href="/t_list.traveldo?ct_u=1&ct_d=4" class="ct_detail">자연&경관</a>
						<a href="/t_list.traveldo?ct_u=1&ct_d=5" class="ct_active">엔터테인먼트</a>
					</c:otherwise>
				</c:choose>
			</div>
			
			<div class="sort-element row align-items-center">
				<div class="col-4"></div>
			
				<div class="offset-md-5 col-3">
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
		  				<div class="d-inline-flex gap-1">
			 				<input class="btn btn-primary me-md-2" type="button" value="게시글 작성" onclick='window.location="/t_write.traveldo?ct_u=${ct_u}&ct_d=${ct_d}"'>
		  					<input class="btn btn-primary" type="button" value="키워드 검색" data-bs-toggle="collapse" data-bs-target="#searchKeyword" aria-expanded="false" aria-controls="searchKeyword">
						</div>
					</div>
				</div>
			</div>
			
			<div class="collapse" id="searchKeyword">
		    	<div class="container text-center">
		    		<form name="keywordForm" class="form-control" action="/t_list.traveldo" method="post">
		    			<input type="hidden" name="ct_u" value="${ct_u}">
		    			<input type="hidden" name="ct_d" value="${ct_d}">
		    			
		    			<c:if test="${searchKeyword eq ''}">
		    				<input type="text" class="searchKeyword" placeholder="키워드" name="searchKeyword">
		    			</c:if>
		    			<c:if test="${searchKeyword ne ''}">
		    				<input type="text" class="searchKeyword" placeholder="키워드" name="searchKeyword" value="${searchKeyword}">
		    			</c:if>
		    			
		    			<input type="submit" class="btn_search_k" value="검 색">
		    		</form>
		    	</div>
			</div>	
			
			
			<%-- 게시글 처리 --%>
			<c:if test="${count>0}">
					<c:if test="${boardList!=null and !boardList.isEmpty()}">
						<c:forEach var="vo" items="${boardList}" begin="0" end="${boardList.size()-1}" step="1" varStatus="status">
							
							<c:if test="${status.index%2==0}">
				<div class="mb">
							</c:if>
					
				<a href="/t_detail.traveldo?num=${vo.getNum()}&ct_u=${ct_u}&ct_d=${ct_d}&pageNum=${currentPage}&searchKeyword=${searchKeyword}">
					<div class="mb_board">  
						<div class="mb_board_one">
							<div class="mb_board_one_left">
								<h3 class="mb_title">${vo.getTitle()}</h3>
								<div class="mb_l_one_left_c">${vo.getContent()}</div>
							</div>
							<img alt="" src="${vo.getFilepath()}" class="mb_board_one_right">
				        </div>
					</div>
				</a>
			
							<c:if test="${status.index%2 == 0}">
				<div class="mb_center" style="width: 34px; display: inline-block;"></div>
							</c:if>
			
						<c:if test="${status.index%2 != 0}">
				</div>
						</c:if>
				
						</c:forEach>
					</c:if>
			</c:if>
			
			<c:if test="${count eq 0}">
				<div class="noContent">
					<div>일치하는 컨텐츠가 존재하지 않습니다.</div>
					<input type="button" class="btn btn-pramary goback" onclick="javascript:history.go(-1)" value="이전으로">
				</div>
			</c:if>
			
		</div>
		
		<%-- 페이징 처리 --%>
		<c:set var="pageBlock" value="5"></c:set>
		<c:set var="imsi" value="${count % boardSize == 0 ? 0 : 1}"></c:set>
		<fmt:parseNumber var="r" value="${count/boardSize}" integerOnly="true"></fmt:parseNumber>
		<c:set var="pageCount" value="${r + imsi}"></c:set>
		<c:set var="firstPage" value="1"></c:set>
		<c:set var="lastPage" value="${pageCount}"></c:set>
		<fmt:parseNumber var="res" value="${(currentPage-1)/pageBlock}" integerOnly="true"></fmt:parseNumber>
		<c:set var="startPage" value="${res*pageBlock + 1}"></c:set>
		<c:set var="endPage" value="${startPage + pageBlock - 1}"></c:set>
		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}"></c:set>
		</c:if>
		
		<c:if test="${count>0}">
			<div class="page_list">
			
			<c:if test="${firstPage!=startPage}">
				<c:if test="${searchKeyword eq ''}">
					<a href="/t_list.traveldo?ct_u=${ct_u}&ct_d=${ct_d}&pageNum=${firstPage}" class="page_f"><<</a>
					<a href="/t_list.traveldo?ct_u=${ct_u}&ct_d=${ct_d}&pageNum=${startPage-1}" class="page_f"><</a>
				</c:if>
				<c:if test="${searchKeyword ne ''}">
					<a href="/t_list.traveldo?ct_u=${ct_u}&ct_d=${ct_d}&pageNum=${firstPage}&searchKeyword=${searchKeyword}" class="page_f"><<</a>
					<a href="/t_list.traveldo?ct_u=${ct_u}&ct_d=${ct_d}&pageNum=${startPage-1}&searchKeyword=${searchKeyword}" class="page_f"><</a>
				</c:if>
			</c:if>
			
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<c:if test="${searchKeyword eq ''}">
					<c:if test="${i eq currentPage}">
						<a href="/t_list.traveldo?ct_u=${ct_u}&ct_d=${ct_d}&pageNum=${i}" class="page_f_on">${i}</a>
					</c:if>
					<c:if test="${i ne currentPage}">
						<a href="/t_list.traveldo?ct_u=${ct_u}&ct_d=${ct_d}&pageNum=${i}" class="page_f">${i}</a>
					</c:if>
				</c:if>
				<c:if test="${searchKeyword ne ''}">
					<c:if test="${i eq currentPage}">
						<a href="/t_list.traveldo?ct_u=${ct_u}&ct_d=${ct_d}&pageNum=${i}&searchKeyword=${searchKeyword}" class="page_f_on">${i}</a>
					</c:if>
					<c:if test="${i ne currentPage}">
						<a href="/t_list.traveldo?ct_u=${ct_u}&ct_d=${ct_d}&pageNum=${i}&searchKeyword=${searchKeyword}" class="page_f">${i}</a>
					</c:if>
				</c:if>
			</c:forEach>
			
			<c:if test="${lastPage!=endPage}">
				<c:if test="${searchKeyword eq ''}">
					<a href="/t_list.traveldo?ct_u=${ct_u}&ct_d=${ct_d}&pageNum=${endPage+1}" class="page_f">></a>
					<a href="/t_list.traveldo?ct_u=${ct_u}&ct_d=${ct_d}&pageNum=${lastPage}" class="page_f">>></a>
				</c:if>
				<c:if test="${searchKeyword ne ''}">
					<a href="/t_list.traveldo?ct_u=${ct_u}&ct_d=${ct_d}&pageNum=${endPage+1}&searchKeyword=${searchKeyword}" class="page_f">></a>
					<a href="/t_list.traveldo?ct_u=${ct_u}&ct_d=${ct_d}&pageNum=${lastPage}&searchKeyword=${searchKeyword}" class="page_f">>></a>
				</c:if>
			</c:if>
			
			</div>
		</c:if>
		
	</main>


	<!-- Carousel End -->

	<!-- Copyright Start -->
	<footer style="width:100%">
		<c:import url="./footer.jsp"></c:import>
	</footer>
    <!-- Copyright End -->
	
    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square rounded-circle back-to-top"><i class="bi bi-arrow-up"></i></a>

	<!-- JavaScript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="/resources/lib/wow/wow.min.js"></script>
    <script src="/resources/lib/easing/easing.min.js"></script>
    <script src="/resources/lib/waypoints/waypoints.min.js"></script>
    <script src="/resources/lib/owlcarousel/owl.carousel.min.js"></script>
    <!-- Template JavaScript -->
    <script src="/resources/js/main.js"></script>
	<!-- Bootstrap Script -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>


</body>
</html>