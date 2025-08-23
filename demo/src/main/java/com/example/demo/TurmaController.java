package com.example.demo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.TurmaDto;
import com.example.demo.service.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TurmaDto cadastrarTurma(@RequestBody TurmaDto turmaDto) {
        return turmaService.cadastrarTurma(turmaDto);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<TurmaDto> buscarTurmas() {
        return turmaService.buscarTurmas(); 
    }

    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.OK)
    public List<TurmaDto>buscarTurmaPorCurso(@RequestParam String curso) {
         return turmaService.findBycurso(curso);
    }

    @GetMapping("/{turmaId}")
    @ResponseStatus(code = HttpStatus.OK)
    public TurmaDto buscarTurma(@PathVariable int turmaId) {
        return turmaService.findById(turmaId);
    }

    @DeleteMapping("/{turmaId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirTurma(@PathVariable int turmaId) {
        turmaService.excluirTurma(turmaId);
    }
}