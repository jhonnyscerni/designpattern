package br.com.designpattern.criacionais.singleton;

// Classe Singleton para o Sistema de Log
public class Logger {

    // Instância única da classe Logger
    private static Logger instanciaUnica;

    // Construtor privado para evitar criação de múltiplas instâncias
    private Logger() {
        // Inicializações que precisem ser feitas apenas uma vez
    }

    // Método que retorna a única instância de Logger
    public static Logger getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Logger();
        }
        return instanciaUnica;
    }

    // Método para gravar uma mensagem de log
    public void log(String mensagem) {
        System.out.println("LOG: " + mensagem);
        // Aqui você poderia salvar no arquivo ou enviar para um sistema de monitoramento
    }
}
