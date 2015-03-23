/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lazy;

import entities.Funcionario;
import facade.FuncionarioFacade;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author desenvolvimento
 */
public class LazyFuncionarioDataModel extends LazyDataModel<Funcionario> {

    FuncionarioFacade funcionarioFacade = lookupFuncionarioFacadeBean();
    private List<Funcionario> funcionarios;

    public LazyFuncionarioDataModel(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    /**
     *
     * @param rowKey
     * @return
     */
    @Override
    public Funcionario getRowData(String rowKey) {
        for (Funcionario f : funcionarioFacade.findAll()) {
            if (String.valueOf(f.getIdDepartamento()).equals(rowKey)) {
                return f;
            }
        }
        return null;
    }

    /**
     *
     * @param f
     * @return
     */
    @Override
    public Object getRowKey(Funcionario f) {
        return f.getIdFuncionario();
    }

    public List<Funcionario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Funcionario> listaFuncionarios = new ArrayList<>();

        //filter
        for (Funcionario f : funcionarioFacade.findAll()) {
            boolean match = true;
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(f.getClass().getField(filterProperty).get(f));

                        if (filterValue == null || fieldValue.startsWith(fieldValue.toString())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }

                    } catch (Exception e) {
                        match = false;
                    }
                }
            }
            if(match){
            listaFuncionarios.add(f);
            }

        }
        
// Sort
        if(sortField!= null){
        Collections.sort(funcionarios, new LazySorter(sortField, sortOrder));
        }
        
        int dataSize = listaFuncionarios.size();
        this.setRowCount(dataSize);
        
        if(dataSize > pageSize){
        try{
        return listaFuncionarios.subList(first, first+pageSize);
        }
        catch(IndexOutOfBoundsException e){
            return listaFuncionarios.subList(first,first+(dataSize%pageSize));
        }
        }else{
        return  listaFuncionarios;
        }

    }

    private FuncionarioFacade lookupFuncionarioFacadeBean() {
        try {
            Context c = new InitialContext();
            return (FuncionarioFacade) c.lookup("java:global/JSFAJAX/FuncionarioFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
