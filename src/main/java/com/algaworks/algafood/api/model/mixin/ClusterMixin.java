package com.algaworks.algafood.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.algaworks.algafood.domain.model.Permission;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ClusterMixin {
	@JsonIgnore
	private List<Permission> permissions = new ArrayList<>();
}
