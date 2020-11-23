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
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "endereco.logradouro",
                "error.logradouro", "O campo Logradouro é obrigatório");

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "endereco.bairro",
                "error.bairro", "O campo Bairro é obrigatório");

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "endereco.cidade",
                "error.cidade", "O campo cidade é obrigatório");

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "endereco.estado",
                "error.estado", "O campo estado é obrigatório");

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "endereco.cep",
                "error.cep", "O campo cep é obrigatório");

        Endereco e = (Endereco) target;

        if (e.getNumero() == null) {
            // if (e.getNumero() < 0) {
            errors.rejectValue("endereco.numero", "error.numero",
                    "O campo número é obrigatório!");
            // }
        }

    }
}
