package br.com.ivanfsilva.editora.web.controller;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class GenericExceptionController {

    private static Logger logger = Logger.getLogger(GenericExceptionController.class);

    private static final String DEFAULT_PAGE_ERROR = "error";

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView notFoundException(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURI() + " lançou a ex: " + ex );

        ModelAndView model = new ModelAndView(DEFAULT_PAGE_ERROR);
        model.addObject("mensagem", "Ops! Esta página não existe! ");
        model.addObject("exception", ex);
        model.addObject("url", req.getRequestURL());

        return model;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView integrityKeyException(HttpServletRequest req, DataIntegrityViolationException ex) {
        logger.error("Request: " + req.getRequestURI() + " lançou a ex: " + ex );

        ModelAndView model = new ModelAndView(DEFAULT_PAGE_ERROR);
        model.addObject("mensagem", "Impossível inserir, registro já inserido no Banco de Dados! ");
        model.addObject("exception", ex);
        model.addObject("url", req.getRequestURL());

        return model;
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public ModelAndView sqlException(HttpServletRequest req, DataAccessException ex) {
        logger.error("Request: " + req.getRequestURI() + " lançou a ex: " + ex );

        ModelAndView model = new ModelAndView(DEFAULT_PAGE_ERROR);
        model.addObject("mensagem", "Ocorreu algum problema ao acessar o banco de dados! ");
        model.addObject("exception", ex);
        model.addObject("url", req.getRequestURL());

        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultException(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURI() + " lançou a ex: " + ex );

        ModelAndView model = new ModelAndView(DEFAULT_PAGE_ERROR);
        model.addObject("mensagem", "Uma exceção ocorreu! ");
        model.addObject("exception", ex);
        model.addObject("url", req.getRequestURL());

        return model;
    }
}
