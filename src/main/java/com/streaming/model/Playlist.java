package com.streaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "playlist")
public class Playlist implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1722862574706793596L;
	private int playlistid;
	private String playlistname;
	private User user;
	private List<Track_playlist> track_playlist;

	@Id
	@Column(name = "playlistid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(int playlistid) {
		this.playlistid = playlistid;
	}

	@Column(name = "playlistname", nullable = false)
	public String getPlaylistname() {
		return playlistname;
	}

	public void setPlaylistname(String playlistname) {
		this.playlistname = playlistname;
	}

	@ManyToOne
	@JoinColumn(name = "createby", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(targetEntity = Track_playlist.class, cascade = CascadeType.ALL, mappedBy = "playlist")
	public List<Track_playlist> getTrack_playlist() {
		return track_playlist;
	}

	public void setTrack_playlist(List<Track_playlist> track_playlist) {
		this.track_playlist = track_playlist;
	}

}
