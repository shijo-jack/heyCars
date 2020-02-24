package com.heycar.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CarListing.class)
public abstract class CarListing_ {

	public static volatile SingularAttribute<CarListing, String> color;
	public static volatile SingularAttribute<CarListing, Integer> year;
	public static volatile SingularAttribute<CarListing, BigDecimal> price;
	public static volatile SingularAttribute<CarListing, String> model;
	public static volatile SingularAttribute<CarListing, Long> id;
	public static volatile SingularAttribute<CarListing, Integer> kW;
	public static volatile SingularAttribute<CarListing, String> make;

}

