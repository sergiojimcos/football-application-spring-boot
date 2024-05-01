package com.clubing.application.app.service.repository;

import com.clubing.application.app.service.model.ClubEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergio Jiménez del Coso
 */

public interface ClubRepository extends JpaRepository<ClubEntry, Long> {

    public ClubEntry save(ClubEntry clubEntry);

    public void deleteById(Long id);

    public Optional<ClubEntry> findById(Long id);

    public List<ClubEntry> findAll();
}
