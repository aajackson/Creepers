import java.util.ArrayList;

public class Member
{
	public int member_id;
	public String username;
	public ArrayList<Playlist> playlists;
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