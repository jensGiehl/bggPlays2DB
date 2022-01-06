package de.agiehl.bgg.plays.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
