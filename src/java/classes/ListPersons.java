/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.Customer;
import entity.Seller;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jvm
 */
public class ListPersons {
    private List<Seller> sellers;
    private List<Customer> customers;

    public ListPersons() {
    }

    public ListPersons(List<Seller> sellers, List<Customer> customers) {
        this.sellers = sellers;
        this.customers = customers;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.sellers);
        hash = 17 * hash + Objects.hashCode(this.customers);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListPersons other = (ListPersons) obj;
        if (!Objects.equals(this.sellers, other.sellers)) {
            return false;
        }
        if (!Objects.equals(this.customers, other.customers)) {
            return false;
        }
        return true;
    }
    
}
