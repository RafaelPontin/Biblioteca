package br.com.fib.Biblioteca.services;



import br.com.fib.Biblioteca.beans.Usuario;
import java.util.List;

public interface UsuarioService {
	
	void save(Usuario usuario);

	Usuario findByUsername(String username);

	List<Usuario> findAll();
	
}