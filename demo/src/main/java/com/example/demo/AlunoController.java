package com.example.demo;

import com.example.demo.dto.AlunoDto;
import com.example.demo.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos") // Endpoint base
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    /**
     * REQUISITO 5: Busca de um aluno por Id
     * GET /alunos/{idAluno}
     */
    @GetMapping("/{idAluno}")
    @ResponseStatus(HttpStatus.OK)
    public AlunoDto getAlunoById(@PathVariable int idAluno) {
        return alunoService.buscarAlunoPorId(idAluno);
    }

    /**
     * REQUISITO 6: Busca de um aluno por nome
     * GET /alunos?nome=Joao
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlunoDto> getAlunoByNome(@RequestParam String nome) {
        return alunoService.buscarAlunoPorNome(nome);
    }
}
