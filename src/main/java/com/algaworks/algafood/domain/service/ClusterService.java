package com.algaworks.algafood.domain.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.api.mapper.ClusterMapper;
import com.algaworks.algafood.api.model.request.ClusterRequest;
import com.algaworks.algafood.api.model.response.ClusterResponse;
import com.algaworks.algafood.domain.exception.ClusterNotFoundException;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.Cluster;
import com.algaworks.algafood.domain.model.Permission;
import com.algaworks.algafood.domain.repository.ClusterRepository;

@Service
public class ClusterService {
	@Autowired
	private ClusterRepository clusterRepository;

	@Autowired
	private PermissionService permissionService;

	private static final String MSG_CLUSTER_IN_USE = "Cluster (%d) in use and cannot be removed";

	@Autowired
	private ClusterMapper clusterMapper;

	public List<ClusterResponse> findAll() {
		return clusterMapper.clusterListForClusterListResponse(this.listAll());
	}
	
	public ClusterResponse getById(Long id) {
		return clusterMapper.clusterForClusterResponse(this.findById(id));
	}
	
	public ClusterResponse create(@Valid ClusterRequest clusterRequest) {
		Cluster cluster = clusterMapper.clusterRequestForCluster(clusterRequest);

		return clusterMapper.clusterForClusterResponse(this.save(cluster));
	}
	
	public ClusterResponse alter(@Valid ClusterRequest clusterRequest, Long id) {
		Cluster cluster = clusterMapper.clusterRequestForCluster(clusterRequest);

		return clusterMapper.clusterForClusterResponse(this.update(cluster, id));
	}

	private List<Cluster> listAll() {
		return clusterRepository.findAll();
	}

	public Cluster findById(Long id) {
		Cluster cluster = clusterRepository.findById(id)
							.orElseThrow(() -> new ClusterNotFoundException(id));

		return cluster;
	}

	@Transactional
	private Cluster save(Cluster cluster) {
		return clusterRepository.save(cluster);
	}

	@Transactional
	private Cluster update(Cluster cluster, Long id) {
		Cluster currentCluster = findById(id);

		clusterMapper.copyClusterForCurrentCluster(cluster, currentCluster);

		currentCluster = clusterRepository.save(currentCluster);

		return currentCluster;
	}

	@Transactional
	public void remove(Long id) {
		try {
			clusterRepository.deleteById(id);
			clusterRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ClusterNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_CLUSTER_IN_USE, id));
		}
	}

	@Transactional
	public void disassociateClusterPermission(Long clusterId, Long permissionId) {
		Cluster cluster = this.findById(clusterId);
		Permission permission = permissionService.findById(permissionId);
		cluster.removePermission(permission);
	}

	@Transactional
	public void associateClusterPermission(Long clusterId, Long permissionId) {
		Cluster cluster = this.findById(clusterId);
		Permission permission = permissionService.findById(permissionId);
		cluster.addPermission(permission);
	}
}