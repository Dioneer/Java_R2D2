package Pegas.services;

import Pegas.entity.Authority;
import Pegas.repository.WebShopRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class WebShopDetailService implements UserDetailsService {
//    private final WebShopRepository webShopRepository;
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return webShopRepository.findByUsername(username).map(i-> User.builder()
//                .username(i.getUsername())
//                .username(i.getPassword())
//                .authorities(i.getAuthorityList().stream().map(Authority::getAuthority)
//                        .map(SimpleGrantedAuthority::new).toList())
//                .build())
//                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
//    }
//}
