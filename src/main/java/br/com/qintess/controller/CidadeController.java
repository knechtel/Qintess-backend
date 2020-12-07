package br.com.qintess.controller;

import br.com.qintess.DTO.CidadeDTO;
import br.com.qintess.DTO.EstadoDTO;
import br.com.qintess.domain.Cidade;
import br.com.qintess.domain.Estado;
import br.com.qintess.service.CidadeService;
import br.com.qintess.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maiquelknechtel on 12/5/20.
 */
@RestController
@RequestMapping("/api/cidade")
public class CidadeController {
    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private EstadoService estadoService;

    @RequestMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public CidadeDTO save(@RequestBody CidadeDTO cidadeDTO) {
        Estado estado = estadoService.findByID(cidadeDTO.getIdEstado());
        Cidade c = cidadeDTO.toCidade(cidadeDTO);
        c.setEstado(estado);
        List<Cidade> listCidade = cidadeService.findByNome(c);
        if (listCidade != null && listCidade.size() >= 1) {
            return new CidadeDTO();
        } else {
            return new CidadeDTO().tocidadeDTO(cidadeService.save(c));
        }
    }

    @RequestMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<CidadeDTO> findAll() {
        List<CidadeDTO> cidadeList = new ArrayList<>();
        for (Cidade c : cidadeService.findAll()) {
            cidadeList.add(new CidadeDTO().tocidadeDTO(c));
        }
        return cidadeList;
    }

    @RequestMapping(value = "/findByEstado", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public List<CidadeDTO> findCidadesByEstado(@RequestBody EstadoDTO estadoDTO) {
        List<CidadeDTO> cidadeList = new ArrayList<>();
        for (Cidade c : cidadeService.findByEstado(estadoDTO.getId())) {
            cidadeList.add(new CidadeDTO().tocidadeDTO(c));
        }
        return cidadeList;
    }

    @RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public void deleteCidade(@RequestBody CidadeDTO cidadeDTO) {
        cidadeService.delete(cidadeDTO.toCidade(cidadeDTO));
    }

}
