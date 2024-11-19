package br.com.designpattern.comportamentais.command.model;

public class TransferRequest {
    private String type; // Tipo de transferência: BALANCE ou CREDIT
    private String accountFrom; // Conta de origem
    private String accountTo;   // Conta de destino
    private Double amount;      // Valor da transferência

    // Construtor vazio para deserialização
    public TransferRequest() {
    }

    // Construtor completo
    public TransferRequest(String type, String accountFrom, String accountTo, Double amount) {
        this.type = type;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    // Getters e Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "type='" + type + '\'' +
                ", accountFrom='" + accountFrom + '\'' +
                ", accountTo='" + accountTo + '\'' +
                ", amount=" + amount +
                '}';
    }
}

