package com.example.demo.Service.Implement;

import com.example.demo.DTO.Cupom.CupomCreateDTO;
import com.example.demo.DTO.Cupom.CupomDTO;
import com.example.demo.DTO.Cupom.CupomUpdateDTO;
import com.example.demo.Exception.RecursoNaoEncontradoExcecao;
import com.example.demo.Model.Cupom;
import com.example.demo.Repository.CupomRepository;
import com.example.demo.Service.CupomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CupomServiceImpl implements CupomService {

    private final CupomRepository cupomRepository;

    public CupomServiceImpl(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    @Override
    public CupomDTO findById(Long id) {
        Cupom cupom =  cupomRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Cupom não encontrado"));

        return new CupomDTO(cupom);
    }

    @Override
    public List<CupomDTO> findAll() {
        List<Cupom> cupons = cupomRepository.findAll();
        List<CupomDTO> cupomDTOS = new ArrayList<>();
        for (Cupom cupom : cupons) {
            cupomDTOS.add(new CupomDTO(cupom));
        }

        return cupomDTOS;
    }

    @Override
    public CupomDTO save(CupomCreateDTO dto) {
        Cupom cupom = new Cupom(dto);
        return new CupomDTO(cupomRepository.save(cupom));
    }

    @Override
    public CupomDTO update(Long id, CupomUpdateDTO novo) {
        Cupom existente = cupomRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Cupom não encontrado"));

        if (novo.codigo() != null) {
            existente.setCodigo(novo.codigo());
        }

        if (novo.tipo() != null) {
            existente.setTipo(novo.tipo());
        }

        if (novo.estado() != null) {
            existente.setEstado(novo.estado());
        }

        if (novo.valor() != null) {
            existente.setValor(novo.valor());
        }

        return new CupomDTO(cupomRepository.save(existente));
    }


    @Override
    public void delete(Long id) {
        cupomRepository.deleteById(id);
    }
}
