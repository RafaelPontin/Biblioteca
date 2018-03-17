package br.com.fib.Biblioteca;

import br.com.fib.Biblioteca.beans.Emprestimo;
import br.com.fib.Biblioteca.beans.Livro;
import br.com.fib.Biblioteca.beans.Usuario;
import br.com.fib.Biblioteca.repository.EmprestimoRepository;
import br.com.fib.Biblioteca.repository.LivroRepository;
import br.com.fib.Biblioteca.repository.UsuarioRepository;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmprestimoTest {
    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    private MockMvc mvc;
    
    @Before
    public void init() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
    @Test
    public void buscaLivro()
    {
        Page<Emprestimo> emprestimo = emprestimoRepository.findAll(new PageRequest(0,10));
        assertThat(emprestimo.getTotalElements()).isNotNull();
    }
    
    @Test
    public void buscaAutorPeloNome()
    {
        Date date = new Date(2018-1900, 2 -1, 3);
        Emprestimo emprestimo = emprestimoRepository.findByDataEmprestimo(date);
        assertThat(emprestimo).isNotNull();
        emprestimo = emprestimoRepository.findByDataEmprestimo(new Date());
        assertThat(emprestimo).isNull();
    }
    
    
    @Test
    public void testeCrud()
    {
        Livro livro = livroRepository.findByTitulo("livro teste 1");
        Usuario usuario = usuarioRepository.findByUsername("rafael");
        
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(new Date());
        Date date = new Date();
        date.setDate(date.getDate() + 7);
        emprestimo.setDataDevolucao(date);
        emprestimo.setId(2L);
        
        emprestimoRepository.save(emprestimo);
        emprestimo = emprestimoRepository.findByDataEmprestimo(new Date());
        assertThat(emprestimo.getId()).isEqualTo(2L);
        date.setDate(date.getDate() + 10);
        emprestimo.setDataEmprestimo(date);
        
        emprestimoRepository.save(emprestimo);
        
        emprestimo = emprestimoRepository.findOne(2L);
        assertThat(emprestimo.getId()).isEqualTo(2L);
        
        emprestimoRepository.delete(emprestimo);
        emprestimo = emprestimoRepository.findOne(2L);
        assertThat(emprestimo).isNull();
    }
    
}