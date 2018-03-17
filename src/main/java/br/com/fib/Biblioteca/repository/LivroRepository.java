package br.com.fib.Biblioteca.repository;

import br.com.fib.Biblioteca.beans.Livro;
import br.com.fib.Biblioteca.beans.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long>{
    public Livro findByTitulo(String titulo);
}
