package com.example.demo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.AlunoDto;
import com.example.demo.dto.TurmaDto;
import com.example.demo.service.AlunoService; 
import com.example.demo.service.TurmaService;

import java.util.List; 

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;
    private final AlunoService alunoService;

   
    public TurmaController(TurmaService turmaService, AlunoService alunoService) {
        this.turmaService = turmaService;
        this.alunoService = alunoService;
    }

    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TurmaDto cadastrarTurma(@RequestBody TurmaDto turmaDto) {
        return turmaService.cadastrarTurma(turmaDto);
    }

 
    @PostMapping("/{idTurma}") 
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<AlunoDto> cadastrarAlunosNaTurma(
            @PathVariable int idTurma, 
            @RequestBody List<AlunoDto> alunosDto) {
        return turmaService.cadastrarAlunosNaTurma(idTurma, alunosDto);
    }

    
    @PostMapping("/{idTurma}/aluno")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AlunoDto cadastrarAlunoNaTurma(
            @PathVariable int idTurma, 
            @RequestBody AlunoDto alunoDto) { 
        return turmaService.cadastrarAlunoNaTurma(idTurma, alunoDto);
    }

    
    @GetMapping("/{idTurma}/alunos")
    @ResponseStatus(code = HttpStatus.OK)
    public List<AlunoDto> getAlunosDaTurma(@PathVariable int idTurma) {
        return alunoService.buscarAlunosDaTurma(idTurma);
    }

   
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public TurmaDto getTurmaById(@PathVariable int id) {
        return turmaService.findById(id);
    }

    @GetMapping("/buscar") 
    @ResponseStatus(code = HttpStatus.OK)
    public TurmaDto getTurmaByNome(@RequestParam String nome) {
        return turmaService.findByNome(nome);
    }
}