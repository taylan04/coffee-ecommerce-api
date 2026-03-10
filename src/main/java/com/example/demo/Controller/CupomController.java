package com.example.demo.Controller;

import com.example.demo.DTO.Cupom.CupomCreateDTO;
import com.example.demo.DTO.Cupom.CupomDTO;
import com.example.demo.DTO.Cupom.CupomUpdateDTO;
import com.example.demo.Service.CupomService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cupom")
public class CupomController {

    private final CupomService cupomService;

    public CupomController(CupomService cupomService) {
        this.cupomService = cupomService;
    }

    @GetMapping("/{id}")
    public CupomDTO findById(@PathVariable Long id) {
        return cupomService.findById(id);
    }

    @GetMapping
    public List<CupomDTO> findAll() {
        return cupomService.findAll();
    }

    @PostMapping
    public CupomDTO create(@RequestBody @Valid CupomCreateDTO cupom) {
        return cupomService.save(cupom);
    }

    @PatchMapping("/{id}")
    public CupomDTO update(@PathVariable Long id, @RequestBody @Valid CupomUpdateDTO cupom) {
        return cupomService.update(id, cupom);
    }

    //implementar autenticação de delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cupomService.delete(id);
    }

}
