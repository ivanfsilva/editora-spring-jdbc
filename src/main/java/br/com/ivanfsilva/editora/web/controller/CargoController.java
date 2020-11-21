package br.com.ivanfsilva.editora.web.controller;

import br.com.ivanfsilva.editora.entity.Cargo;
import br.com.ivanfsilva.editora.entity.Departamento;
import br.com.ivanfsilva.editora.service.CargoService;
import br.com.ivanfsilva.editora.service.DepartamentoService;
import br.com.ivanfsilva.editora.web.editor.DepartamentoEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;
    @Autowired
    private DepartamentoService departamentoService;

    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(
                Departamento.class,
                new DepartamentoEditorSupport(departamentoService));
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView findAll(@ModelAttribute("cargo") Cargo cargo, ModelMap model) {
        model.addAttribute("cargos", cargoService.findAll());
        model.addAttribute("departamentos", departamentoService.findAll());

        return new ModelAndView("addCargo", model);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("cargo") Cargo cargo) {
        cargoService.saveOrUpdate(cargo);

        return "redirect:/cargo/add";
    }

    @RequestMapping(value = "/update/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Integer id) {
        Cargo cargo = cargoService.findById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addCargo");
        modelAndView.addObject("cargo", cargo);
        modelAndView.addObject("cargos", cargoService.findAll());
        modelAndView.addObject("departamentos", departamentoService.findAll());

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        cargoService.delete(id);
        return  "redirect:/cargo/add";
    }
}
