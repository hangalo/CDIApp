/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Departamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author praveen
 */
@Stateless
public class DepartamentoFacade extends AbstractFacade<Departamento> {

    @PersistenceContext(unitName = "JSFAJAXPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoFacade() {
        super(Departamento.class);
    }

    public List<Departamento> buscaDepartamentoByNome(String nomeDepartamento) {
        Query query = em.createQuery("SELECT d FROM Departamento d WHERE upper(d.nomeDepartamento) like upper(:nomeDepartamento)");
        query.setParameter("nomeDepartamento", "%" + nomeDepartamento + "%");
        
        return query.getResultList();

    }

}
