package br.com.fib.Biblioteca;

import br.com.fib.Biblioteca.beans.Usuario;
import br.com.fib.Biblioteca.repository.UsuarioRepository;
import static org.assertj.core.api.Assertions.assertThat;

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


import org.junit.Before;


/**
 * @author rafael
 * classe para teste unitario de usuario
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioTest {
    
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    private MockMvc mvc;
    
    @Before
    public void init() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
    @Test
    public void buscaUsuario()
    {
        Page<Usuario> usuarios = usuarioRepository.findAll(new PageRequest(0,10));
        assertThat(usuarios.getTotalElements()).isNotNull();
    }
    
    @Test
    public void buscaUsuarioPeloNome()
    {
        Usuario usuario = usuarioRepository.findByUsername("rafael");
        assertThat(usuario).isNotNull();
        usuario = usuarioRepository.findByUsername("djasdlask");
        assertThat(usuario).isNull();
    }
    
    
    @Test
    public void testeCrud()
    {
        Usuario usuario = new Usuario();
        usuario.setId(5L);
        usuario.setPassword("$2a$10$gieb4eSVlchb2YFlXdWjYum.u9zeSC/C5vEIiL62kfCVqhWGvlr5K");
        usuario.setUsername("manolo");
        
        usuarioRepository.save(usuario);
        usuario = usuarioRepository.findByUsername("manolo");
        assertThat(usuario.getUsername()).isEqualTo("manolo");
        
        usuario.setUsername("manolo da silva");
        usuarioRepository.save(usuario);
        
        usuario = usuarioRepository.findByUsername("manolo da silva");
        assertThat(usuario.getId()).isEqualTo(5L);
        
        usuarioRepository.delete(usuario);
        usuario = usuarioRepository.findByUsername("manolo da silva");
        assertThat(usuario).isNull();
    }
    
    
}
