package br.com.designpattern.estruturais.proxy.service;

import br.com.designpattern.estruturais.proxy.model.UserAccount;

public interface UserService {
    UserAccount getUserAccount(Long userId);
}