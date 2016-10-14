package com.streaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 5197775542126980854L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "nom", nullable = false)
	private String nom;
	@Column(name = "prenom", nullable = false)
	private String prenom;
	@Column(name = "mail", nullable = false, unique = true)
	private String mail;
	@Column(name = "mot_passe", nullable = false)
	private String mot_passe;
	@OneToMany(targetEntity = Playlist.class, cascade = CascadeType.ALL, mappedBy = "user")
	private List<Playlist> playlist;
	@OneToMany(targetEntity = Album_favorites.class, cascade = CascadeType.ALL, mappedBy = "user")
	private List<Album_favorites> favAlbums;

	public User() {

	}

	public User(String nom, String prenom, String mail, String mot_passe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.mot_passe = mot_passe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMot_passe() {
		return mot_passe;
	}

	public void setMot_passe(String mot_passe) {
		this.mot_passe = mot_passe;
	}

	public List<Playlist> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<Playlist> playlist) {
		this.playlist = playlist;
	}

	public List<Album_favorites> getFavAlbums() {
		return favAlbums;
	}

	public void setFavAlbums(List<Album_favorites> favAlbums) {
		this.favAlbums = favAlbums;
	}

}
