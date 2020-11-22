package br.com.ivanfsilva.editora.web.editor;

import br.com.ivanfsilva.editora.entity.Cargo;
import br.com.ivanfsilva.editora.service.CargoService;

import java.beans.PropertyEditorSupport;

public class CargoEditorSupport extends PropertyEditorSupport {

    private CargoService service;

    public CargoEditorSupport(CargoService service) {
        this.service = service;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!text.isEmpty()) {
            Integer id = Integer.parseInt(text);
            Cargo cargo = service.findById(id);
            super.setValue(cargo);
        }
    }
}
