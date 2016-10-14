
<%
	if (session.getAttribute("mail") != null
			&& session.getAttribute("password") != null) {
%>

<!DOCTYPE html>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MusicShow</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>


</head>
<body>
	<div id="main">
		<jsp:include page="header.jsp"></jsp:include>
		<div id="mainCont">
			<div id="centrCol">
				<div id="albmBlock">
					<c:forEach items="${Semelar_Albums}" var="al">
						<div id="albmBox3">
							<div class="topCont">
								<h1>
									<img src="images/${al[2]}" width="63" height="54" alt="" />
								</h1>
								<a style="font-size:12px" href="./Albums_details?action=detailsAlbum&albumid=${al[4]}" class="headings">${al[0]}</a><span><a
									href="./Artist_details?artist=${al[3]}" class="headings">${al[1]}</a></span>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
<%
	} else {
		request.getRequestDispatcher("index.jsp").forward(request,
				response);
	}
%>

