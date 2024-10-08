package com.clubing.application.app.service.repository;

import com.clubing.application.app.service.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sergio Jiménez del Coso
 */

public interface MatchRepository extends JpaRepository<Match, Long> {
}
