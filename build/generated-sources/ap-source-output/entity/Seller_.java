package entity;

import entity.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-24T23:32:54")
@StaticMetamodel(Seller.class)
public class Seller_ extends Person_ {

    public static volatile SingularAttribute<Seller, String> nameShop;
    public static volatile SingularAttribute<Seller, Integer> profit;
    public static volatile ListAttribute<Seller, Product> products;

}