import java.util.ArrayList;

public class Playlist
{
	private int playlist_id;
	private int member_id;
	private String playlist_name;
	private ArrayList<Song> songs;
	public Playlist(int p, int m, String n)
	{
		playlist_id = p;
		member_id = m;
		playlist_name = n;
		songs = new ArrayList<Song>();
	}
	public void addSong(Song s)
	{
		songs.add(s);
	}
}