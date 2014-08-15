package com.netbuilder.thejuke;

import javax.ejb.Remote;

import com.netbuilder.thejuke.entities.User;

@Remote
public interface SongCatalogue {
	
	public void initialize(User user);

}
