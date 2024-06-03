package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "Title", length = 100)
	@NotBlank
	@Size(min = 4)
	private String categoryTitle;
	@Column(name = "Description")
	@NotBlank
	@Size(min = 10)
	private String categoryDescription;
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Post> post = new ArrayList<>();
}
