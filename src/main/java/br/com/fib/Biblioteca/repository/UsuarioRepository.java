/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fib.Biblioteca.repository;

import br.com.fib.Biblioteca.beans.Usuario;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rafael
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    public Usuario findByUsername(String userName);
    
}
