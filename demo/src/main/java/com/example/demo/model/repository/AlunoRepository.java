package com.example.demo.model.repository;

import com.example.demo.model.repository.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Integer> {

    List<AlunoEntity> findByTurmaId(int turmaId);

  
    List<AlunoEntity> findByNomeContainingIgnoreCase(String nome);
}
