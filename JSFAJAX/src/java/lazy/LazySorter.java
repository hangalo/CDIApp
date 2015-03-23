/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lazy;

import entities.Funcionario;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 *
 * @author desenvolvimento
 */
public class LazySorter implements Comparator<Funcionario>{
    private String sortField;
    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
    
    public int compare(Funcionario f1, Funcionario f2){
    try{
        Object value1 = Funcionario.class.getField(this.sortField).get(f1);
        Object value2 = Funcionario.class.getField(this.sortField).get(f2);
        
        int value =((Comparable)value1).compareTo(value2);
        return  SortOrder.ASCENDING.equals(sortOrder)?value: -1 * value;
    }
    catch(Exception e){
        throw  new RuntimeException();
    }
    }

   
    
    
    
    
    
    
    
    
}
