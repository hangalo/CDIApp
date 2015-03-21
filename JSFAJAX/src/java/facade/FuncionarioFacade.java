/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Funcionario;
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
public class FuncionarioFacade extends AbstractFacade<Funcionario> {

    @PersistenceContext(unitName = "JSFAJAXPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FuncionarioFacade() {
        super(Funcionario.class);
    }

    public List<Funcionario> buscaFuncionario(int startingAt, int maxPerPage) {

        Query query = em.createQuery("SELECT f FROM Funcionario f");
        query.setFirstResult(startingAt);
        query.setMaxResults(maxPerPage);
        return query.getResultList();
    }

    public int countPlayersTotal() {
        Query query = em.createQuery("SELECT COUNT(p) FROM Funcionario p");
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

}
