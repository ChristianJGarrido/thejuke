package com.netbuilder.thejuke.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-08-18T15:26:51.942+0100")
@StaticMetamodel(Admin.class)
public class Admin_ {
	public static volatile SingularAttribute<Admin, Long> id;
	public static volatile SingularAttribute<Admin, User> user;
	public static volatile ListAttribute<Admin, PlayList> plList;
}
