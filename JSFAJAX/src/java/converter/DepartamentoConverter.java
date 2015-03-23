package converter;


import entities.Departamento;
import java.util.HashMap;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author desenvolvimento
 */
@FacesConverter("departamentoConverter")
public class DepartamentoConverter  implements Converter{
    
    private static Map<String, Departamento> mapa = new HashMap<String, Departamento>();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       return mapa.get(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if(value instanceof Departamento){
       Departamento d = (Departamento) value;
       mapa.put(String.valueOf(d.getIdDepartamento()), d);
       return String.valueOf(d.getIdDepartamento());
       }else{
       return "";
       }
    }
    
}
