package com.streaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "artists",uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
public class Artist implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1832906333555867459L;
	private int id;
	private String name;
	private String description;
	private int years_active;
	private List<Album> albums;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "years_active", nullable = false)
	public int getYears_active() {
		return years_active;
	}

	public void setYears_active(int years_active) {
		this.years_active = years_active;
	}

	@OneToMany(targetEntity = Album.class, cascade = CascadeType.ALL, mappedBy = "artist")
	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

}
