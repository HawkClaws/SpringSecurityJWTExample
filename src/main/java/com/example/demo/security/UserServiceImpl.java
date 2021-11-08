package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.infrastructure.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	// UserDetailsを取得できるRepository
	private UserRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;

		// Mockで上書き(本来はRDBを使う)
		this.repository = new MockUserRepository();
	}

	// 認証後にユーザ情報を取得するためのメソッド
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		return this.repository.findByUsername(username);
	}
}
