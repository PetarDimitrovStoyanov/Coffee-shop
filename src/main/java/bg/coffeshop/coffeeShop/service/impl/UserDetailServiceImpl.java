package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import bg.coffeshop.coffeeShop.repository.UserEntityRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserEntityRepository userEntityRepository;

    public UserDetailServiceImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = this.userEntityRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("The user was not found!"));

        if(user.getRole().getName().name().equalsIgnoreCase("USER")){
            return mapUser(user);
        } else {
            return mapAdmin(user);
        }

    }

    public UserDetails mapUser(UserEntity userEntity) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                grantedAuthorities
        );
    }

    public UserDetails mapAdmin(UserEntity userEntity) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"));

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                grantedAuthorities
        );
    }
}
