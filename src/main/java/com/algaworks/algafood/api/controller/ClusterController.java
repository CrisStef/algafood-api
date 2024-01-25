package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.model.request.ClusterRequest;
import com.algaworks.algafood.api.model.response.ClusterResponse;
import com.algaworks.algafood.domain.service.ClusterService;

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
@RequestMapping("/clusters")
public class ClusterController {
	@Autowired
	private ClusterService clusterService;

	@GetMapping
	public List<ClusterResponse> findAll() {
		return clusterService.findAll();
	}

	@GetMapping("/{cluster_id}")
	public ClusterResponse getById(@PathVariable("cluster_id") Long id) {
		return clusterService.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClusterResponse create(@RequestBody @Valid ClusterRequest cluster) {
		return clusterService.create(cluster);
	}

	@PutMapping("/{cluster_id}")
	public ClusterResponse alter(@RequestBody @Valid ClusterRequest cluster, @PathVariable("cluster_id") Long id) {
		return clusterService.alter(cluster, id);
	}

	@DeleteMapping("/{cluster_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("cluster_id") Long id) {
		clusterService.remove(id);
	}
}