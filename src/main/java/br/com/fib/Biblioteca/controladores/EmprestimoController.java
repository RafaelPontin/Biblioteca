package br.com.fib.Biblioteca.controladores;

import br.com.fib.Biblioteca.beans.Autor;
import br.com.fib.Biblioteca.beans.Emprestimo;
import br.com.fib.Biblioteca.beans.Livro;
import br.com.fib.Biblioteca.beans.Usuario;
import br.com.fib.Biblioteca.repository.EmprestimoRepository;
import br.com.fib.Biblioteca.repository.LivroRepository;
import br.com.fib.Biblioteca.repository.UsuarioRepository;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    @GetMapping("/list")
    public ModelAndView emprestimo()
    {
        List<Emprestimo> list = emprestimoRepository.findAll();
        return new ModelAndView("emprestimo/list", "emprestimos", list);
    }

    @GetMapping("/novo")
    public ModelAndView createForm(@ModelAttribute Emprestimo emprestimo)
    {
        ModelAndView modelAndView = new ModelAndView("emprestimo/form");
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        modelAndView.addObject("usuarios", usuarios);
        Iterable<Livro> livros = livroRepository.findAll();
        modelAndView.addObject("livros", livros);
        return modelAndView;
    }
    
    @PostMapping(params = "form")
    public ModelAndView create(@ModelAttribute("emprestimo") @Valid Emprestimo emprestimo, BindingResult bindingResult)
    {
//        if (bindingResult.hasErrors())
//        {
//            return new ModelAndView("emprestimo/form");
//        }
        emprestimo.setDataEmprestimo(new Date());
        Date dataDevolcucao = new Date();
        dataDevolcucao.setDate(emprestimo.getDataEmprestimo().getDate() + 7);
        emprestimo.setDataDevolucao(dataDevolcucao);
        emprestimoRepository.save(emprestimo);
        return new ModelAndView("redirect:/emprestimo/list");
    }
    
}
