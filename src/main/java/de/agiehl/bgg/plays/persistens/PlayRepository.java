package de.agiehl.bgg.plays.persistens;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.agiehl.bgg.plays.model.entity.PlayEntity;

@Repository
public interface PlayRepository extends CrudRepository<PlayEntity, Long> {

}
