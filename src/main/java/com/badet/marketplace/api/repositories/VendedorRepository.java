package com.badet.marketplace.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badet.marketplace.api.entities.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

}
