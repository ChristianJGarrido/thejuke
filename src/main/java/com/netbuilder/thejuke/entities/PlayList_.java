package com.netbuilder.thejuke.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-08-18T15:26:52.008+0100")
@StaticMetamodel(PlayList.class)
public class PlayList_ {
	public static volatile SingularAttribute<PlayList, Long> id;
	public static volatile SingularAttribute<PlayList, Admin> adminId;
	public static volatile SingularAttribute<PlayList, String> name;
	public static volatile ListAttribute<PlayList, Song> songList;
}
