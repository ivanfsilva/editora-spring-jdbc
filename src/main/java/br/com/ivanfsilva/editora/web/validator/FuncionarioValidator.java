package br.com.ivanfsilva.editora.web.validator;

import br.com.ivanfsilva.editora.entity.Funcionario;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class FuncionarioValidator implements Validator  {

    @Override
    public boolean supports(Class<?> clazz) {
        return Funcionario.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "error.nome",
                "O campo nome é obrigatório");

        Funcionario f = (Funcionario) target;

        if (f.getSalario() != null) {
            if (f.getSalario() < 0 ) {
                errors.rejectValue("nome", "error.salario", "O salário não deve ser negativo");
            }
        } else {
            errors.rejectValue("nome", "error.salario", "O campo salário é obrigatório");
        }

        if (f.getDataEntrada() != null) {
            LocalDate atual = LocalDate.now();
            if (f.getDataEntrada().isAfter(atual) ) {
                errors.rejectValue("dataEntrada", "error.dataEntrara",
                        "A data de entrada deve ser anterior ou igual a data atual");
            }
        } else {
            errors.rejectValue("dataEntrada", "error.dataEntrada",
                    "O campo data de entrada é obrigatório");
        }

        if (f.getDataSaida() != null) {
            if (f.getDataSaida().isBefore(f.getDataEntrada())) {
                errors.rejectValue("dataSaida", "error.dataSaida",
                        "A data de saída deve ser posterior ou igual a data de entrada");
            }
        }

        if (f.getCargo() == null) {
            errors.rejectValue("cargo", "erroer.cargo", "O campo cargo é obrigatório");
        }
    }
}
