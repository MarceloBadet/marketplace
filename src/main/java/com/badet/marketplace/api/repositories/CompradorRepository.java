package com.badet.marketplace.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badet.marketplace.api.entities.Comprador;

public interface CompradorRepository extends JpaRepository<Comprador, Long> {

}
