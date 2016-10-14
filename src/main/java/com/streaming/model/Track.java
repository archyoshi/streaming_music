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

@Entity
@Table(name = "tracks")
public class Track  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1439892852531483118L;
	private int id;
	private Album album;
	private String name;
	private String source ;
	
	private List<Track_playlist> track_playlist;

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
	@JoinColumn(name = "albumid", nullable = false)
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Column(name = "name", nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(targetEntity = Track_playlist.class, cascade = CascadeType.ALL, mappedBy = "track")
	public List<Track_playlist> getTrack_playlist() {
		return track_playlist;
	}

	public void setTrack_playlist(List<Track_playlist> track_playlist) {
		this.track_playlist = track_playlist;
	}
	
	@Column(name = "source", nullable = false, unique = true)
	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


}
