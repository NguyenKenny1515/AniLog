package com.nguyenkenny.anilog.dao;

import com.nguyenkenny.anilog.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

    List<AppUser> findAllByOrderByUsername();

    Optional<AppUser> findByUsername(String username);

    @Query("SELECT a FROM AppUser a LEFT JOIN FETCH a.animeEntries WHERE a.username = :username")
    AppUser findByUsernameFetchEntries(@Param("username") String username);
}
