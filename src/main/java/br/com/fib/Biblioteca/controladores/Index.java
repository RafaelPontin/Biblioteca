package br.com.fib.Biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Index {
    
//    @Autowired
//    private LivroRepository livroRepository;
    
    @RequestMapping("/")
    public ModelAndView home()
    {
        return new ModelAndView("index");
    }
    
//    @GetMapping("/livros")
//    public ModelAndView livros()
//    {
//        Iterable<Livro> livros = livroRepository.findAll();
//        return new ModelAndView("livros","livros",livros)
//        ;
//    }
        
    
}
