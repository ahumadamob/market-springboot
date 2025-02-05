package com.ahumadamob.market.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahumadamob.market.entity.User;
import com.ahumadamob.market.repository.UserRepository;
import com.ahumadamob.market.service.IUserService;

@Service
public class UserServiceImplJpa implements IUserService{
	
	@Autowired
	private UserRepository r;

	@Override
	public List<User> getAll() {
		return r.findAllByOrderByUsernameAsc();

	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		return (id == null)?false:r.existsById(id);
	}

	@Override
	public User save(User user) {
		return r.save(user);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
