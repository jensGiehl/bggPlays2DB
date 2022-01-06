package de.agiehl.bgg.plays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.agiehl.bgg.plays.loader.FetchPlays;
import de.agiehl.bgg.plays.model.entity.PlayEntity;
import de.agiehl.bgg.plays.persistens.PlayRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BggToDatabaseService {

	@Autowired
	private PlayRepository repository;

	@Autowired
	private FetchPlays bggFetcher;

	public void fetchAllPlays(String bggUsername) {
		List<PlayEntity> fetchPlays = bggFetcher.fetchPlays(bggUsername);

		log.info("Fetched {} plays from boardgamegeek", fetchPlays.size());

		fetchPlays.forEach(repository::save);

		log.info("All plays saved!");
	}

}
