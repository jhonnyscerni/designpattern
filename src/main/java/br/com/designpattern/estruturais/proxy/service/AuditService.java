package br.com.designpattern.estruturais.proxy;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService {
    public void logAccess(Long userId) {
        System.out.println("Auditando acesso ao usuário: " + userId + " em " + LocalDateTime.now());
        // Aqui, registraríamos o acesso no banco de dados ou sistema de auditoria
    }
}
