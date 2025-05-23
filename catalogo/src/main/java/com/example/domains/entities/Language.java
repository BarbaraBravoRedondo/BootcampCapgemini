package com.example.domains.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import com.example.domains.core.entities.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="language")
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language  extends AbstractEntity<Language> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static class Partial {}
    public static class Complete extends Partial {}

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="language_id")
    @JsonProperty("id")
    @JsonView(Language.Partial.class)

    private int languageId;

    @Column(name="last_update", insertable = false, updatable = false)
    @JsonView(Language.Complete.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonProperty("ultimaModificacion")
    private Timestamp lastUpdate;

    @NotBlank
    @Size(max=20)
    @JsonProperty("idioma")
    @JsonView(Language.Partial.class)
    private String name;


    @OneToMany(mappedBy="language")
    @JsonIgnore
   
    private List<Film> films;

   
    @OneToMany(mappedBy="languageVO")
 
    @JsonIgnore
    private List<Film> filmsVO;

    public Language() {}

    public Language(int languageId) {
        this.languageId = languageId;
    }

    public Language(int languageId, String name) {
        this.languageId = languageId;
        this.name = name;
    }

    public int getLanguageId() {
        return this.languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Film> getFilms() {
        return this.films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public Film addFilm(Film film) {
        getFilms().add(film);
        film.setLanguage(this);
        return film;
    }

    public Film removeFilm(Film film) {
        getFilms().remove(film);
        film.setLanguage(null);
        return film;
    }

    public List<Film> getFilmsVO() {
        return this.filmsVO;
    }

    public void setFilmsVO(List<Film> filmsVO) {
        this.filmsVO = filmsVO;
    }

    public Film addFilmsVO(Film filmsVO) {
        getFilmsVO().add(filmsVO);
        filmsVO.setLanguageVO(this);
        return filmsVO;
    }

    public Film removeFilmsVO(Film filmsVO) {
        getFilmsVO().remove(filmsVO);
        filmsVO.setLanguageVO(null);
        return filmsVO;
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Language o)
            return languageId == o.languageId;
        else
            return false;
    }

    @Override
    public String toString() {
        return "Language [languageId=" + languageId + ", name=" + name + "]";
    }
}
