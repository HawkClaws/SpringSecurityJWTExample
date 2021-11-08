package com.example.demo.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.infrastructure.UserRepository;
import com.example.demo.model.Employee;

public class MockUserRepository implements UserRepository {

	private List<User> userDatas = new ArrayList<User>();
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	//DBのMockです。本来RDBを使います
	public MockUserRepository() {
		User user = new User();
		user.setId(123);
		user.setUsername("tanaka");
		user.setPassword(bCryptPasswordEncoder.encode("sdf"));
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);

		user.setRoles(new ArrayList<>(Arrays.asList("ROLE_USER")));
		userDatas.add(user);
	}

	@Override
	public User findByUsername(String username) {
		// TODO 自動生成されたメソッド・スタブ
		return userDatas.stream().filter(x -> x.getUsername().equals(username)).findFirst().get();
	}

	@Override
	public List<User> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<User> findAll(Sort sort) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<User> findAllById(Iterable<String> ids) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends User> List<S> saveAll(Iterable<S> entities) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void flush() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public <S extends User> S saveAndFlush(S entity) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<User> entities) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAllInBatch() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public User getOne(String id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public User getById(String id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends User> S save(S entity) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Optional<User> findById(String id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean existsById(String id) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public long count() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public void deleteById(String id) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(User entity) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAll(Iterable<? extends User> entities) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteAll() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public <S extends User> Optional<S> findOne(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <S extends User> long count(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public <S extends User> boolean exists(Example<S> example) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public Optional<Employee> findOne(Specification<Employee> spec) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Employee> findAll(Specification<Employee> spec) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Page<Employee> findAll(Specification<Employee> spec, Pageable pageable) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<Employee> findAll(Specification<Employee> spec, Sort sort) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public long count(Specification<Employee> spec) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}
