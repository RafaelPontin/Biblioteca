package br.com.fib.Biblioteca.services;

import br.com.fib.Biblioteca.beans.Role;
import br.com.fib.Biblioteca.beans.Usuario;
import br.com.fib.Biblioteca.repository.UsuarioRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException 
    {
        Usuario user = userRepository.findByUsername(userName);
        Set<GrantedAuthority> grantedAuthority = new HashSet<>();
        
        for(Role role: user.getRoles())
        {
            grantedAuthority.add(new SimpleGrantedAuthority(role.getRole()));
        }
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantedAuthority);
    }
    
    
    
    
}