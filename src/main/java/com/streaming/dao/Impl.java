package com.streaming.dao;

import java.util.Collection;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.streaming.model.Album;
import com.streaming.model.Album_favorites;
import com.streaming.model.Artist;
import com.streaming.model.Label;
import com.streaming.model.Playlist;
import com.streaming.model.Style;
import com.streaming.model.Track;
import com.streaming.model.Track_playlist;
import com.streaming.model.User;

@Stateless(mappedName = "Stream")
public class Impl implements Inter {

	@PersistenceContext(unitName = "getstarted")
	private EntityManager em;

	public void createUser(User user) {
		if (emailAlreadyExists(user.getMail())) {
			System.out.println("Nop");
		} else {
			em.persist(user);
		}
	}

	public boolean authenticate(String mail, String mot_passe) {
		try {
			Query q = em
					.createQuery("select us from User us where us.mail = :mail and us.mot_passe = :mot_passe");
			q.setParameter("mail", mail);
			q.setParameter("mot_passe", mot_passe);
			q.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	public void updateUser(User user) {
		em.merge(user);
	}

	public User getUser(int id_user) {
		return em.find(User.class, id_user);
	}

	public User getUserByMail(String mail) {
		Query q = em
				.createQuery("select us from User us where us.mail = :mail");
		q.setParameter("mail", mail);
		return (User) q.getSingleResult();
	}

	public void deleteUser(int id) {
		em.remove(getUser(id));
	}

	public Album getRelease(int id_release) {
		return em.find(Album.class, id_release);
	}

	public Label getLabel(int id_label) {
		return em.find(Label.class, id_label);
	}

	public Artist getArtist(int id_artist) {
		return em.find(Artist.class, id_artist);
	}

	public Style getStyle(int id_style) {
		return em.find(Style.class, id_style);
	}

	public void deletePlaylist(int id_playlist) {
		em.remove(getPlaylist(id_playlist));
	}

	public Playlist getPlaylist(int id_playlist) {
		return em.find(Playlist.class, id_playlist);
	}

	@SuppressWarnings("unchecked")
	public Collection<User> getAllUsers() {
		Query query = em.createQuery("SELECT e FROM User e");
		return (Collection<User>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Style> getAllStyles() {
		Query query = em.createQuery("SELECT e FROM Style e");
		return (Collection<Style>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Track> getLatestTrack() {
		Query query = em
				.createQuery("SELECT e FROM Track e Order by e.id desc");
		query.setMaxResults(7);
		return (Collection<Track>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Track> getTracksOfAlbum(int id_album) {
		Query query = em.createQuery("SELECT e FROM Track e where e.album=:al");
		query.setParameter("al", getRelease(id_album));
		return (Collection<Track>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Album> getLatestReleases() {

		Query query = em
				.createQuery("select ab.name, at.name, ab.picture, at.id, ab.id FROM Album ab LEFT JOIN FETCH Artist at where at.id= ab.artist.id");
		query.setMaxResults(25);
		return (Collection<Album>) query.getResultList();
	}

	public Object getReleaseWithDetails(int id_album) {
		Query query = em
				.createQuery("select ab.name, at.name, ab.picture, at.id, ab.id FROM Album ab LEFT JOIN Artist at ON (ab.id=at.id) where ab.id = :id_album");
		query.setParameter("id_album", id_album);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Playlist> getCurrentPlaylists(int id_user) {
		Query q = em
				.createQuery("select us from Playlist us where us.user = :id_user");
		q.setParameter("id_user", getUser(id_user));
		return q.getResultList();
	}

	public void addTrackToPlaylist(int id_playlist, int id_track) {
		Track_playlist t = new Track_playlist();
		t.setPlaylist(getPlaylist(id_playlist));
		t.setTrack(getTrack(id_track));
		em.persist(t);
	}

	@SuppressWarnings("unchecked")
	public Collection<Artist> getLatestArtists() {
		Query query = em
				.createQuery("SELECT e FROM Artist e Order by e.id desc");
		return (Collection<Artist>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Label> getLatestLabels() {
		Query query = em
				.createQuery("SELECT e FROM Label e Order by e.id desc");
		return (Collection<Label>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Artist> getArtistsFromLabel(int id_label) {
		Query query = em
				.createQuery("SELECT DISTINCT at.id, at.name, at.description FROM Artist at LEFT JOIN Album ab LEFT JOIN Label lab "
						+ "WHERE (at.id= ab.artist.id)"
						+ " AND (ab.label.id= lab.id) AND (lab.id= :id_label)");
		query.setParameter("id_label", id_label);
		return query.getResultList();
	}

	public int createPlaylist(Playlist playlist) {
		em.persist(playlist);
		return 1;
	}

	public Track getTrack(int id_track) {
		return em.find(Track.class, id_track);
	}

	@SuppressWarnings("unchecked")
	public Collection<Album> getFavorities(int id_user) {
		Query q = em
				.createQuery("select ab.name,af.id,ab.id from Album_favorites af LEFT JOIN Album ab where af.user = :id_user and ab.id=af.album.id");
		q.setParameter("id_user", getUser(id_user));
		return q.getResultList();
	}

	public void addReleaseToFavorities(int id_release, User user) {
		Album_favorites af = new Album_favorites();
		af.setAlbum(getRelease(id_release));
		af.setUser(user);
		em.persist(af);
	}

	public boolean emailAlreadyExists(String email) {
		Query checkEmailExists = em
				.createQuery("SELECT us FROM User us WHERE us.mail = :mail");
		checkEmailExists.setParameter("mail", email);
		if (checkEmailExists.getResultList().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean AlbumFavoriteAlreadyExists(int id_user, int id_album) {
		Query q = em
				.createQuery("SELECT us FROM Album_favorites us WHERE us.album=:album and us.user=:user");
		q.setParameter("album", getRelease(id_album));
		q.setParameter("user", getUser(id_user));
		if (q.getResultList().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean playAlreadyExists(String namePlayList, int id_user) {
		Query checkPlayListExists = em
				.createQuery("SELECT us FROM Playlist us WHERE us.playlistname = :namePlayList and us.user = :id_user");
		checkPlayListExists.setParameter("id_user", getUser(id_user));
		checkPlayListExists.setParameter("namePlayList", namePlayList);
		if (checkPlayListExists.getResultList().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean TrackInPlaylistAlreadyExists(int id_playlist, int id_track) {
		Query q = em
				.createQuery("SELECT us FROM Track_playlist us WHERE us.playlist=:id_playlist and us.track=:id_track");
		q.setParameter("id_playlist", getPlaylist(id_playlist));
		q.setParameter("id_track", getTrack(id_track));
		if (q.getResultList().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Album> getReleasesFromArtist(int id_artist) {
		Query query = em
				.createQuery("SELECT ab.name, at.name, ab.picture, at.id, ab.id FROM Album ab LEFT JOIN Artist at where ab.artist=:id_artist and at.id=:id");
		query.setParameter("id", id_artist);
		query.setParameter("id_artist", getArtist(id_artist));
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Album> getSimilarReleases(int id_style) {
		Query query = em
				.createQuery("SELECT ab.name, at.name, ab.picture, at.id, ab.id FROM Album ab LEFT JOIN Artist at where ab.style.id=:id_style and ab.artist.id=at.id");
		query.setParameter("id_style", id_style);
		return query.getResultList();
	}

	public void removeReleaseToFavorities(int id_release) {
		Album_favorites af = em.find(Album_favorites.class, id_release);
		em.remove(af);
	}

	public void inviteUser(String email) {
		String to = email;
		String from = "web@gmail.com";
		String host = "localhost";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.auth", "false");
		properties.setProperty("mail.smtp.port", "25");

		Session session = Session.getDefaultInstance(properties);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject("This is the Subject Line!");
			message.setText("This is actual message");
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public void acceptInvitation(String token, String email, String password) {
	}

	public void askResetPassword(int id) {
	}

	public void resetPassword(String token, String password) {
	}

	public void deleteTrackFromPlaylist(int id_playlist, int index) {
	}

	@SuppressWarnings("unchecked")
	public Collection<Track> getTracksOfPlaylist(int id_playlist) {
		Query query = em
				.createQuery("SELECT tr FROM Track tr LEFT JOIN Track_playlist pc where tr.id=pc.track.id and pc.playlist.playlistid=:id_playlist");
		query.setParameter("id_playlist", id_playlist); 
		return (Collection<Track>) query.getResultList();
	}
}
