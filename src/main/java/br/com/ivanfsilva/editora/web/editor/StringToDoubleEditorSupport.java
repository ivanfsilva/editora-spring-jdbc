package br.com.ivanfsilva.editora.web.editor;

import java.beans.PropertyEditorSupport;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

public class StringToDoubleEditorSupport extends PropertyEditorSupport {

    private static Logger logger = Logger.getLogger(StringToDoubleEditorSupport.class);

    @Override
    public String getAsText() {

        Double value = (Double) this.getValue();

        DecimalFormat df = new DecimalFormat("#,##0.00");

        if (value == null) {
            value = 0.00;
        }

        return df.format(value);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            text = text.replace(".", "").replace(",", ".");
            super.setValue(Double.parseDouble(text));
        } catch(NumberFormatException ex) {
            logger.fatal("O campo salário espera um double e rebebeu uma string!", ex);
        }
    }
}
