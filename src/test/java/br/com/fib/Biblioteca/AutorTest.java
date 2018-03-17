package br.com.fib.Biblioteca;

import br.com.fib.Biblioteca.beans.Autor;
import br.com.fib.Biblioteca.beans.Usuario;
import br.com.fib.Biblioteca.repository.AutorRepository;
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
public class AutorTest {
    
    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private AutorRepository autorRepository;
    
    private MockMvc mvc;
    
    @Before
    public void init() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
    @Test
    public void buscaAutor()
    {
        Page<Autor> autores = autorRepository.findAll(new PageRequest(0,10));
        assertThat(autores.getTotalElements()).isNotNull();
    }
    
    @Test
    public void buscaAutorPeloNome()
    {
        Autor autor = autorRepository.findByNome("autor teste 1");
        assertThat(autor).isNotNull();
        autor = autorRepository.findByNome("douglas adams");
        assertThat(autor).isNull();
    }
    
    
    @Test
    public void testeCrud()
    {
        Autor autor = new Autor();
        autor.setId(2L);
        autor.setNome("Douglas Adams");
        
        autorRepository.save(autor);
        autor = autorRepository.findByNome("Douglas Adams");
        assertThat(autor.getNome()).isEqualTo("Douglas Adams");
        
        autor.setNome("Douglas Adams 2");
        autorRepository.save(autor);
        
        autor = autorRepository.findByNome("Douglas Adams 2");
        assertThat(autor.getId()).isEqualTo(2L);
        
        autorRepository.delete(autor);
        autor = autorRepository.findByNome("Douglas Adams");
        assertThat(autor).isNull();
    }
    
}
