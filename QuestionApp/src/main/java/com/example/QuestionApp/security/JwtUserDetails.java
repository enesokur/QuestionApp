package com.example.QuestionApp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.QuestionApp.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtUserDetails implements UserDetails {
	
	private Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	
	public static JwtUserDetails create(User user) {
		List<GrantedAuthority> authoritiesList = new ArrayList<GrantedAuthority>();
		authoritiesList.add(new SimpleGrantedAuthority("user"));
		return new JwtUserDetails(user.getId(),user.getPassword(),user.getPassword(),authoritiesList);
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
