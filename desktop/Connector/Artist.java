import java.util.ArrayList;

public class Artist
{
    public int artist_id;
    public String name;
    public ArrayList<Album> albums;
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
    public String toString()
    {
        String temp = "artist id:" + artist_id + " artist name:" + name + " albums:";
        for (int x=0;x<albums.size();x++)
            temp += albums.get(x).toString() + " ";
         return temp;
    }
}