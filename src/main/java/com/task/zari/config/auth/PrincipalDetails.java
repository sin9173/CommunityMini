package com.task.zari.config.auth;

import com.task.zari.dto.AuthDto;
import com.task.zari.entity.account.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class PrincipalDetails implements UserDetails {

    private AuthDto authDto;

    public PrincipalDetails(Account account) {
        this.authDto = new AuthDto(account);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> authDto.getAccountType().toString());
        return authorities;
    }

    @Override
    public String getPassword() {
        return authDto.getPassword();
    }

    @Override
    public String getUsername() {
        return authDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
