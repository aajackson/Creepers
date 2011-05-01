import java.util.ArrayList;

public class Member
{
	private int member_id;
	private String username;
	private ArrayList<Playlist> playlists;
	public Member(int id, String u)
	{
		member_id = id;
		username = u;
		playlists = new ArrayList<Playlist>();
	}
	public void addPlaylist(Playlist p)
	{
		playlists.add(p);	
	}
}