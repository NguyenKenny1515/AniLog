package com.nguyenkenny.anilog.entity;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "appUser",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<AppAuthority> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_pic")
    private Image profilePic;

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
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(role);
        role.setAppUser(this);
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }
}
