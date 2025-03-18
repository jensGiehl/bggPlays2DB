package de.agiehl.bgg.plays.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "player")
@Data
public class PlayerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "play_id", referencedColumnName = "id") })
	private PlayEntity play;

	@Column(name = "username")
	private String bggUsername;

	@Column(name = "userid")
	private Integer bggUserId;

	@Column
	private String name;

	@Column(name = "start_position")
	private String startPosition;

	@Column(name = "color")
	private String colorOrTeamname;

	@Column
	private Double score;

	@Column(name = "isnew")
	private Boolean playedThisGameTheFirstTime;

	@Column
	private Integer rating;

	@Column(name = "win")
	private Boolean wonTheGame;

}
