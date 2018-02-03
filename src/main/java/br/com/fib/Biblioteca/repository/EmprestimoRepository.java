package br.com.fib.Biblioteca.repository;

import br.com.fib.Biblioteca.beans.Autor;
import br.com.fib.Biblioteca.beans.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
    
}
