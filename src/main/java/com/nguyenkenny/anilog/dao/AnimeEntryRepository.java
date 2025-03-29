package com.nguyenkenny.anilog.dao;

import com.nguyenkenny.anilog.entity.AnimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeEntryRepository extends JpaRepository<AnimeEntry, Integer> {
}
