package br.com.designpattern.comportamentais.strategy.controller;

import br.com.designpattern.comportamentais.strategy.service.CalculadoraTaxaJurosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/strategy/api/emprestimos")
public class EmprestimoController {

    private final CalculadoraTaxaJurosService calculadoraTaxaJurosService;

    /**
     * curl -X GET "http://localhost:8080/api/emprestimos/calcular" \
     *      -G \
     *      --data-urlencode "perfilCliente=taxaAltaRisco" \
     *      --data-urlencode "valorEmprestimo=10000" \
     *      --data-urlencode "prazo=12"
     */

    @Autowired
    public EmprestimoController(CalculadoraTaxaJurosService calculadoraTaxaJurosService) {
        this.calculadoraTaxaJurosService = calculadoraTaxaJurosService;
    }

    @GetMapping("/calcular")
    public BigDecimal calcularTaxa(@RequestParam String perfilCliente,
                                   @RequestParam BigDecimal valorEmprestimo,
                                   @RequestParam int prazo) {
        return calculadoraTaxaJurosService.calcularTaxa(perfilCliente, valorEmprestimo, prazo);
    }
}
