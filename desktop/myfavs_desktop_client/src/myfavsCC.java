
/**
 * This is the myfavs Command Connection class
 * 
 * The point in this class is to provide a simple way for a java application to send http requests to a server running apache tomcat
 * 
 * @author James "T.J." Moats
 * @version 4/25/2011
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class myfavsCC
{
    private URL urlsrc;
    private URLConnection urlconn;
    private BufferedReader in;
    private String inputLine;
    private String myCookie;
   
    public static void main(String[] args) throws Exception
    {
    	myCookie = "";
    	myfavsCC temp = new myfavsCC();
    	temp.logIn("tj", "tj");
    	System.out.println(myCookie);
    	try {Thread.sleep(2000);}catch(InterruptedException e){}
    	
    }
    
    public myfavsCC()
    {
    	
    }
    
    public void logIn(String username, String password) throws Exception
    {
        String temp = ("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=update&type=user&username=" + username + "&password=" + password);
        urlsrc = new URL(temp);
        urlconn = urlsrc.openConnection();
        urlconn.connect();
        getCookie(urlconn);
    }
    
    public  void getCookie(URLConnection c)
    {
    	String headerName=null;
    	for (int i=1; (headerName = c.getHeaderFieldKey(i))!=null; i++) 
    	{
    	 	if (headerName.equals("Set-Cookie")) 
    	 	{                  
    	 		myCookie = c.getHeaderField(i);
    	 	}
    	}	
    }
    
    public  void setCookie(URLConnection c)
    {
        c.setRequestProperty("Cookie", myCookie);
    }
    
    public  String createMember(String username, String password) throws Exception
    {
        String temp = ("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=member&username=" + username + "&password=" + password);
        urlsrc = new URL(temp);
        urlconn = urlsrc.openConnection();
        in = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
        inputLine = in.readLine();
        getCookie(urlconn);
        if (inputLine != null)
            return inputLine;
        else
            return "ERROR";
    }
    
    public  String createSong(String name, String album_id, String artist_id, String track_number) throws Exception
    {
        String temp = ("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=song&name=" + name + "&album_id=" + album_id + "&artist_id" + artist_id + "&track_number" + track_number);
        urlsrc = new URL(temp);
        setCookie(urlconn);
        urlconn = urlsrc.openConnection();
        in = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
        inputLine = in.readLine();
        if (inputLine != null)
            return inputLine;
        else
            return "ERROR";
    }
    
    public  String createAlbum(String name, String artist_id) throws Exception
    {
        String temp = ("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=song&name=" + name + "&artist_id=" + artist_id);
        urlsrc = new URL(temp);
        setCookie(urlconn);
        urlconn = urlsrc.openConnection();
        in = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
        inputLine = in.readLine();
        if (inputLine != null)
            return inputLine;
        else
            return "ERROR";
    }
    
    public  String createArtist(String name) throws Exception
    {
        String temp = ("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=song&name=" + name);
        urlsrc = new URL(temp);
        setCookie(urlconn);
        urlconn = urlsrc.openConnection();
        in = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
        inputLine = in.readLine();
        if (inputLine != null)
            return inputLine;
        else
            return "ERROR";
    }
    
    public  String createPlaylist(String name, String[] songs) throws Exception
    {
        if (songs.length > 0)
        {
            String temp = ("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=song&name=" + name + "&songs=[");
            for(int counter = 0;counter < songs.length; counter++)
            {
                temp += songs[counter] + ",";
            }
            temp += "]";
            urlsrc = new URL(temp);
            setCookie(urlconn);
            urlconn = urlsrc.openConnection();
            in = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
            inputLine = in.readLine();
            if (inputLine != null)
                return inputLine;
            else
                return "ERROR";
        }
        else
        {
            return "No Songs Selected";
        }
    }
    
    public  Playlist readPlaylist(String playlist_id)
    {
        return null;
    }
    
}

//http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=member&username=tj&password=tj