package com.ahumadamob.market.service;

import java.util.List;

import com.ahumadamob.market.entity.User;

public interface IUserService {
	public List<User> getAll();
	public User getById(Long id);
	public boolean existsById(Long id);
	public User save(User user);
	public void deleteById(Long id);
}
