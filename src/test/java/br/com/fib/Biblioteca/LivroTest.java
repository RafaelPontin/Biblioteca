package br.com.fib.Biblioteca;

import br.com.fib.Biblioteca.beans.Autor;
import br.com.fib.Biblioteca.beans.Livro;
import br.com.fib.Biblioteca.repository.AutorRepository;
import br.com.fib.Biblioteca.repository.LivroRepository;
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
public class LivroTest {
    
    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private LivroRepository livroRepository;
    
    private MockMvc mvc;
    
    @Before
    public void init() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
    @Test
    public void buscaLivro()
    {
        Page<Livro> livro = livroRepository.findAll(new PageRequest(0,10));
        assertThat(livro.getTotalElements()).isNotNull();
    }
    
    @Test
    public void buscaAutorPeloNome()
    {
        Livro livro = livroRepository.findByTitulo("livro teste 1");
        assertThat(livro).isNotNull();
        livro = livroRepository.findByTitulo("addasda");
        assertThat(livro).isNull();
    }
    
    
    @Test
    public void testeCrud()
    {
        Livro livro = new Livro();
        livro.setId(2L);
        livro.setIsbn("ddasdasd");
        livro.setQuantidade(10);
        livro.setTitulo("Guia mochileiro das galaxias");
        
        livroRepository.save(livro);
        livro = livroRepository.findByTitulo("Guia mochileiro das galaxias");
        assertThat(livro.getTitulo()).isEqualTo("Guia mochileiro das galaxias");
        
        livro.setTitulo("Guia mochileiro das galaxias 2");
        livroRepository.save(livro);
        
        livro = livroRepository.findByTitulo("Guia mochileiro das galaxias 2");
        assertThat(livro.getId()).isEqualTo(2L);
        
        livroRepository.delete(livro);
        livro = livroRepository.findByTitulo("Guia mochileiro das galaxias 2");
        assertThat(livro).isNull();
    }
    
}
