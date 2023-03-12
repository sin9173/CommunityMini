package com.task.zari.auth;

import com.task.zari.dto.AuthDto;
import com.task.zari.entity.account.Account;
import com.task.zari.entity.account.AccountType;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class PrincipalDetails implements UserDetails { //계정 인증 정보

    private AuthDto authDto; //계정 DTO

    public PrincipalDetails(Account account) {
        this.authDto = new AuthDto(account);
    }

    public PrincipalDetails(String username, AccountType accountType) {
        this.authDto = new AuthDto(username, accountType);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> authDto.getAccountType().toString().toUpperCase());
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
