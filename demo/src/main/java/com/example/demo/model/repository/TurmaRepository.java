package com.example.demo.model.repository;

import com.example.demo.model.repository.entity.TurmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaEntity, Integer> {
    

    TurmaEntity findByNomeTurma(String nomeTurma);
}

    
    

    
    
      
            
