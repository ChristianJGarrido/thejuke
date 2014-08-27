package com.netbuilder.thejuke.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import javax.enterprise.context.SessionScoped;

import com.netbuilder.thejuke.entities.Song;
@SessionScoped
public class QueueController implements Serializable
{
	private Queue<Song> songQueue;
	
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
	public void addSongToQueue(Song song)
	{
		songQueue.add(song);
	}
	public void removeSongFromQueue()
	{
		songQueue.remove();
	}

}
