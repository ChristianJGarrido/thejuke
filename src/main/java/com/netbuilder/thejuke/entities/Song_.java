package com.netbuilder.thejuke.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-08-18T15:26:52.009+0100")
@StaticMetamodel(Song.class)
public class Song_ {
	public static volatile SingularAttribute<Song, Long> id;
	public static volatile SingularAttribute<Song, String> name;
	public static volatile SingularAttribute<Song, Float> length;
	public static volatile SingularAttribute<Song, String> audioPath;
	public static volatile SingularAttribute<Song, Float> cost;
	public static volatile SingularAttribute<Song, Genre> genre;
	public static volatile ListAttribute<Song, Album> albumList;
}
