package br.com.absoft.conversores;

import java.text.DecimalFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converterMinutosHora")
public class ConverterMinutosHora implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        return converteHora_Minuto(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return converteHora_Minuto(Integer.parseInt(value.toString()));
    }

    public static String converteHora_Minuto(int min) {
        float valorFinalEmHoras = (float) min / 60;

        float tempoM = valorFinalEmHoras * 60;
        int hora = 0;
        int minutos = 0;
        String hora_minutos = "00:00";
        while (tempoM >= 60) {
            hora++;
            tempoM = tempoM - 60;
        }
        minutos = (int) tempoM;

        DecimalFormat df = new DecimalFormat("00");

        hora_minutos = df.format(hora) + ":" + df.format(minutos);
        return hora_minutos;
    }

}
