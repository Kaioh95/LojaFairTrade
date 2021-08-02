package com.app.LojaFairTrade.service;

import com.app.LojaFairTrade.entity.AppUser;
import com.app.LojaFairTrade.entity.ConfirmationToken;
import com.app.LojaFairTrade.entity.Produto;
import com.app.LojaFairTrade.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public List<AppUser> listarTodos(){
        return appUserRepository.findAll();
    }

    public AppUser findById(Long id){
        return appUserRepository.findById(id).orElseThrow();
    }


    public String atualizarUsuario(AppUser usuario){
        boolean userExists = appUserRepository.findById(usuario.getId()).isPresent();

        if(userExists) {
            appUserRepository.save(usuario);
            return "Usuário atualizado";
        } else {
            return "O usuário especificado não existe no sistema";
        }
    }

    public String singUpUser(AppUser appUser){
        boolean userExists =  appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if(userExists){
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodePassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodePassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        // TODO: Send Email

        return token;
    }

    public int enableAppUser(String email){
        return appUserRepository.enableAppUser(email);
    }
}
