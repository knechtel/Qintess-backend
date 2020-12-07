package br.com.qintess.controller;

import br.com.qintess.DTO.CustoPopulacionalDTO;
import br.com.qintess.conf.CustoPopulacionalConfig;
import br.com.qintess.domain.Cidade;
import br.com.qintess.domain.Estado;
import br.com.qintess.service.CidadeService;
import br.com.qintess.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by maiquelknechtel on 12/5/20.
 */
@RestController
@RequestMapping("/api/estado")
public class EstadoController {
    @Autowired
    private CustoPopulacionalConfig custoPopulacionalConfig;
    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private EstadoService estadoService;

    @RequestMapping(value = "/getValor", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public CustoPopulacionalDTO getValorPopulacional(@RequestBody CustoPopulacionalDTO custoPopulacionalDTO) {
        List<Cidade> cidadeList = cidadeService.findByEstado(custoPopulacionalDTO.getIdEstado());
        Integer populacao = 0;
        Double custo = 0.0;
        Integer cidadoDesconto = 0;

        Double regra = 0.0;
        for (Cidade c : cidadeList) {
            populacao = populacao + c.getPopulacao();
        }
        if (populacao > custoPopulacionalConfig.getValorDeCorte()) {
            cidadoDesconto = populacao - custoPopulacionalConfig.getValorDeCorte();
            regra = custoReajuste(cidadoDesconto);
            custo = regra + custoPopulacionalConfig.getValorDeCorte() * custoPopulacionalConfig.getValorPessoa();
        } else {
            custo = populacao * custoPopulacionalConfig.getValorPessoa();
        }
        System.out.println("populacao " + populacao);
        System.out.println("valor da regra :" + regra);
        return new CustoPopulacionalDTO(custoPopulacionalDTO.getIdEstado(), custo);
    }

    public Double custoDesconto() {
        return 1 * (custoPopulacionalConfig.getValorPessoa() *
                (custoPopulacionalConfig.getValorDesconto() / 100));
    }

    public Double custoReajuste(Integer populaco) {
        return (populaco * custoPopulacionalConfig.getValorPessoa()) - custoDesconto();
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("imageFile") MultipartFile file) {
        if (file.isEmpty()) {
            return "No file attached";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + "lote.cvs");
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Sucesso";
    }



    @RequestMapping(method = RequestMethod.POST, value = "/import")
    public void readFile() {
        try {
            String line = null;
            BufferedReader bf = new BufferedReader(new FileReader(UPLOADED_FOLDER + "/lote.cvs"));
            while ((line = bf.readLine()) != null) {
                String columns[] = line.split(",");
                Integer idEstado = 0;
                Integer populacao = 0;
                String nome = null;
                if (columns[ID_ESTADO].equals(COD_RS) || columns[ID_ESTADO].equals("RS")) {
                    idEstado = 1;
                } else if (columns[ID_ESTADO].equals(COD_SC) || columns[ID_ESTADO].equals("SC")) {
                    idEstado = 2;
                } else if (columns[ID_ESTADO].equals(COD_PR) || columns[ID_ESTADO].equals("SC")) {
                    idEstado = 3;
                }
                nome = columns[NOME_CIDADE];
                populacao = Integer.parseInt(columns[POPULACAO]);
                Estado estado = estadoService.findByID(idEstado);
                Cidade cidade = new Cidade(estado,nome,populacao);

                List<Cidade> cidadeList = cidadeService.findByNome(cidade);
                if (cidadeList != null && cidadeList.size() >= 1) {
                } else {
                    cidadeService.save(cidade);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String UPLOADED_FOLDER = System.getProperty("user.dir") + "/temp/";
    private static Integer ID_ESTADO = 1;
    private static Integer NOME_CIDADE = 2;
    private static Integer POPULACAO = 3;
    private static String COD_RS = "1";
    private static String COD_SC = "2";
    private static String COD_PR = "3";
}
