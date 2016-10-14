
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
<style type="text/css">
p {
	color: white;
}

td {
	height: 50px;
}

img {
	-moz-border-radius: 7px;
	-webkit-border-radius: 7px;
	border-radius: 7px;
}
</style>
</head>
<body>
	<div id="main">
		<jsp:include page="header.jsp"></jsp:include>
		<div id="mainCont">
			<div id="leftCol">
				<table style="color: white">
					<tr>
						<td><a href="Labels_Controller?action=details&id=${id}">${name}</a>
							<p>
								<img alt="${name}" src="${image}">
							</p></td>
					</tr>
				</table>
			</div>
			<div id="centrCol">
				<c:forEach items="${artistFromLabel}" var="lab">
					<p><a href="./Artist_details?action=artistDetails&artist=${lab[0]}">${lab[1]}</a></p>
					<br>
					<p>${lab[2]}</p>
					<br>
				</c:forEach>
			</div>

			<div id="rightCol"></div>
			<div id="dialog" title="Message de confirmation"
				style="display: none">
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

