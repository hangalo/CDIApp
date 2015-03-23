/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md;

import entities.Funcionario;
import facade.FuncionarioFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lazy.LazyFuncionarioDataModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author desenvolvimento
 */
@Named(value = "funcionarioBean")
@RequestScoped
public class FuncionarioBean implements Serializable {

    @EJB
    private FuncionarioFacade funcionarioFacade;
    private LazyDataModel<Funcionario> funcionarios;
    private Funcionario funcionarioSeleccionado;

    /**
     * Creates a new instance of FuncionarioBean
     */
    public FuncionarioBean() {
    }

  

    public LazyDataModel<Funcionario> getFuncionarios() {
        return funcionarios= new LazyFuncionarioDataModel(funcionarioFacade.findAll());
    }

    public void setFuncionarios(LazyDataModel<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Funcionario getFuncionarioSeleccionado() {
        return funcionarioSeleccionado;
    }

    public void setFuncionarioSeleccionado(Funcionario funcionarioSeleccionado) {
        this.funcionarioSeleccionado = funcionarioSeleccionado;
    }
    
     public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Funcionario Seleccionado", ((Funcionario) event.getObject()).getIdFuncionario().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
