package br.com.fib.Biblioteca.repository;

import br.com.fib.Biblioteca.beans.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository  extends JpaRepository<Autor, Long>
{
    
}
