package com.netbuilder.thejuke.entities;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-08-18T15:26:52.004+0100")
@StaticMetamodel(Album.class)
public class Album_ {
	public static volatile SingularAttribute<Album, Long> id;
	public static volatile SingularAttribute<Album, String> name;
	public static volatile SingularAttribute<Album, String> producer;
	public static volatile SingularAttribute<Album, Date> dateCreated;
	public static volatile SingularAttribute<Album, String> artPath;
	public static volatile ListAttribute<Album, Artist> artistList;
	public static volatile ListAttribute<Album, Song> songList;
}
