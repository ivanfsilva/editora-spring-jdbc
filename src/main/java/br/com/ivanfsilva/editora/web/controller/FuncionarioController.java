package br.com.ivanfsilva.editora.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.ivanfsilva.editora.entity.Cargo;
import br.com.ivanfsilva.editora.entity.Funcionario;
import br.com.ivanfsilva.editora.service.CargoService;
import br.com.ivanfsilva.editora.service.EnderecoService;
import br.com.ivanfsilva.editora.service.FuncionarioService;
import br.com.ivanfsilva.editora.web.editor.CargoEditorSupport;

@Controller
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private CargoService cargoService;
    @Autowired
    private EnderecoService enderecoService;

    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Cargo.class, new CargoEditorSupport(cargoService));
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView findAll(@ModelAttribute("funcionario") Funcionario funcionario) {

        ModelAndView modelAndView = new ModelAndView("addFuncionario");
        modelAndView.addObject("funcionarios", funcionarioService.findAll());
        modelAndView.addObject("cargos", cargoService.findAll());

        return modelAndView;
    }

    @RequestMapping(value = "/save")
    public String save(@ModelAttribute("funcionario") Funcionario funcionario) {

        funcionarioService.saveOrUpdate(funcionario);

        return "redirect:/funcionario/add";
    }

    @RequestMapping(value = "/update/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Integer id, ModelMap model) {

        return new ModelAndView();
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        funcionarioService.delete(id);
        return "redirect:/funcionario/add";
    }
}
