package br.com.qintess.controller;

import br.com.qintess.DTO.CidadeDTO;
import br.com.qintess.DTO.CustoPopulacionalDTO;
import br.com.qintess.conf.CustoPopulacionalConfig;
import br.com.qintess.domain.Cidade;
import br.com.qintess.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "/getValor", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public CustoPopulacionalDTO getValorPopulacional(@RequestBody CustoPopulacionalDTO custoPopulacionalDTO) {
        //estado 2
        System.out.println(custoPopulacionalConfig.getValorDeCorte());
        System.out.println(custoPopulacionalConfig.getValorPessoa());

        List<Cidade> cidadeList = cidadeService.findByEstado(custoPopulacionalDTO.getIdEstado());

        Integer totalPopulacao = 0;
        Double custo = 0.0;
        Double custoBruto = 0.0;
        Integer populacaoValorCorte = 0;
        Double desconto = 0.0;

        for (Cidade c : cidadeList) {
            totalPopulacao = totalPopulacao + c.getPopulacao();
        }

        if (totalPopulacao > custoPopulacionalConfig.getValorDeCorte()) {
            custoBruto = totalPopulacao * custoPopulacionalConfig.getValorPessoa();
            Double descAux = custoBruto * custoPopulacionalConfig.getValorDesconto() / 100;
            desconto =descAux;
            custo = custoBruto - descAux;
        } else {
            custo = totalPopulacao * custoPopulacionalConfig.getValorPessoa();
        }
        System.out.println("total de pessoas: "+totalPopulacao);
        System.out.println("custo : "+custo);
        System.out.println("custo bruto : "+custoBruto);
        System.out.println("desconto : "+desconto);

        return new CustoPopulacionalDTO(2,custo);
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
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Sucesso";
    }
    private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"/temp/";

}
