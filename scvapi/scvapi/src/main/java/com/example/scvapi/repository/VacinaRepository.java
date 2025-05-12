package com.example.scvapi.repository;

import com.example.scvapi.model.entity.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaRepository extends JpaRepository<Vacina, Long>{
}