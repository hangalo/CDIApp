/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md;

import entities.Departamento;
import entities.Funcionario;
import facade.DepartamentoFacade;
import facade.FuncionarioFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import lazy.LazyFuncionarioDataModel;
import static org.primefaces.behavior.ajax.AjaxBehavior.PropertyKeys.listener;
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
    private DepartamentoFacade departamentoFacade;

    @EJB
    private FuncionarioFacade funcionarioFacade;
    private LazyDataModel<Funcionario> funcionarios;
    private Funcionario funcionarioSeleccionado;
    private List<Funcionario> listaFuncionarios;
    private List<Departamento> departamentos;
    private Departamento departamento;

    /**
     * Creates a new instance of FuncionarioBean
     */
    public FuncionarioBean() {
        listaFuncionarios = new ArrayList<>();
        departamento = new Departamento();
    }

    public void mudancaValor(ValueChangeEvent e){
    
    departamento = (Departamento) e.getNewValue();
        System.out.println("Departamento Seleccionado:"+ departamento);
        //System.out.println("Departamento Seleccionado:"+ departamento.getNomeDepartamento());
        //System.out.println("Departamento Seleccionado:"+ departamento.getNomeDepartamento());
        //System.out.println("Departamento Seleccionado:"+ departamento.getNomeDepartamento());
         System.out.println("Departamento Seleccionado:"+ departamento);
          System.out.println("Departamento Seleccionado:"+ departamento);
           System.out.println("Departamento Seleccionado:"+ departamento);
        
    }
    
    
    public void buscaValor(ActionEvent actionEvent){
    departamento = (Departamento) actionEvent.getSource();
    System.out.println("Departamento Seleccionado:"+ departamento);
          System.out.println("Departamento Seleccionado:"+ departamento);
           System.out.println("Departamento Seleccionado:"+ departamento);
    }
    
    public List<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public List<Departamento> getDepartamentos() {
        departamentos = departamentoFacade.findAll();
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
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
     
     
  public String guardar(){
  
  funcionarioFacade.create(funcionarioSeleccionado);
  funcionarioSeleccionado = new Funcionario();
  return null;
  }
}
