package com.netbuilder.thejuke.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.netbuilder.thejuke.entities.Song;
import com.netbuilder.thejuke.entities.User;
import com.netbuilder.thejuke.services.UserService;
@Named
@SessionScoped
public class QueueController implements Serializable
{
	private Queue<Song> songQueue;
	
	@Inject
	UserController userController;
	@Inject
	UserService userService;
	
	public QueueController()
	{
		songQueue=new LinkedList<Song>();
	}

	public Queue<Song> getSongQueue() {
		return songQueue;
	}

	public void setSongQueue(Queue<Song> songQueue) {
		this.songQueue = songQueue;
	}
	public String doAddSongToQueue(Song song)
	{
		User loggedInUser= userController.getLoggedinUser();
		float balance=loggedInUser.getBalance();
		float cost = song.getCost();
		if((balance-cost)>=0)
		{
			System.out.println("BALANCE WILL BE"+(balance-cost));
			loggedInUser.setBalance(balance-cost);
			userService.updateUser(loggedInUser);
			songQueue.add(song);
			
		}
		return "#";
	}
	public String doRemoveSongFromQueue(Song song)
	{
		if(song==null)
		{
			System.out.println("Song is null");
			return "#";
		}
		songQueue.remove(song);
		return "#";
	}
	public String doRemoveSongFromQueue()
	{
		if(songQueue==null)
		{
			System.out.println("Song Queue is null");
			return "#";
		}
		if(songQueue.size()<1)
		{
			System.out.println("Song Queue is empty");
			return "#";
		}
		songQueue.remove();
		System.out.println(songQueue);
		return "#";
	}

}
