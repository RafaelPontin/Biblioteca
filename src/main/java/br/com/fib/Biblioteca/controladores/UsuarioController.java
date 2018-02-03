package br.com.fib.Biblioteca.controladores;

import br.com.fib.Biblioteca.beans.Usuario;
import br.com.fib.Biblioteca.repository.UsuarioRepository;
import br.com.fib.Biblioteca.services.SecurityService;
import br.com.fib.Biblioteca.validator.LoginValidator;
import br.com.fib.Biblioteca.validator.UsuarioValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.fib.Biblioteca.services.UsuarioService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private SecurityService securityService;
        
    @Autowired
    private UsuarioService userService;

    @Autowired
    private UsuarioValidator userValidator;

    @Autowired
    private LoginValidator loginValidator;

    @GetMapping("/list")
    public ModelAndView usuarios() {
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        return new ModelAndView("usuarios/list", "usuarios", usuarios);
    }

    @GetMapping("/novo")
    public String createForm(@ModelAttribute Usuario usuario) {
        return "usuarios/form";
    }

    @PostMapping(params = "form")
    public ModelAndView create(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("usuarios/form");
        }
        usuarioRepository.save(usuario);
        return new ModelAndView("redirect:/usuarios/list");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id) {
        Usuario usuario = usuarioRepository.findOne(id);
        return new ModelAndView("usuarios/form", "usuario", usuario);
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        this.usuarioRepository.delete(this.usuarioRepository.findOne(id));
        return new ModelAndView("redirect:/usuarios/list");
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration() {
        return new ModelAndView("usuario/registration", "userForm", new Usuario());
    }

    @PostMapping(value = "/registration")
    public ModelAndView registrationform(@ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ModelAndView("usuario/registration");
        }

        String password = userForm.getPassword();

        userService.save(userForm); 

        try {
            securityService.login(userForm.getUsername(), userForm.getPassword());
            return new ModelAndView("redirect:/usuario/list");

        } catch (Exception e) {

            return new ModelAndView("redirect:/usuario/login");
        }
    }
    
    @GetMapping("/login")
    public ModelAndView login() {
            return new ModelAndView("usuarios/login", "userForm", new Usuario());
    }
    
    @PostMapping("/autentication")
    public ModelAndView autentication(@ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult, Model model) {

            loginValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                    return new ModelAndView("usuarios/login");
            }
            securityService.login(userForm.getUsername(), userForm.getPassword());
            return new ModelAndView("redirect:/");
    }
    
    
    @GetMapping("/logout")
	public String logout(HttpServletRequest request) {
            HttpSession session = request.getSession(false);
            SecurityContextHolder.clearContext();
            if(session != null)
            {
                session.invalidate();
            }
	    return "redirect:/usuarios/login";
	}
    
    
}
