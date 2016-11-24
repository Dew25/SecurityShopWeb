/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Product;
import entity.Seller;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jvm
 */
@Stateless
public class SellerFacade extends AbstractFacade<Seller> {

    @PersistenceContext(unitName = "KTVRShopWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SellerFacade() {
        super(Seller.class);
    }
    @RolesAllowed("user")
    public void addProductToSeller(String sellerId,Product product){
        Seller seller = em.find(Seller.class,Integer.parseInt(sellerId));
        seller.getProducts().add(product);
        em.persist(seller);
    }
    
}
