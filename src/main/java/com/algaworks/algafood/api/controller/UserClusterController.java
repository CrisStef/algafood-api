package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.mapper.ClusterMapper;
import com.algaworks.algafood.api.model.response.ClusterResponse;
import com.algaworks.algafood.domain.model.User;
import com.algaworks.algafood.domain.service.UserService;

@RestController
@RequestMapping("/users/{user_id}/clusters")
public class UserClusterController {
	@Autowired
	private UserService userService;

	@Autowired
	private ClusterMapper clusterMapper;

	@GetMapping
	public List<ClusterResponse> findAll(@PathVariable("user_id") Long userId) {
		User user = userService.findById(userId);

		return clusterMapper.clusterListForClusterListResponse(user.getClusters());
	}

	@DeleteMapping("/{cluster_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable("user_id") Long userId, @PathVariable("cluster_id") Long clusterId) {
		userService.disassociateUserCluster(userId, clusterId);
	}

	@PutMapping("/{cluster_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associate(@PathVariable("user_id") Long userId, @PathVariable("cluster_id") Long clusterId) {
		userService.associateUserCluster(userId, clusterId);
	}
}