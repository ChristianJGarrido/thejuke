package com.netbuilder.thejuke.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.netbuilder.thejuke.entities.Song;
import com.netbuilder.thejuke.entities.User;
//@SessionScoped
public class SongQueueController  implements Serializable
{
	//@Inject
	private LinkedList<Song> songQueue;
	//@Inject
	private User linkedUser;
	public SongQueueController()
	{
		
	}
	public void addSong(Song song)
	{
		if(linkedUser.subtractFrombalance(song.getCost()))
		{
			songQueue.add(song);
		}
	}
	public void playSong()
	{
		songQueue.getFirst().getAudioPath();
	}
	public void pauseSong()
	{
		
	}
	public void Next()
	{
		songQueue.remove();
	}
	public Song getNext()
	{
		this.Next();
		if(!songQueue.isEmpty())
		{
			return songQueue.getFirst();
		}
		else
		{
		return null;
		}
	}
	public LinkedList<Song> getSongQueue() {
		return songQueue;
	}
	public void setSongQueue(LinkedList<Song> songQueue) {
		this.songQueue = songQueue;
	}
	public User getLinkedUser() {
		return linkedUser;
	}
	public void setLinkedUser(User linkedUser) {
		this.linkedUser = linkedUser;
	}
	
}
