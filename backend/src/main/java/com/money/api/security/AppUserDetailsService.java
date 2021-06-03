package com.money.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.money.api.model.App_User;
import com.money.api.repository.App_UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private App_UserRepository app_userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<App_User> app_userOptional = app_userRepository.findByEmail(email);
		App_User app_user = app_userOptional.orElseThrow(() -> new UsernameNotFoundException("Invalid user and/or password."));
		return new User(email, app_user.getPassword(), getPermissions(app_user));
	}

	private Collection<? extends GrantedAuthority> getPermissions(App_User app_user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		app_user.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
		return authorities;
	}

}
