import java.util.ArrayList;

public class Playlist
{
	public int playlist_id;
	public String name;
	public int member_id;
	public String username;
	public ArrayList<Song> songs;
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