package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.model.request.UserPasswordRequest;
import com.algaworks.algafood.api.model.request.UserRequest;
import com.algaworks.algafood.api.model.request.UserUpdateRequest;
import com.algaworks.algafood.api.model.response.UserResponse;
import com.algaworks.algafood.domain.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	public List<UserResponse> findAll() {
		return userService.findAll();
	}

	@GetMapping("/{user_id}")
	public UserResponse getById(@PathVariable("user_id") Long id) {
		return userService.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse create(@RequestBody @Valid UserRequest user) {
		return userService.create(user);
	}

	@PutMapping("/{user_id}")
	public UserResponse alter(@RequestBody @Valid UserUpdateRequest user, @PathVariable("user_id") Long id) {
		return userService.alter(user, id);
	}

	@PutMapping("/{user_id}/password")
	public UserResponse alter(@RequestBody @Valid UserPasswordRequest user, @PathVariable("user_id") Long id) {
		return userService.alterPassword(user, id);
	}

	@DeleteMapping("/{user_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("user_id") Long id) {
		userService.remove(id);
	}
}