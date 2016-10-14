
<%
	if (session.getAttribute("mail") != null
			&& session.getAttribute("password") != null) {
%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
		<div id="main">
		<jsp:include page="header.jsp"></jsp:include>
		<div id="mainCont">
			<div id="leftCol">
				<div id="playListTop">
					<h3>ARTIST DETAILS</h3> 
				</div>
				<p style="color:white">
					<%=session.getAttribute("name")%>
				</p>
				<p style="color:white">
					<%=session.getAttribute("description")%>
				</p>

			</div>
			<div id="centrCol">
				<div id="albmBlock">
					<c:forEach items="${ReleasesFromArtist}" var="al">
						<div id="albmBox3">
							<div class="topCont">
								<h1>
									<img src="images/${al[2]}" width="63" height="54" alt="" />
								</h1>
								<a style="font-size:12px" href="./Albums_details?action=detailsAlbum&albumid=${al[4]}" class="headings">${al[0]} </a><span><a
									href="./Artist_details?action=artistDetails&artist=${al[3]}" class="headings">${al[1]}</a></span>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div id="dialog" title="Message de confirmation" style="display: none">
			  <p></p>
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
