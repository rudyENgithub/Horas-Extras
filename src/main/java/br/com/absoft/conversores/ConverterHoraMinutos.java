package br.com.absoft.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converterHoraMinutos")
public class ConverterHoraMinutos implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        return converteMinuto_Hora(value);

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return converteMinuto_Hora((String) value);

    }

    public static String converteMinuto_Hora(String data) {
        int horas = Integer.parseInt(data.substring(0, 2));
        int minutos = Integer.parseInt(data.substring(3, 5));

        int minutosfinais = (horas * 60 + minutos);
        return minutosfinais + "";

    }

}
