import java.util.ArrayList;

public class Album
{
	private int album_id;
	private int artist_id;
	private String artist;
	private String name;
	private ArrayList<Song> songs;
	public Album(int id, int a, String ar, String n)
	{
		album_id = id;
		artist_id = a;
		artist = ar;
		name = n;
		songs = new ArrayList<Song>();
	}
	public void addSong(Song s)
	{
		songs.add(s);
	}
}