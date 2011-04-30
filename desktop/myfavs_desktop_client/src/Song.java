
public class Song
{
	private int song_id;
	private String name;
	private int album_id;
	private String album_name;
	private int artist_id;
	private String artist_name;
	private int track_number;
	public Song(int id, String n, int t, int ali, String aln, int ari, String arn)
	{
		song_id = id;
		name = n;
		track_number = t;
		album_id = ali;
		album_name = aln;
		artist_id = ari;
		artist_name = arn;
	}
}