package com.example.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.example.domains.core.entities.AbstractEntity;
import com.example.domains.core.validations.NIF;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the actor database table.
 * 
 */
@Entity
@Table(name="actor")
@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a")
public class Actor  extends AbstractEntity<Actor> implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="actor_id", unique=true, nullable=false)
	private int actorId;

	@Column(name="first_name", nullable=false, length=45)
	@NotBlank
	@Size(max=45, min=2)
//	@NIF
	private String firstName;

	@Column(name="last_name", nullable=false, length=45)
	@Size(max=45, min=2)
//	@Pattern(regexp = "[A-Z]+", message = "Tiene que estar en mayusculas")
	private String lastName;

	@Column(name="last_update", insertable=false, updatable=false, nullable=false)
	@PastOrPresent
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="actor", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<FilmActor> filmActors = new ArrayList<>();

	public Actor() {
	}
	
	public Actor(int actorId) {
		super();
		this.actorId = actorId;
	}

	public Actor(int actorId, String firstName, String lastName) {
		super();
		setActorId(actorId);
		setFirstName(firstName);
		setLastName(lastName);
	}


	public int getActorId() {
		return this.actorId;
	}

	public void setActorId(int actorId) {
	    if (this.actorId == actorId) {
	        return;
	    }
	    this.actorId = actorId; 
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
	    if (this.firstName == null ? firstName == null : this.firstName.equals(firstName)) {
	        return; 
	    }
	    this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
	    if (this.lastName != null && this.lastName.equals(lastName)) {
	        return; 
	    }
	    this.lastName = lastName; 
	}


	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(List<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	public FilmActor addFilmActor(FilmActor filmActor) {
		getFilmActors().add(filmActor);
		filmActor.setActor(this);

		return filmActor;
	}

	public FilmActor removeFilmActor(FilmActor filmActor) {
		getFilmActors().remove(filmActor);
		filmActor.setActor(null);

		return filmActor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return actorId == other.actorId;
	}

	@Override
	public String toString() {
		return "Actor [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + ", lastUpdate="
				+ lastUpdate + "]";
	}

	public void jubilate() {
		
	}
	

	
	


	public void premioRecibido(String premio) {
		// ...
	}
}