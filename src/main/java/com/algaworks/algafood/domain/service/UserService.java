package com.algaworks.algafood.domain.service;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.mapper.UserMapper;
import com.algaworks.algafood.api.model.request.UserPasswordRequest;
import com.algaworks.algafood.api.model.request.UserRequest;
import com.algaworks.algafood.api.model.request.UserUpdateRequest;
import com.algaworks.algafood.api.model.response.UserResponse;
import com.algaworks.algafood.domain.exception.BusinessException;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.UserNotFoundException;
import com.algaworks.algafood.domain.model.User;
import com.algaworks.algafood.domain.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	private static final String MSG_STATE_IN_USE = "User (%d) in use and cannot be removed";

	@Autowired
	private UserMapper userMapper;

	public List<UserResponse> findAll() {
		return userMapper.userListForUserListResponse(this.listAll());
	}

	public UserResponse getById(Long id) {
		return userMapper.userForUserResponse(this.findById(id));
	}

	public UserResponse create(@Valid UserRequest userRequest) {
		User user = userMapper.userRequestForUser(userRequest);

		return userMapper.userForUserResponse(this.save(user));
	}

	public UserResponse alter(@Valid UserUpdateRequest userRequest, Long id) {
		User user = userMapper.userUpdateRequestForUser(userRequest);

		return userMapper.userForUserResponse(this.update(user, id));
	}

	public UserResponse alterPassword(@Valid UserPasswordRequest userRequest, Long id) {
		return userMapper.userForUserResponse(this.updatePassword(userRequest, id));
	}

	private List<User> listAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		User user = userRepository.findById(id)
							.orElseThrow(() -> new UserNotFoundException(id));

		return user;
	}

	@Transactional
	private User save(User user) {
		return userRepository.save(user);
	}

	@Transactional
	private User update(User user, Long id) {
		User currentUser = findById(id);

		userMapper.copyUserForCurrentUser(user, currentUser);

		currentUser = userRepository.save(currentUser);

		return currentUser;
	}

	@Transactional
	private User updatePassword(UserPasswordRequest userPasswordRequest, Long id) {
		User currentUser = findById(id);

		if (currentUser.invalidPassword(userPasswordRequest.getCurrentPassword())) {
			throw new BusinessException("Senha atual informada não coincide com a senha do usuário.");
		}

		currentUser.setPassword(userPasswordRequest.getNewPassword());
		currentUser = userRepository.save(currentUser);

		return currentUser;
	}

	@Transactional
	public void remove(Long id) {
		try {
			userRepository.deleteById(id);
			userRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_STATE_IN_USE, id));
		}
	}
}