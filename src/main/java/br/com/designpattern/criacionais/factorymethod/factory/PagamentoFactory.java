package br.com.designpattern.criacionais.factorymethod.factory;

import br.com.designpattern.criacionais.factorymethod.model.Pagamento;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class PagamentoFactory {

    private final Map<String, Pagamento> pagamentos;

    public Pagamento criarPagamento(String tipo) {
        return pagamentos.get(tipo.toLowerCase());
    }
}
