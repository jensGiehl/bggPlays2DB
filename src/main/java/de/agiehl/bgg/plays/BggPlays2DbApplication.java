package de.agiehl.bgg.plays;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.agiehl.bgg.plays.service.BggToDatabaseService;

@SpringBootApplication
public class BggPlays2DbApplication implements CommandLineRunner {

	@Value("${bggusernames}")
	private String bggusernames;

	@Autowired
	private BggToDatabaseService fetchService;

	public static void main(String[] args) {
		SpringApplication.run(BggPlays2DbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		asList(bggusernames.split(",")).stream().map(String::trim).forEach(fetchService::fetchAllPlays);
	}

}
