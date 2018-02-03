package br.com.fib.Biblioteca.services;


import br.com.fib.Biblioteca.beans.Role;
import br.com.fib.Biblioteca.beans.Usuario;
import br.com.fib.Biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Usuario user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = new Role();
        role.setRole("ROLE_BASIC");
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public Usuario findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Usuario> findAll() {
        return userRepository.findAll();
    }
}