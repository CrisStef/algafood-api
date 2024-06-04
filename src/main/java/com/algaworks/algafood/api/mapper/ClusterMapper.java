package com.algaworks.algafood.api.mapper;

import java.util.Collection;
import java.util.List;

import com.algaworks.algafood.api.model.request.ClusterRequest;
import com.algaworks.algafood.api.model.response.ClusterResponse;
import com.algaworks.algafood.domain.model.Cluster;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClusterMapper {
	@Autowired
	private ModelMapper modelMapper;

	public Cluster clusterRequestForCluster(ClusterRequest clusterRequest) {
		return modelMapper.map(clusterRequest, Cluster.class);
	}

	public void copyClusterForCurrentCluster(Cluster cluster, Cluster currentCluster) {
		modelMapper.map(cluster, currentCluster);
	}

	public ClusterResponse clusterForClusterResponse(Cluster cluster) {
		return modelMapper.map(cluster, ClusterResponse.class);
	}

	public List<ClusterResponse> clusterListForClusterListResponse(Collection<Cluster> cluster) {
		return modelMapper.map(cluster, new TypeToken<List<ClusterResponse>>(){}.getType());
	}
}