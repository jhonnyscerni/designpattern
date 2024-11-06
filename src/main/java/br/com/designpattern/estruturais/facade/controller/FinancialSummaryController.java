package br.com.designpattern.estruturais.facade.controller;

import br.com.designpattern.estruturais.facade.FinancialSummaryFacade;
import br.com.designpattern.estruturais.facade.dto.FinancialSummary;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financial-summary")
public class FinancialSummaryController {

    private final FinancialSummaryFacade financialSummaryFacade;

    public FinancialSummaryController(FinancialSummaryFacade financialSummaryFacade) {
        this.financialSummaryFacade = financialSummaryFacade;
    }

    @GetMapping("/{userId}")
    public FinancialSummary getFinancialSummary(@PathVariable String userId) {
        return financialSummaryFacade.getFinancialSummary(userId);
    }
}
