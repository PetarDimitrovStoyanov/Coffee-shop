package bg.coffeshop.coffeeShop.service.impl;

import bg.coffeshop.coffeeShop.model.entity.UserEntity;
import bg.coffeshop.coffeeShop.repository.UserEntityRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserEntityRepository userEntityRepository;

    public UserDetailServiceImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var user = this.userEntityRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("The user was not found!"));

        UserDetails mappedUser = map(user);

        return mappedUser;
    }
    /*UserDetails from Spring Security*/
    private UserDetails map(UserEntity userEntity) {
        Set<GrantedAuthority> grantedAuthorities =
                userEntity
                        .getRoles()
                        .stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                        .collect(Collectors.toSet());

        /*returning User from spring Security*/
        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                grantedAuthorities
        );
    }
}
