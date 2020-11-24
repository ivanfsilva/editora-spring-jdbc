package br.com.ivanfsilva.editora.web.validator;

import br.com.ivanfsilva.editora.entity.Endereco;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EnderecoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Endereco.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.logradouro",
                "error.logradouro");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.bairro",
                "error.bairro");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.cidade",
                "error.cidade");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.estado",
                "error.estado");

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "endereco.cep","error.cep");

        Endereco e = (Endereco) target;

        if (e.getNumero() == null) {
            // if (e.getNumero() < 0) {
            errors.rejectValue("endereco.numero", "error.numero");
            // }
        }

    }
}
