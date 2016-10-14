
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
			<table>
				<c:forEach items="${AllUsers}" var="al">
					<tr>
						<td><a href="">${al.nom}</a></td>
						<td><a href="">${al.prenom}</a></td>
						<td><a href="">${al.mail}</a></td>
					</tr>
				</c:forEach>
			</table>
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

