package br.com.fib.Biblioteca.repository;

import br.com.fib.Biblioteca.beans.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long>{
    
}
