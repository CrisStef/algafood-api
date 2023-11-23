package com.algaworks.algafood.api.model.mixin;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.algafood.domain.model.Cluster;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserMixin {
	@JsonIgnore
	private OffsetDateTime registrationDate;

	@JsonIgnore
	private List<Cluster> clusters = new ArrayList<>();
}
