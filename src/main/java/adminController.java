import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.netbuilder.thejuke.entities.Album;
import com.netbuilder.thejuke.entities.Artist;
import com.netbuilder.thejuke.entities.Song;
import com.netbuilder.thejuke.services.AlbumService;
import com.netbuilder.thejuke.services.SongService;
import com.netbuilder.thejuke.web.SearchController;

/**The bean that manages the Admin page. **/
public class adminController
{
	@Inject
	public SearchController searchController;
	
	@Inject
	public AlbumService albumService;
	
	@Inject
	public SongService songService;
	
	private long songID;
	
	private String songSearch;

	private List<Song> songList;
	private String songNewPrice;
	public List<Song> getSongList() {
		return songList;
	}

	public AlbumService getAlbumService() {
		return albumService;
	}

	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}
	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}


	public SearchController getSearchController() {
		return searchController;
	}

	public void setSearchController(SearchController searchController) {
		this.searchController = searchController;
	}

	public String getSongSearch() {
		return songSearch;
	}

	public SongService getSongService() {
		return songService;
	}
	public void setSongService(SongService songService) {
		this.songService = songService;
	}
	public long getSongID() {
		return songID;
	}
	public void setSongID(long songID) {
		this.songID = songID;
	}
	
	public void setSongSearch(String songSearch) {
		this.songSearch = songSearch;
	}
	
	public String getSongNewPrice() {
		return songNewPrice;
	}

	public void setSongNewPrice(String songNewPrice) {
		this.songNewPrice = songNewPrice;
	}
	public String doSearchBySongName()
	{
		//songList=searchController.searchByName(songSearch);
		//songSearch=songList.get(0).getName();
		return "#";
	}
	public String findAlbumBySong(Song song)
	{
			List<Album> albumList = new ArrayList<Album>();
			if (song != null) 
			{
				albumList=albumService.findBySong(song);
			}

		return albumList.get(0).getName();
	}
	public String doChangeSongPrice(String songIDString)
	{
		System.out.println("Unparsed String "+songIDString);
		Integer songId = Integer.parseInt(songIDString);
		System.out.println("PARSED PARSED PARSED PARSED"+songID);
//		Song song= songService.findSong(songID);
//		song.setCost(.05F);
//
//		System.out.println(songNewPrice);
//		songSearch=FacesContext.getCurrentInstance().
//				getExternalContext().getRequestParameterMap().get("hidden1");
//		System.out.println(songSearch);
//		 songService.updateSong(song);
		return "#";
	}
	
}
