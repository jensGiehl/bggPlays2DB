package de.agiehl.bgg.plays.model.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "play")
@Data
public class PlayEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String bggUsername;

	@Column(name = "userid")
	private Integer bggUserId;

	@Column(name = "playid")
	private Long playId;

	@Column(name = "play_date")
	private LocalDate playedOn;

	@Column
	private Integer quantity;

	@Column(name = "length")
	private Integer lengthInMinutes;

	@Column
	private Boolean incomplete;

	@Column(name = "nowinstats")
	private Boolean doesntApplyToWinStatistics;

	@Column
	private String location;

	@Column(name = "play_comment")
	private String comment;

	@Column(name = "gamename")
	private String nameOfTheBoardgame;

	@Column(name = "gameid")
	private Integer idOfTheBoardgame;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "play", orphanRemoval = true, fetch = FetchType.EAGER)
	private List<PlayerEntity> players;

}
