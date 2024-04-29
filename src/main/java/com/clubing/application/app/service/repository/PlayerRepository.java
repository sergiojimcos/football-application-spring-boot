package com.clubing.application.app.service.repository;

import com.clubing.application.app.service.model.PlayerEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergio Jiménez del Coso
 */

public interface PlayerRepository extends JpaRepository<PlayerEntry, Long> {

    public PlayerEntry save(PlayerEntry playerEntry);

    public void deleteById(Long id);

    public Optional<PlayerEntry> findById(Long id);

    public List<PlayerEntry> findAll();
}
