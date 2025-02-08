package com.ahumadamob.market.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ahumadamob.market.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public Page<User> findAllByOrderByUsernameAsc(Pageable pageable);
}
