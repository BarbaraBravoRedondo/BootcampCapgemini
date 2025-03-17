package com.example.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@Table(name="film")
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="film_id", unique=true, nullable=false)
	@Positive(message = "El ID de la película debe ser positivo.")
	private int filmId;

	@Lob
	private String description;

	@Column(name="last_update", insertable=false, updatable=false, nullable=false)
	@PastOrPresent
	private Timestamp lastUpdate;
	
	@Min(value = 1, message = "La duración de la película debe ser mayor que 0.")
	private int length;

	@Column(length=1)
	@Pattern(regexp = "^(G|PG|PG-13|R|NC-17)$", message = "La calificación debe ser una de las siguientes: G, PG, PG-13, R, NC-17.")
	private String rating;
	
	@Min(value=1901)
	@Max(value = 2025, message = "El año de estreno no puede ser mayor que el año actual.")
    @Column(name="release_year")
	private Short releaseYear;
	
	@NotBlank
	@Min(value = 1, message = "La duración del alquiler debe ser al menos 1 día.")
    @Max(value = 30, message = "La duración del alquiler no puede ser mayor a 30 días.")
    @Column(name="rental_duration", nullable=false)
	private byte rentalDuration;

	
	@NotBlank
	@Column(name="rental_rate", nullable=false, precision=4, scale=2)
	private BigDecimal rentalRate;
    
	
	@NotBlank
	@Column(name="replacement_cost", nullable=false, precision=5, scale=2)
	private BigDecimal replacementCost;
	
	
	
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9ÁáÉéÍíÓóÚúÑñ ]*$", message = "El título solo puede contener letras, numeros y espacios. No se permiten caracteres especiales.")
	@Column(nullable=false, length=128)
	private String title;

	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="language_id", nullable=false)
	@NotNull(message = "El idioma es obligatorio.")
	private Language language;

	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="original_language_id")
	private Language languageVO;

	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="film", cascade = CascadeType.ALL, orphanRemoval = true)
	@NotBlank(message = "Debe haber al menos un actor asociado a la película.")
	private List<FilmActor> filmActors;

	//bi-directional many-to-one association to FilmCategory
	@OneToMany(mappedBy="film", cascade = CascadeType.ALL, orphanRemoval = true)
	@NotBlank(message = "Debe haber al menos una categoría asociada a la película.")
	private List<FilmCategory> filmCategories;

	//bi-directional many-to-one association to Inventory
//	@OneToMany(mappedBy="film")
//	private List<Inventory> inventories;

	public Film() {
	}

	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Short getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(Short releaseYear) {
		this.releaseYear = releaseYear;
	}

	public byte getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public BigDecimal getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	public BigDecimal getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Language getLanguageVO() {
		return this.languageVO;
	}

	public void setLanguageVO(Language languageVO) {
		this.languageVO = languageVO;
	}

	public List<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(List<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	public FilmActor addFilmActor(FilmActor filmActor) {
		getFilmActors().add(filmActor);
		filmActor.setFilm(this);

		return filmActor;
	}

	public FilmActor removeFilmActor(FilmActor filmActor) {
		getFilmActors().remove(filmActor);
		filmActor.setFilm(null);

		return filmActor;
	}

	public List<FilmCategory> getFilmCategories() {
		return this.filmCategories;
	}

	public void setFilmCategories(List<FilmCategory> filmCategories) {
		this.filmCategories = filmCategories;
	}

	public FilmCategory addFilmCategory(FilmCategory filmCategory) {
		getFilmCategories().add(filmCategory);
		filmCategory.setFilm(this);

		return filmCategory;
	}

	public FilmCategory removeFilmCategory(FilmCategory filmCategory) {
		getFilmCategories().remove(filmCategory);
		filmCategory.setFilm(null);

		return filmCategory;
	}
//
//	public List<Inventory> getInventories() {
//		return this.inventories;
//	}
//
//	public void setInventories(List<Inventory> inventories) {
//		this.inventories = inventories;
//	}
//
//	public Inventory addInventory(Inventory inventory) {
//		getInventories().add(inventory);
//		inventory.setFilm(this);
//
//		return inventory;
//	}
//
//	public Inventory removeInventory(Inventory inventory) {
//		getInventories().remove(inventory);
//		inventory.setFilm(null);

//		return inventory;
	}

