package com.example.demo.service;

import com.example.demo.dto.TurmaDto;
import com.example.demo.model.repository.TurmaRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TurmaService {
    
    private final TurmaRepository turmaRepository;

    public TurmaService (TurmaRepository turmaRepository){
        this.turmaRepository = turmaRepository;
    }

    public TurmaDto cadastrarTurma(TurmaDto turmaDto){
        return turmaRepository.save(turmaDto);
    }
    
    public List<TurmaDto> buscarTurmas() {
        return turmaRepository.buscarTurmas();
    }
    
    public TurmaDto findById(int turmaId) {
        return turmaRepository.findById(turmaId);
    }

    public List <TurmaDto> findBycurso(String curso) {
        return turmaRepository.findBycurso(curso);
    }

    public void excluirTurma(int turmaId) {
        turmaRepository.deleteById(turmaId);
    }
}