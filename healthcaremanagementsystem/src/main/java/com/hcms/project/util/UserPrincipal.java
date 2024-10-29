package com.hcms.project.util;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hcms.project.entity.Doctor;
import com.hcms.project.entity.Patient;

public class UserPrincipal implements UserDetails {

    private String username;
    private String password;
    private String role;

    public UserPrincipal(Doctor doctor) {
        this.username = doctor.getDoctorUsername();
        this.password = doctor.getDoctorPassword();
        this.role = doctor.getRole();
    }

    public UserPrincipal(Patient patient) {
        this.username = patient.getPatientUsername();
        this.password = patient.getPatientPassword();
        this.role = patient.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() { return password; }
    @Override
    public String getUsername() { return username; }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}