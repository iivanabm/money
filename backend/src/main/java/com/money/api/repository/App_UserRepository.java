package com.money.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.money.api.model.App_User;

@Repository
public interface App_UserRepository extends JpaRepository<App_User, Long> {

	public Optional<App_User> findByEmail(String email);
}
