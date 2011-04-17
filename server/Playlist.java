import java.util.ArrayList;

public class Playlist
{
	private int playlist_id;
	private String name;
	private int member_id;
	private String username;
	private ArrayList<Song> songs;
	public Playlist(int p, int m, String n, String u)
	{
		playlist_id = p;
		member_id = m;
		name = n;
		username = u;
		songs = new ArrayList<Song>();
	}
	public void addSong(Song s)
	{
		songs.add(s);
	}
}