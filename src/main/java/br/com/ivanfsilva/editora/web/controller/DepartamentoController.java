package br.com.ivanfsilva.editora.web.controller;

import br.com.ivanfsilva.editora.entity.Departamento;
import br.com.ivanfsilva.editora.service.DepartamentoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("departamento")
public class DepartamentoController {

    private static Logger logger = Logger.getLogger(DepartamentoController.class);

    @Autowired
    private DepartamentoService service;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView findAll(@ModelAttribute("departamento")Departamento departamento, ModelMap model) {
        logger.info("Acesso realizado ao departamento controller!");

        model.addAttribute("departamentos", service.findAll());
        return new ModelAndView("addDepartamento", model);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("departamento")Departamento departamento) {

        service.saveOrUpdate(departamento);

        return "redirect:/departamento/add";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView preUpdate(@PathVariable("id") Integer id, ModelMap model) {

        Departamento d = service.findById(id);
        model.addAttribute("idDepartamento", id);
        model.addAttribute("departamento", d.getDepartamento());

        return new ModelAndView("redirect:/departamento/add", model);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id) {

        service.delete(id);

        return "redirect:/departamento/add";
    }
}
