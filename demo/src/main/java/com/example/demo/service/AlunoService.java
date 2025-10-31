package com.example.demo.service;

import com.example.demo.dto.AlunoDto;
import com.example.demo.model.repository.AlunoRepository;
import com.example.demo.model.repository.entity.AlunoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<AlunoDto> buscarAlunosDaTurma(int idTurma) {
        List<AlunoEntity> alunos = alunoRepository.findByTurmaId(idTurma);
        
        return alunos.stream()
                .map(this::toAlunoDto)
                .collect(Collectors.toList());
    }

    
    public AlunoDto buscarAlunoPorId(int idAluno) {
        AlunoEntity aluno = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new RuntimeException("Aluno nao encontrado"));
        return toAlunoDto(aluno);
    }

    
     
    public List<AlunoDto> buscarAlunoPorNome(String nome) {
        List<AlunoEntity> alunos = alunoRepository.findByNomeContainingIgnoreCase(nome);
        if (alunos.isEmpty()) {
            throw new RuntimeException("Nenhum aluno encontrado com o nome: " + nome);
        }
        return alunos.stream()
                .map(this::toAlunoDto)
                .collect(Collectors.toList());
    }

    
    private AlunoDto toAlunoDto(AlunoEntity entity) {
        AlunoDto dto = new AlunoDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;
    }
}
