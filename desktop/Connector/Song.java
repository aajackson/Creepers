
public class Song
{
    public int song_id;
    public String name;
    public int album_id;
    public String album_name;
    public int artist_id;
    public String artist_name;
    public int track_number;
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
    public String toString()
    {
        String temp = "song id:" + song_id + " song name:" + name + " track number:" + track_number;
        return temp;
    }
}