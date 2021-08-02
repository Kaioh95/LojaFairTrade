package com.app.LojaFairTrade.controller;

import com.app.LojaFairTrade.entity.AppUser;
import com.app.LojaFairTrade.service.AppUserService;
import com.app.LojaFairTrade.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/fairtradedata")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final AppUserService appUserService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping(path = "/")
    public List<AppUser> listarTodos(){
        return appUserService.listarTodos();
    }

    @PutMapping(path = "/{id}")
    public String atualizarUser(@RequestBody AppUser usuario){
        return appUserService.atualizarUsuario(usuario);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
