package com.netbuilder.thejuke.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-08-18T15:26:52.011+0100")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Float> balance;
	public static volatile SingularAttribute<User, Boolean> admin;
}
