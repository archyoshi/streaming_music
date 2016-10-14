package com.streaming.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "track_playlist")
public class Track_playlist implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4185966354523452220L;
	private int id;
	private Playlist playlist;
	private Track track;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "playlistid", nullable = false)
	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	@ManyToOne
	@JoinColumn(name = "trackid", nullable = false)
	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

}
