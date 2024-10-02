package com.clubing.application.app.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Time matchDate;

    @ManyToOne
    @JoinColumn(name = "club_entry_local_id")
    private ClubEntry localClubEntry;

    @ManyToOne
    @JoinColumn(name = "club_entry_visitant_id")
    private ClubEntry visitantClubEntry;

}
