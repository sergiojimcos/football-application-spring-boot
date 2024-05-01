package com.clubing.application.app.service.repository;

import com.clubing.application.app.service.model.UserEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sergio Jiménez del Coso
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntry, String> {

    public UserEntry save(UserEntry userEntry);

    public UserEntry findByAccessToken(String accessToken);
}
