
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
<script type="text/javascript">
	function doConfirm() {
		return confirm('Are you sure?');
	}
	$(document).ready(function() {
		$('#modify').submit(function() {
			$.ajax({
				url : './Crud_user',
				type : 'POST',
				dataType : 'json',
				data : $('#modify').serialize(),
				success : function(data) {
					if (data.isValid) {
						$('#dialog').html(data.message);
						$('#dialog').dialog();
						$('#dialog').slideDown(500);
					} else {
						$('#dialog').html(data.message);
						$('#dialog').dialog();
						$('#dialog').slideDown(500);
					}
				}
			});
			return false;
		});
	});
</script>

</head>
<body>
	<div id="main">
		<jsp:include page="header.jsp"></jsp:include>
		<div id="mainCont">
			<form id="modify">
				<table style="color: white">
					<tr>
						<td>First Name</td>
						<td><input type="text"
							value="<%=session.getAttribute("Firstname")%>" name="Firstname" /></td>
					</tr>
					<tr>
						<td>Last Name</td>
						<td><input type="text"
							value="<%=session.getAttribute("Lastname")%>" name="Lastname" /></td>
					</tr>
					<tr>
						<td>E-mail</td>
						<td><input type="text"
							value="<%=session.getAttribute("mail")%>" name="mail" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="text"
							value="<%=session.getAttribute("password")%>" name="password" /></td>
					</tr>
					<tr>
						<td><input type="hidden" value="modify" name="action"></td>
						<td><input type="submit" value="Modify" /></td>
					</tr>
				</table>
			</form>
			<a href="Crud_user?action=deleteUser" style="float: right" onclick="return doConfirm()"><input
				type="button" value="Delete account"></a>
		</div>
		<div id="dialog" title="Message de confirmation" style="display: none">
			<p></p>
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

