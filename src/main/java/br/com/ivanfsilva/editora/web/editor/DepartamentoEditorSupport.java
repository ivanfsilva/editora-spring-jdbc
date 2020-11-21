package br.com.ivanfsilva.editora.web.editor;

import br.com.ivanfsilva.editora.entity.Departamento;
import br.com.ivanfsilva.editora.service.DepartamentoService;

import java.beans.PropertyEditorSupport;

public class DepartamentoEditorSupport extends PropertyEditorSupport {

    private DepartamentoService service;

    public DepartamentoEditorSupport(DepartamentoService service) {
        this.service = service;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!text.isEmpty()) {
            Integer id = Integer.parseInt(text);

            Departamento departamento = service.findById(id);

            super.setValue(departamento);
        }
    }
}
