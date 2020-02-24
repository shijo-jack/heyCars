package com.heycar.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProviderListing.class)
public abstract class ProviderListing_ {

	public static volatile SingularAttribute<ProviderListing, Long> providerId;
	public static volatile SingularAttribute<ProviderListing, Long> id;
	public static volatile SingularAttribute<ProviderListing, String> listingId;
	public static volatile SingularAttribute<ProviderListing, CarListing> carListing;

}

