
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
<script>
	function loadSong(elt, e) {
		if (!e)
			var e = window.event;
		document.getElementById("player").src = elt.href;
		document.getElementById("player").load();
		document.getElementById("player").play();
		return false;
	}
	window.onload = function() {
		links = document.getElementById("playlist").getElementsByTagName("a");
		for (var i = 0; i < links.length; i++) {
			links[i].onclick = function(e) {
				return loadSong(this, e);
			};
		}
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#addPlayList').submit(function() {
			$.ajax({
				url : './Playlist_Controller',
				type : 'POST',
				dataType : 'json',
				data : $('#addPlayList').serialize(),
				success : function(data) {
					if (data.isValid) {
						$('#dialog').html(data.message);
						$("#relo").load("home.jsp #relo");
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

		$('#go').submit(function() {
			$.ajax({
				url : './Playlist_Controller',
				type : 'POST',
				dataType : 'json',
				data : $('#go').serialize(),
				success : function(data) {
					if (data.isValid) {
						$('#dialog').html(data.message);
						$("#playlist").load("home.jsp #playlist");
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
		
		$(".delete").click(function(){
		     var href = $(this).attr("href");
		      $.ajax({
		        type: "POST",
		        url: href,
		        dataType : 'json',
		    	success : function(data) {
					if (data.isValid) {
						$('#dialog').html(data.message);
						$("#relo").load("home.jsp #relo");
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
		
		$(".deleteAlbum").click(function(){
		     var href = $(this).attr("href");
		      $.ajax({
		        type: "POST",
		        url: href,
		        dataType : 'json',
		    	success : function(data) {
					if (data.isValid) {
						$('#dialog').html(data.message);
						$("#albm").load("home.jsp #albm");
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
		
		$('#invite').submit(function() {
			$.ajax({
				url : './Crud_user',
				type : 'POST',
				dataType : 'json',
				data : $('#invite').serialize(),
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
			<div id="leftCol">
				<div id="playListTop">
					<h3>PLAYLIST</h3>
				</div>
				<div id="tracks">
					<audio tabindex="0" id="player" controls="controls"> balise audio HTML5 inconnue</audio>
					<form id="go">
						<div id="playlist">
								<c:forEach items="${PlayList_Tracks}" var="tr">
										<li><a href="${tr.source}" onclick="loadSong()">${tr.name}</a></li>									
								</c:forEach>
								
						</div>
					</form>
				</div>
			</div>
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

