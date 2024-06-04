package com.algaworks.algafood.domain.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class User {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime registrationDate;

	@ManyToMany
	@JoinTable(name = "cluster_user",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "cluster_id"))
	private Set<Cluster> clusters = new HashSet<>();

	public boolean validPassword(String password) {
		return this.getPassword().equals(password);
	}

	public boolean invalidPassword(String password) {
		return !validPassword(password);
	}

	public boolean removeCluster(Cluster cluster) {
		return getClusters().remove(cluster);
	}

	public boolean addCluster(Cluster cluster) {
		return getClusters().add(cluster);
	}
}