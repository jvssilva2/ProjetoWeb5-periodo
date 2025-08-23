package com.example.demo.model.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.dto.TurmaDto;

@Repository
public class TurmaRepository {
    
    final List<TurmaDto> turmas = new ArrayList<>();

    public  TurmaDto save(TurmaDto turmaDto) {
        turmaDto.setId(turmas.size() + 1);
        turmas.add(turmaDto);
        return turmaDto;
    }

    public List<TurmaDto> buscarTurmas() {
        return turmas;
    }

    public TurmaDto findById(int turmaId) {
        for(TurmaDto turma : turmas) {
            if(turma.getId() == turmaId){
                return turma;
            }
        }
        throw new RuntimeException("Turma não encontrada com o ID" + turmaId);
    }

    public TurmaDto findByperiodo(String periodo) {
        for(TurmaDto turma : turmas) {
            if(turma.getPeriodo().equalsIgnoreCase(periodo)){
                return turma;
            }
        }
        throw new RuntimeException("Turma não encontrada com o periodo" + periodo);
    }

    public List<TurmaDto> findBycurso(String curso) {
        List<TurmaDto> resultado = new ArrayList<>();
        for(TurmaDto turma : turmas) {
            if(turma.getCurso().equalsIgnoreCase(curso)){
                resultado.add(turma);
            }
        }
        return resultado;
    }
    public void deleteById(int turmaId) {
        for (int i = 0; i < turmas.size(); i++) {
            if (turmas.get(i).getId() == turmaId) {
                turmas.remove(i);
                return;
            }
        }
    }
}
