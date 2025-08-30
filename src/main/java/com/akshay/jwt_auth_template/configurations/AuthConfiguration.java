package com.akshay.jwt_auth_template.configurations;

import com.akshay.jwt_auth_template.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AuthConfiguration {
    private final UserRepository userRepository;

    public AuthConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // UserDetailsService is a functional interface — it has just one method:
    //      UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    //
    //That means you can implement it with a lambda (short form)
    @Bean
    UserDetailsService userDetailsService() {
        // returns an implementation of the UserDetailsService functional interface
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}


// Authentication Flow

// [ User submits login request (email + password) ]
//         |
//         ▼
// [ AuthenticationManager receives request ]
//         |
//         ▼
// [ Delegates to DaoAuthenticationProvider ]
//         |
//         ▼
// [ DaoAuthenticationProvider calls UserDetailsService ]
//         |
//         ▼
// [ UserRepository searches DB by email ] --------------------------------
//           /                                                            |
//          /                                                             |
// [ User found → return UserDetails ]                              [ User not found ]
//         |                                                                |
//         ▼                                                                ▼
// [ PasswordEncoder checks - hash(pass) == hashed in db ]   [ Throw UsernameNotFoundException ]
//         |
//   /----------\
//  /            \
// [ Match ]       [ Mismatch ]
//     |               |
//     ▼               ▼
// [ Authentication   [ Throw BadCredentialsException ]
// successful ]
//      |
//      ▼
// [ Authentication Object stored in SecurityContext ]
