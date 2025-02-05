package com.ahumadamob.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahumadamob.market.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public List<User> findAllByOrderByUsernameAsc();
}
