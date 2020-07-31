package com.badet.marketplace.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badet.marketplace.api.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
