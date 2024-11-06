package br.com.designpattern.estruturais.proxy;

import br.com.designpattern.estruturais.proxy.impl.UserServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceProxy implements UserService {
    private final UserServiceImpl userService;
    private final AuditService auditService;

    public UserServiceProxy(UserServiceImpl userService, AuditService auditService) {
        this.userService = userService;
        this.auditService = auditService;
    }

    @Override
    public UserAccount getUserAccount(Long userId) {
        // Verificação de permissão
        if (!hasPermission(userId)) {
            throw new SecurityException("Acesso negado para o usuário: " + userId);
        }

        // Registro de auditoria
        auditService.logAccess(userId);

        // Chamada ao serviço real
        return userService.getUserAccount(userId);
    }

    private boolean hasPermission(Long userId) {
        // Lógica para verificar se o usuário tem permissão
        return true; // Exemplo simplificado
    }
}
