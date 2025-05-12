package com.example.scvapi.repository;

import com.example.scvapi.model.entity.Vacinacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long> {
}
