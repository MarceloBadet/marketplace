package com.badet.marketplace.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.badet.marketplace.api.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

}
