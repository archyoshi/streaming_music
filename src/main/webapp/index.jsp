<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>MusicShow</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<link href="css/styles.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<script type="text/javascript">
  	$(document).ready(function(){
  		$('#authentification').submit(function(){
  			$.ajax({
  				url:'./Crud_user' ,
  				type:'POST',
  				dataType:'json',
  				data:$('#authentification').serialize(),
  				success:function(data){
  					if(data.isValid){
  						location.href=data.url ;
  					}else {
  						$('#dialog').html(data.message);
  						$('#dialog').dialog();
  						$('#dialog').slideDown(500);
  					}
  				}
  			});
  			return false ;
  		});
  	});
	</script>
</head>
<body>
	<div id="main">
		<div id="header">
			<div id="menu"></div>
			<div id="header-Bottom">
				<div id="logoBlock">
					<h1>Music Store</h1>
					<p>Master WI</p>
				</div>
				<div id="navBlock"></div>
			</div>
			<form id="authentification">
				<table style="color: white">
					<tr>
						<td>E-mail</td>
						<td><input type="text" name="mail" id="mail" /></td>
					</tr>
					<tr>
						<td>Mot de passe</td>
						<td><input type="password" name="password" id="password" /></td>
					</tr>
					<tr>
						<td><input type="hidden" name="action" id="action" value="authentification"></td>
						<td><input type="submit" value="Se connecter" /> <br /> <a
							href="create_account.jsp">New user</a><br /> <a
							href="forgot_password.jsp">Forgot password ?</a></td>
					</tr>
				</table>
			</form>
			<div id="dialog" title="Message de confirmation" style="display: none">
			  <p></p>
			</div>
		
		</div>

	</div>
</body>
</html>
