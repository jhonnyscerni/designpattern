package br.com.designpattern.criacionais.singleton;

public class Main {
    public static void main(String[] args) {
        // Usando a instância única do Logger para gravar logs
        Logger logger = Logger.getInstancia();

        logger.log("Iniciando o sistema de e-commerce");

        // Em outra parte do código, acessando o mesmo Logger
        Logger outroLogger = Logger.getInstancia();
        outroLogger.log("Processando uma nova venda");

        // Verificando que é a mesma instância
        if (logger == outroLogger) {
            System.out.println("Logger é uma única instância (Singleton)!");
        }
    }
}
