package com.example.scvapi.repository;

import com.example.scvapi.model.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{
}