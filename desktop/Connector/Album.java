import java.util.ArrayList;

public class Album
{
    public int album_id;
    public int artist_id;
    public String artist;
    public String name;
    public ArrayList<Song> songs;
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
    public String toString()
    {
        String temp = "album id:" + artist_id + " album name:" + name + " songs:";
        for (int x=0;x<songs.size();x++)
            temp += songs.get(x).toString() + " ";
         return temp;
    }
}