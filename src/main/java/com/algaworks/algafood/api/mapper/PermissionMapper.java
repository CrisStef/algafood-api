package com.algaworks.algafood.api.mapper;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.response.PermissionResponse;
import com.algaworks.algafood.domain.model.Permission;

@Component
public class PermissionMapper {
	@Autowired
	private ModelMapper modelMapper;

	public List<PermissionResponse> permissionListForPermissionListResponse(Collection<Permission> permission) {
		return modelMapper.map(permission, new TypeToken<List<PermissionResponse>>(){}.getType());
	}
}