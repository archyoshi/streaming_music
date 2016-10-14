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
  		$('#ajouter').submit(function(){
  			$.ajax({
  				url:'./Crud_user' ,
  				type:'POST',
  				dataType:'json',
  				data:$('#ajouter').serialize(),
  				success:function(data){
  					if(data.isValid){
  						$('#dialog').html(data.message);
  						$('#dialog').dialog();
  						$('#dialog').slideDown(500);
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
		<jsp:include page="header.jsp"></jsp:include>
			<form id="ajouter">
				<table style="color: white">
					<tr>
						<td>Nom</td>
						<td><input type="text" name="nom" id="nom"/></td>
					</tr>
					<tr>
						<td>Prénom</td>
						<td><input type="text" name="prenom" id="prenom" /></td>
					</tr>
					<tr>
						<td>E-mail</td>
						<td><input type="text" name="mail" id="mail" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="text" name="password" id="password"/></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="hidden" name="action" id="action" value="ajouter" /> 
						<input type="submit" value="Créer nouveau compte" /> <br /></td>
					</tr>
				</table>
			</form>
			<div id="dialog" title="Message de confirmation" style="display: none">
			  <p></p>
			</div>
		

	</div>
</body>
</html>
