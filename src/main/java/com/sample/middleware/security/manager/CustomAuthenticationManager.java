package com.sample.middleware.security.manager;


import com.sample.middleware.repository.MerchantDetailsRepositoryTable;
import com.sample.middleware.utils.Encryptor;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    MerchantDetailsRepositoryTable merchantDetailsRepositoryTable;
    Encryptor encryptor;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var userFromDb = merchantDetailsRepositoryTable.findByEmail(authentication.getName());
        if (userFromDb.isEmpty()) {
            throw new BadCredentialsException("");
        }
        var password = userFromDb.get().getPassword();
        //decrypt password here
        var isValid = encryptor.passwordIsValid(password, authentication.getCredentials().toString());
        if (!isValid) {
            throw new BadCredentialsException("Password wrong");
        }
//        if (!Utils.passwordEncoder().matches(authentication.getCredentials().toString(),user.getPassword())) {
//            throw new BadCredentialsException("Password wrong");
//        }
        //ensure credentials is not pased arround, pass null
        return new UsernamePasswordAuthenticationToken(userFromDb.get().getMerchantId(), "");
    }
}
