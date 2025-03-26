package com.nguyenkenny.anilog.service;

import com.nguyenkenny.anilog.entity.AnimeEntry;
import com.nguyenkenny.anilog.entity.AppUser;

public interface AnimeEntryService {

    AnimeEntry findById(int id);

    AnimeEntry save(AnimeEntry animeEntry);

    void delete(AnimeEntry animeEntry);

    AnimeEntry createEntry(AnimeEntry animeEntry, AppUser appUser);
}
