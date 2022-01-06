package de.agiehl.bgg.plays.loader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import de.agiehl.bgg.plays.model.bgg.Play;
import de.agiehl.bgg.plays.model.bgg.Players;
import de.agiehl.bgg.plays.model.bgg.Plays;
import de.agiehl.bgg.plays.model.entity.PlayEntity;
import de.agiehl.bgg.plays.model.entity.PlayerEntity;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FetchPlays {

	@Value("${bgg.api.baseurl}")
	private String bggBaseUrl;

	@Value("${bgg.api.plays.maxresult:100}")
	private int maxResultsPerPage;

	@Autowired
	private BggLoader bggLoader;

	public List<PlayEntity> fetchPlays(String bggUsername) {
		log.info("Fetch plays for {}", bggUsername);

		int pageCount = 1;
		int maxPage = 1;

		List<PlayEntity> allPlays = new ArrayList<>();
		do {
			String url = bggBaseUrl + "?username=" + bggUsername + "&page=" + pageCount;
			log.debug("URL is {}", url);
			Plays plays = bggLoader.fetchData(url, Plays.class);

			maxPage = getTotalPages(plays);
			log.info("Process plays for {}. Page {} / {}", bggUsername, pageCount, maxPage);

			Collection<? extends PlayEntity> allEntities = toEntity(plays);
			log.info("Found {} plays on page {}", allEntities.size(), pageCount);
			allPlays.addAll(allEntities);

			pageCount++;
		} while (pageCount <= maxPage);

		return allPlays;
	}

	private Collection<? extends PlayEntity> toEntity(Plays plays) {
		ArrayList<PlayEntity> allPlayEntities = new ArrayList<>();

		for (Play play : plays.getPlay()) {
			PlayEntity playEntity = new PlayEntity();
			playEntity.setBggUserId(plays.getUserid());
			playEntity.setBggUsername(plays.getUsername());
			playEntity.setComment(play.getComments());
			playEntity.setDoesntApplyToWinStatistics(toBoolean(play.getNowinstats()));
			playEntity.setIdOfTheBoardgame(play.getItem().getObjectid());
			playEntity.setIncomplete(toBoolean(play.getIncomplete()));
			playEntity.setLengthInMinutes(play.getLength());
			playEntity.setLocation(play.getLocation());
			playEntity.setNameOfTheBoardgame(play.getItem().getName());
			playEntity.setPlayedOn(play.getDate());
			playEntity.setPlayId(play.getId());
			playEntity.setQuantity(play.getQuantity());

			ArrayList<PlayerEntity> players = new ArrayList<>();
			playEntity.setPlayers(players);
			for (Players player : play.getPlayers()) {
				PlayerEntity playerEntity = new PlayerEntity();
				playerEntity.setBggUserId(player.getUserid());
				playerEntity.setBggUsername(player.getUsername());
				playerEntity.setColorOrTeamname(player.getColor());
				playerEntity.setName(player.getName());
				playerEntity.setPlay(playEntity);
				playerEntity.setPlayedThisGameTheFirstTime(toBoolean(player.getIsNew()));
				playerEntity.setRating(player.getRating());
				playerEntity.setScore(player.getScore());
				playerEntity.setStartPosition(player.getStartposition());
				playerEntity.setWonTheGame(toBoolean(player.getWin()));
				players.add(playerEntity);
			}

			allPlayEntities.add(playEntity);
		}

		return allPlayEntities;
	}

	private Boolean toBoolean(Short number) {
		if (number == null) {
			return Boolean.FALSE;
		}
		return number == 1;
	}

	private int getTotalPages(Plays plays) {
		return new BigDecimal(plays.getTotal()).divide(BigDecimal.valueOf(maxResultsPerPage), RoundingMode.UP)
				.intValue();
	}
}
