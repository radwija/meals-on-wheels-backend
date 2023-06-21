package com.lithan.mow.security;

import com.lithan.mow.constraint.ERole;
import com.lithan.mow.entity.Customer;
import com.lithan.mow.entity.Partner;
import com.lithan.mow.exception.UserNotActiveException;
import com.lithan.mow.repository.CustomerRepository;
import com.lithan.mow.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PartnerRepository partnerRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (partnerRepository.findByEmail(username).isPresent()) {
            Partner data = partnerRepository.findByEmail(username).get();
            if (!data.isActive()) throw new UserNotActiveException(ERole.ROLE_PARTNER.toString());
            return new User(data.getEmail(), data.getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority(ERole.ROLE_PARTNER.toString())));
        }

        Customer user = customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " Not Found"));

        if (!user.isActive()) throw new UserNotActiveException(String.valueOf(user.getRole()));
        return new User(user.getEmail(), user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(user.getRole().toString())));
    }
}
