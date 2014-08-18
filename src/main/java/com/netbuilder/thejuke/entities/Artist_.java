package com.netbuilder.thejuke.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-08-18T15:26:52.005+0100")
@StaticMetamodel(Artist.class)
public class Artist_ {
	public static volatile SingularAttribute<Artist, Long> id;
	public static volatile SingularAttribute<Artist, String> name;
	public static volatile ListAttribute<Artist, Album> albumList;
	public static volatile SingularAttribute<Artist, String> bio;
}
