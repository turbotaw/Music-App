package com.music.musicapp.util.data_access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotifyTokenRepository extends JpaRepository<SpotifyTokenTable, Long> {
}
