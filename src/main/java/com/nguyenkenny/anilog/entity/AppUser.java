package com.nguyenkenny.anilog.entity;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "users")
public class AppUser {

    @Id
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "created_datetime", columnDefinition = "zoned_date_time")
    private ZonedDateTime createdDatetime;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AppAuthority> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_pic")
    private Image profilePic;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKey(name = "malId")
    private Map<Integer, AnimeEntry> animeEntries;

    public AppUser() {
        this.roles = new ArrayList<>();
        this.animeEntries = new HashMap<>();
    }

    public AppUser(String username, String password, boolean enabled, ZonedDateTime createdDatetime) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.createdDatetime = createdDatetime;
        this.roles = new ArrayList<>();
        this.animeEntries = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ZonedDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(ZonedDateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public List<AppAuthority> getRoles() {
        return roles;
    }

    public void setRoles(List<AppAuthority> roles) {
        this.roles = roles;
    }

    public void addRole(AppAuthority role) {
        roles.add(role);
        role.setAppUser(this);
    }

    public Map<Integer, AnimeEntry> getAnimeEntries() {
        return animeEntries;
    }

    public void setAnimeEntries(Map<Integer, AnimeEntry> animeEntries) {
        this.animeEntries = animeEntries;
    }

    public void addAnimeEntry(AnimeEntry entry) {
        animeEntries.put(entry.getMalId(), entry);
        entry.setAppUser(this);
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }
}
