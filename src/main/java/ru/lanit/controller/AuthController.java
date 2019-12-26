package ru.lanit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.dto.LoginRequest;
import ru.lanit.dto.LoginResponse;
import ru.lanit.service.UserService;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<?> createToken(@RequestBody LoginRequest request) throws Exception{
        userService.registerIfNotExist(request.getLogin(),request.getPassword());
        String token = userService.authenticate(request.getLogin(),request.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
