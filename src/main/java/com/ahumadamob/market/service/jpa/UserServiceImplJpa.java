package com.ahumadamob.market.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ahumadamob.market.entity.User;
import com.ahumadamob.market.repository.UserRepository;
import com.ahumadamob.market.service.IUserService;

@Service
public class UserServiceImplJpa implements IUserService{
	
	@Autowired
	private UserRepository r;

	@Override
	public Page<User> getAll(Pageable pageable) {
		return r.findAllByOrderByUsernameAsc(pageable);

	}

	@Override
	public User getById(Long id) {
		return (id==null)?null:r.findById(id).orElse(null);
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
		r.deleteById(id);		
	}

}
