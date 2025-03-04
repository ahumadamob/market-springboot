package com.ahumadamob.market.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ahumadamob.market.entity.User;

public interface IUserService {
	public Page<User> getAll(Pageable pageable);
	public User getById(Long id);
	public boolean existsById(Long id);
	public User save(User user);
	public void deleteById(Long id);
}
