
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
				<form id=invite>
					<input type="text" name="sendInv">
					<input type="hidden" name="action" value="invite">
					<input type="submit" value="Invite User" class="boutonAdd">
				</form>
				<div id="playListTop">
					<h3>LATEST 10 TRACKS</h3>
				</div>
				<div id="tracks">
					<audio tabindex="0" id="player" controls="controls"> balise audio HTML5 inconnue</audio>
					<form id="go">
						<div id="playlist">
								<c:forEach items="${Alltracks}" var="tr">
										<li><a href="${tr.source}" onclick="loadSong()">${tr.name}</a></li>									
								</c:forEach>
							<select name="idplaylist">
								<c:forEach items="${PlayLists}" var="pl">
									<option value="${pl.playlistid}">${pl.playlistname}</option>
								</c:forEach>
							</select> 
							<select name="idtrack">
								<c:forEach items="${Alltracks}" var="tr">
									<option value="${tr.id}">${tr.name}</option>
								</c:forEach>
							</select> 
							<input type="hidden" value="addToPlaylist" name="action">
							<input type="submit" value="Add this track" class="boutonAdd" style="float:right">
						</div>
					</form>
				</div>
			</div>
			<div id="centrCol">
				<div id="albmBlock">
					<c:forEach items="${AllAlbums}" var="al">
						<div id="albmBox3">
							<div class="topCont">
								<h1>
									<img src="images/${al[2]}" width="63" height="54" alt="" />
								</h1>
								<a style="font-size:12px" href="./Albums_details?action=detailsAlbum&albumid=${al[4]}" class="headings">${al[0]}</a><span><a
									href="./Artist_details?action=artistDetails&artist=${al[3]}" class="headings">${al[1]}</a></span>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

			<div id="rightCol">
				<div id="videoBlock">
					<div id="videoBlockTop">
						<h3>My Playlists</h3>
					</div>
					<div id="videoBlockBody">
						<div class="vidBox">
							<form id="addPlayList">
								<input type="text" name="nameP" id="nameP"> <input
									type="hidden" name="action" id="action" value="creer_playlist">
								<input type="hidden" name="mail" id="mail"
									value="<%=session.getAttribute("mail")%>"> <input
									type="submit" class="boutonAdd" style="width: 100px"
									value="Add playList" />
							</form>

						</div>
						<div id="relo">
							<table>
								<c:forEach items="${PlayLists}" var="pl">
									<tr>
										<td><a href="Playlist_Controller?action=afficher&id=${pl.playlistid}" style="font-size: 13px; color: wight">${pl.playlistname}</a></td>
										<td><a href="#"><img src="images/play.png" style="width:20px;height:20px" /></a></td>
										<td><a class="delete" href="Playlist_Controller?action=delete&id=${pl.playlistid}"><img src="images/delete.png" style="width:20px;height:20px" /></a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
					<div id="videoBlockTop">
						<h3>My Favorities Albums</h3>
						<div id="albm">
							<table style="margin-left:10px">
								<c:forEach items="${Fav}" var="alb">
									<tr>
										<td><a href="Albums_details?action=detailsAlbum&albumid=${alb[2]}" style="font-size: 13px; color: wight">${alb[0]}</a></td>
										<td><a href="#"><img src="images/play.png" style="width:20px;height:20px" /></a></td>
										<td><a class="deleteAlbum" href="Playlist_Controller?action=deleteAlbum&id=${alb[1]}"><img src="images/delete.png" style="width:20px;height:20px" /></a></td>
										
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<div id="videoBlockBot"></div>
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

