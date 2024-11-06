package br.com.designpattern.estruturais.proxy.controller;

import br.com.designpattern.estruturais.proxy.model.UserAccount;
import br.com.designpattern.estruturais.proxy.service.UserService;
import br.com.designpattern.estruturais.proxy.service.UserServiceProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class UserController {
    private final UserService userService;

    public UserController(UserServiceProxy userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserAccount> getUserAccount(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserAccount(userId));
    }
}
