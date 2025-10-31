package com.example.demo.service;

import com.example.demo.dto.AlunoDto;
import com.example.demo.dto.TurmaDto;
import java.util.List;

import com.example.demo.model.repository.AlunoRepository; 
import com.example.demo.model.repository.TurmaRepository;
import com.example.demo.model.repository.entity.AlunoEntity;
import com.example.demo.model.repository.entity.TurmaEntity;

import java.util.stream.Collectors; 

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

@Service
public class TurmaService {
    
    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository; 

   
    public TurmaService(TurmaRepository turmaRepository, AlunoRepository alunoRepository) {
        this.turmaRepository = turmaRepository;
        this.alunoRepository = alunoRepository;
    }

   
    @Transactional 
    public TurmaDto cadastrarTurma(TurmaDto turmaDto) {
        final TurmaEntity turmaEntity = new TurmaEntity();
        turmaEntity.setNomeTurma(turmaDto.getNome());
        turmaEntity.setCurso(turmaDto.getCurso());
        turmaEntity.setPeriodo(turmaDto.getPeriodo());

        
        if (turmaDto.getAlunos() != null && !turmaDto.getAlunos().isEmpty()) {
            for (AlunoDto alunoDto : turmaDto.getAlunos()) {
                AlunoEntity alunoEntity = new AlunoEntity();
                alunoEntity.setNome(alunoDto.getNome());
                
                turmaEntity.addAluno(alunoEntity); 
            }
        }
        
        final TurmaEntity turmaCadastrada = turmaRepository.save(turmaEntity);
        
        
        return toTurmaDto(turmaCadastrada);
    }


    @Transactional
    public List<AlunoDto> cadastrarAlunosNaTurma(int idTurma, List<AlunoDto> alunosDto) {
        TurmaEntity turmaEntity = turmaRepository.findById(idTurma)
                .orElseThrow(() -> new RuntimeException("Turma nao encontrada"));

        List<AlunoEntity> novosAlunos = alunosDto.stream().map(dto -> {
            AlunoEntity alunoEntity = new AlunoEntity();
            alunoEntity.setNome(dto.getNome());
            alunoEntity.setTurma(turmaEntity); 
            return alunoEntity;
        }).collect(Collectors.toList());

        
        List<AlunoEntity> alunosSalvos = alunoRepository.saveAll(novosAlunos);

        return alunosSalvos.stream()
                .map(this::toAlunoDto)
                .collect(Collectors.toList());
    }

    
     
    @Transactional
    public AlunoDto cadastrarAlunoNaTurma(int idTurma, AlunoDto alunoDto) {
        TurmaEntity turmaEntity = turmaRepository.findById(idTurma)
                .orElseThrow(() -> new RuntimeException("Turma nao encontrada"));

        AlunoEntity alunoEntity = new AlunoEntity();
        alunoEntity.setNome(alunoDto.getNome());
        alunoEntity.setTurma(turmaEntity); 

        AlunoEntity alunoSalvo = alunoRepository.save(alunoEntity);

        return toAlunoDto(alunoSalvo);
    }

    

    public TurmaDto findById(int turmaId) {
        final TurmaEntity turmaEntity = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma nao encontrada"));
        
        return toTurmaDto(turmaEntity); 
    }

    public TurmaDto findByNome(String nome) {
        
        final TurmaEntity turma = turmaRepository.findByNomeTurma(nome);
        if (turma == null) {
            throw new RuntimeException("Turma nao encontrada");
        }
        return toTurmaDto(turma); 
    }
    

    private AlunoDto toAlunoDto(AlunoEntity entity) {
        AlunoDto dto = new AlunoDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;
    }

    private TurmaDto toTurmaDto(TurmaEntity entity) {
        TurmaDto dto = new TurmaDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNomeTurma());
        dto.setCurso(entity.getCurso());
        dto.setPeriodo(entity.getPeriodo());
        
        if (entity.getAlunos() != null) {
            dto.setAlunos(entity.getAlunos().stream()
                    .map(this::toAlunoDto)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
