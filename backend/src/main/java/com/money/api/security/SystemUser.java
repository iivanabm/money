package com.money.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.money.api.model.App_User;

public class SystemUser extends User{

	private static final long serialVersionUID = 1L;
	
	private App_User app_user;

	public SystemUser(App_User app_user, Collection<? extends GrantedAuthority> authorities) {
		super(app_user.getEmail(), app_user.getPassword(), authorities);
		this.app_user = app_user;
	}
	
	public App_User getApp_User() {
		return app_user;
	}

}
