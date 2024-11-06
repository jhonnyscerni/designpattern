package br.com.designpattern.estruturais.proxy.impl;

import br.com.designpattern.estruturais.proxy.model.UserAccount;
import br.com.designpattern.estruturais.proxy.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserAccount getUserAccount(Long userId) {
        // Aqui, você buscaria as informações do usuário no banco de dados.
        return new UserAccount(userId, "John Doe", "1234-5678-9000", 1500.00);
    }
}
