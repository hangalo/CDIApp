/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lazy;

import entities.Funcionario;
import facade.FuncionarioFacade;
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
public class FuncionarioLazyList extends LazyDataModel<Funcionario> {

    FuncionarioFacade funcionarioFacade = lookupFuncionarioFacadeBean();
    private List<Funcionario> funcionarios;

    /**
     *
     * @param startingAt
     * @param maxPerPage
     * @param sortField
     * @param sortOrder
     * @param filters
     * @return
     */
    @Override
    public List<Funcionario> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        funcionarios = funcionarioFacade.buscaFuncionario(startingAt, maxPerPage);
        if (getRowCount() <= 0) {
            setRowCount(funcionarioFacade.countPlayersTotal());
        }
        setPageSize(maxPerPage);

        return funcionarios;
    }

    @Override
    public Object getRowKey(Funcionario funcionario) {
        return funcionario.getIdFuncionario();
    }

    /**
     *
     * @param funcionarioId
     * @return
     */
    @Override
    public Funcionario getRowData(String funcionarioId) {
        Integer id = Integer.valueOf(funcionarioId);
        for (Funcionario f : funcionarios) {
            if (id.equals(f.getIdFuncionario())) {
                return f;
            }
        }
        return null;
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
