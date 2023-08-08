package com.zakaria.ecom.services.impl;

import com.zakaria.ecom.dtos.request.UserRequest;
import com.zakaria.ecom.models.Role;
import com.zakaria.ecom.models.User;
import com.zakaria.ecom.repositories.RoleRepository;
import com.zakaria.ecom.repositories.UserRepository;
import com.zakaria.ecom.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<User> getAll(int page,int size) {
        if(page > 0) page-=1;
        List<User> users = new ArrayList<>();
        Pageable pageable =  PageRequest.of(page,size);
        Page <User> userPage = userRepository.findAll(pageable);
        List<User> userList = userPage.getContent();
        // userRepository.findAll()
           //     .forEach(users::add);
        // method refrence lamda expressions

        return userList;
    }

    @Override
    public User get(Long id) {
        return Objects.requireNonNull(userRepository.findById(id).orElse(null));
    }

    @Override
    public User add(UserRequest userRequest) {
        User user =new User();
        BeanUtils.copyProperties(userRequest,user);
        ArrayList<Role> roles = new ArrayList<>();
        for (int i = 0; i < userRequest.getRoles().size(); i++) {
            Long id = Long.valueOf((userRequest.getRoles()).get(i));
            Optional<Role> optionalRole = roleRepository.findById(id);
            Role role = optionalRole.get();
            roles.add(role);
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
     return userRepository.save(user);

    }

    @Override
    public User update(Long id,User user) {
        User dao = userRepository.findById(id).orElse(null);
        if(dao!=null){
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        User dao = userRepository.findById(id).orElse(null);
        if(dao!=null){
            userRepository.delete(dao);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("User Not Found ");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities );
    }
}
