package com.streaming.model;


import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "albums")
public class Album implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3613156495676021469L;
	private int id;
	private String name;
	private Date releasedate;
	private String picture;
	private Artist artist;
	private Label label;
	private Style style;
	private List<Track> tracks;
	private List<Album_favorites> album_favorites ;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "releasedate", nullable = false)
	public Date getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}

	@Column(name = "picture", nullable = false)
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@ManyToOne
	@JoinColumn(name = "labelid", nullable = false)
	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	@ManyToOne
	@JoinColumn(name = "artistid", nullable = false)
	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	@OneToMany(targetEntity = Track.class, cascade = CascadeType.ALL, mappedBy = "album")
	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	
	
	@OneToMany(targetEntity = Album_favorites.class, cascade = CascadeType.ALL, mappedBy = "album")
	public List<Album_favorites> getAlbum_favorites() {
		return album_favorites;
	}

	public void setAlbum_favorites(List<Album_favorites> album_favorites) {
		this.album_favorites = album_favorites;
	}

	@ManyToOne
	@JoinColumn(name = "styleid", nullable = false)
	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}
}
