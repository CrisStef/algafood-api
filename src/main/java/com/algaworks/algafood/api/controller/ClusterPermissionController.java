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

import com.algaworks.algafood.api.mapper.PermissionMapper;
import com.algaworks.algafood.api.model.response.PermissionResponse;
import com.algaworks.algafood.domain.model.Cluster;
import com.algaworks.algafood.domain.service.ClusterService;

@RestController
@RequestMapping("/clusters/{cluster_id}/permissions")
public class ClusterPermissionController {
	@Autowired
	private ClusterService clusterService;

	@Autowired
	private PermissionMapper permissionMapper;

	@GetMapping
	public List<PermissionResponse> findAll(@PathVariable("cluster_id") Long clusterId) {
		Cluster cluster = clusterService.findById(clusterId);

		return permissionMapper.permissionListForPermissionListResponse(cluster.getPermissions());
	}

	@DeleteMapping("/{permission_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable("cluster_id") Long clusterId, @PathVariable("permission_id") Long permissionId) {
		clusterService.disassociateClusterPermission(clusterId, permissionId);
	}

	@PutMapping("/{permission_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associate(@PathVariable("cluster_id") Long clusterId, @PathVariable("permission_id") Long permissionId) {
		clusterService.associateClusterPermission(clusterId, permissionId);
	}
}