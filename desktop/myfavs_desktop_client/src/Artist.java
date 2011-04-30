import java.util.ArrayList;

public class Artist
{
	private int artist_id;
	private String name;
	private ArrayList<Album> albums;
	public Artist(int id, String n)
	{
		artist_id = id;
		name = n;
		albums = new ArrayList<Album>();
	}
	public void addAlbum(Album a)
	{
		albums.add(a);
	}
}