/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package md;

import entities.Departamento;
import facade.DepartamentoFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author praveen
 */
@ManagedBean
@RequestScoped
public class DepartamentoBean {

    @EJB
    private DepartamentoFacade departamentoFacade;
    private Departamento departamento;
    private List<Departamento> departamentos;
    private List<Departamento> deparamentosSeleccionados;

    /**
     * Creates a new instance of Departamento
     */
    public DepartamentoBean() {
        departamento = new Departamento();
        departamentos = new ArrayList<>();
         deparamentosSeleccionados = new ArrayList<>();
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getDepartamentos() {
        departamentos = departamentoFacade.findAll();
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public String guardar() {

        departamentoFacade.create(departamento);
        departamento = new Departamento();
        return null;

    }

    public List<Departamento> getDeparamentosSeleccionados() {
        return deparamentosSeleccionados;
    }

    public void setDeparamentosSeleccionados(List<Departamento> deparamentosSeleccionados) {
        this.deparamentosSeleccionados = deparamentosSeleccionados;
    }

    public String eliminar() {

        departamentoFacade.remove(departamento);
        departamento = new Departamento();
        return null;
    }

    public List<Departamento> listarPorNome(String nomeDepartamento) {
        
        System.out.println("Passou Muito bem:"+nomeDepartamento);
        deparamentosSeleccionados= departamentoFacade.buscaDepartamentoByNome(nomeDepartamento);
        return  deparamentosSeleccionados;
        
    }

}
