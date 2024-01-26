package com.algaworks.algafood.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.request.UserRequest;
import com.algaworks.algafood.api.model.request.UserUpdateRequest;
import com.algaworks.algafood.api.model.response.UserResponse;
import com.algaworks.algafood.domain.model.User;

@Component
public class UserMapper {
	@Autowired
	private ModelMapper modelMapper;

	public User userRequestForUser(UserRequest userRequest) {
		return modelMapper.map(userRequest, User.class);
	}

	public User userUpdateRequestForUser(UserUpdateRequest userUpdateRequest) {
		return modelMapper.map(userUpdateRequest, User.class);
	}

	public void copyUserForCurrentUser(User user, User currentUser) {
		modelMapper.map(user, currentUser);
	}

	public UserResponse userForUserResponse(User user) {
		return modelMapper.map(user, UserResponse.class);
	}

	public List<UserResponse> userListForUserListResponse(List<User> user) {
		return modelMapper.map(user, new TypeToken<List<UserResponse>>(){}.getType());
	}
}