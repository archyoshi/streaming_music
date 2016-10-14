package com.streaming.dao;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.streaming.model.*;

@Remote
public interface Inter {

	public boolean authenticate(String email, String password);

	public Collection<Album> getLatestReleases();

	public Collection<Track> getLatestTrack();

	public Collection<Track> getTracksOfAlbum(int id_album);

	public Album getRelease(int id_release);

	public void addReleaseToFavorities(int id_release, User user);
	
	public Artist getArtist(int id_artist);
	
	public Collection<Album> getReleasesFromArtist(int id_artist);
	
	public Track getTrack(int id_track);
	
	public void createUser(User user);

	public User getUser(int id_user);

	public boolean emailAlreadyExists(String email);

	public boolean AlbumFavoriteAlreadyExists(int id_user, int id_album);
	
	public boolean TrackInPlaylistAlreadyExists(int id_playlist, int id_track);
	
	public User getUserByMail(String mail);
	
	public Collection<Album> getFavorities(int id_user);
		
	public Object getReleaseWithDetails(int id_album);
	
	public Collection<Playlist> getCurrentPlaylists(int id_user);

	public int createPlaylist(Playlist playlist);

	public Playlist getPlaylist(int id_playlist);
	
	public boolean playAlreadyExists(String namePlayList, int id_user);
	
	public void addTrackToPlaylist(int id_playlist, int id_track);
	
	public void deletePlaylist(int id_playlist);
	
	public void removeReleaseToFavorities(int id_release);

	public Collection<Album> getSimilarReleases(int id_style);
	
	public void updateUser(User user);
	
	public void deleteUser(int id);
	
	public void inviteUser(String email);
	
	public Collection<Artist> getLatestArtists();	
	
	public Collection<Label> getLatestLabels();
	
	public Label getLabel(int id_label);

	public Collection<Artist> getArtistsFromLabel(int id_label);
	
	public Collection<Track> getTracksOfPlaylist(int id_playlist);
	

	public Collection<Style> getAllStyles();

	public Style getStyle(int id_style);


	public Collection<User> getAllUsers();

	public void askResetPassword(int id);

	public void resetPassword(String token, String password);
	
	public void acceptInvitation(String token, String email, String password);

	public void deleteTrackFromPlaylist(int id_playlist, int index);

	
}