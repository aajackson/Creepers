
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

import org.apache.http.client.*;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.utils.*;
import org.apache.http.*;
import org.apache.http.message.*;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;

public class myfavsCC
{
    private DefaultHttpClient httpclient;
    private HttpGet httpget;
    private HttpResponse response;
    private boolean loggedIn;

    /**
     * Main Method - Mainly for debugging.
     */
    public static void main(String[] args) throws Exception
    {
        myfavsCC temp = new myfavsCC();
		System.out.println(temp.createSong("happy go lucky",6,5,4));
    }
    
    public myfavsCC() throws Exception
    {
        httpclient = new DefaultHttpClient();
        httpget = new HttpGet(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet"));
        response = httpclient.execute(httpget);
        response.getEntity().getContent().close();
        loggedIn = false;
    }
    
    /**
     * debugging method - prints important data data.
     */
    public void printData() throws Exception
    {
        printResponse(response);
        printCookie(httpclient);
        System.out.println();
    }
    
    /**
     * debugging method - prints the cookies of responses.
     */
    public void printCookie(DefaultHttpClient httpclient)
    {
        System.out.println("-----------Cookies------------");
        List<Cookie> cookies = httpclient.getCookieStore().getCookies();
        if (cookies.isEmpty()) 
        {
            System.out.println("None");
        } 
        else 
        {
            for (int i = 0; i < cookies.size(); i++) 
            {
                System.out.println("- " + cookies.get(i).toString());
            }
        }
    }
    
    /**
     * debugging method - converts responses to Strings.
     */
    public String responseToString(HttpResponse response) throws Exception //simple test method to show the output of the request
    {
        String temp = "";
        HttpEntity entity = response.getEntity();
        if (entity != null) 
        {
            InputStream instream = entity.getContent();
            try 
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                // do something useful with the response
                temp += reader.readLine();
            } catch (IOException ex) 
            {
                // In case of an IOException the connection will be released
                // back to the connection manager automatically
                throw ex;
            } catch (RuntimeException ex) 
            {
                // In case of an unexpected exception you may want to abort
                // the HTTP request in order to shut down the underlying
                // connection and release it back to the connection manager.
                throw ex;
            } finally 
            {
                // Closing the input stream will trigger connection release
                instream.close();
            }
        }
        return temp;
    }
    
    /**
     * debugging method - prints the output of responses.
     */
    public void printResponse(HttpResponse response) throws Exception //simple test method to show the output of the request
    {
        System.out.println("------------Entity------------");
        HttpEntity entity = response.getEntity();
        if (entity != null) 
        {
            InputStream instream = entity.getContent();
            try 
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                // do something useful with the response
                System.out.println(reader.readLine());
            } catch (IOException ex) 
            {
                // In case of an IOException the connection will be released
                // back to the connection manager automatically
                throw ex;
            } catch (RuntimeException ex) 
            {
                // In case of an unexpected exception you may want to abort
                // the HTTP request in order to shut down the underlying
                // connection and release it back to the connection manager.
                throw ex;
            } finally 
            {
                // Closing the input stream will trigger connection release
                instream.close();
            }
        }
    }
    
	public String webFriendly(String in)
	{
		return in.replace( " " , "%20" );
	}

    /**
     * creates a new member with the given parameters
     **/
    public boolean createUser(String username, String password) throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=member&username=" + username + "&password=" + password));
        response = httpclient.execute(httpost);
        String temp = responseToString(response);
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); loggedIn = Boolean.parseBoolean(st.nextToken());
        return loggedIn;
    }
    
    /**
     * creates a song with the given parameters
     **/
    public Song createSong(String name, int album_id, int artist_id, int track_number) throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=song&name=" + name + "&album_id=" + album_id + "&artist_id" + artist_id + "track_number" + track_number));
        response = httpclient.execute(httpost);  
        String temp = responseToString(response);
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); boolean suceeded = Boolean.parseBoolean(st.nextToken());
        int song_id;
        if (suceeded)
        {
            st.nextToken();
            song_id = Integer.parseInt(st.nextToken());
        }
        else
        {
            song_id = 0;
        }
        Song temp2 = new Song(song_id,name,track_number,album_id,getAlbum(album_id).name,artist_id,getArtist(artist_id).name);
        return temp2;
    }
    
    /**
     * creates an album with the given parameters
     **/
    public Album createAlbum(String name, int artist_id) throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=album&name=" + name + "&artist_id" + artist_id));
        response = httpclient.execute(httpost);  
        String temp = responseToString(response);
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); boolean suceeded = Boolean.parseBoolean(st.nextToken());
        st.nextToken();
        int album_id;
        if (suceeded)
        {
            album_id = Integer.parseInt(st.nextToken());
        }
        else
        {
            album_id = 0;
        }
        Album temp2 = new Album(album_id,artist_id,getArtist(artist_id).name,name);
        return temp2;
    }
    
    /**
     * creates an artist with the given parameters
     **/
    public Artist createArtist(String name) throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=artist&name=" + name));
        response = httpclient.execute(httpost);
        String temp = responseToString(response);
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); boolean suceeded = Boolean.parseBoolean(st.nextToken());
        if (suceeded)
        {
            st.nextToken();
            int id = Integer.parseInt(st.nextToken());
            return new Artist(id,name);
        }
        else
        {
            return null;
        }
    }
    
    /**
     * creates a playlist with the given parameters
     **/
    public Playlist createPlaylist(String name, int[] song_id_array) throws Exception
    {
        String temp = "http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=playlist&name=" + name + "&songs=[";

        for(int i = 0; i < song_id_array.length; i++)
        {
            temp += song_id_array[i];
            if (i != song_id_array.length-1)
                temp += ",";
        }
        temp += "]";
        System.out.println(temp);
        HttpPost httpost = new HttpPost(webFriendly(temp));
        response = httpclient.execute(httpost);
        String temp2 = responseToString(response);
        StringTokenizer st = new StringTokenizer(temp2, "{\":[],;}");
        st.nextToken(); boolean suceeded = Boolean.parseBoolean(st.nextToken());st.nextToken();
        if (suceeded)
        {
            return getPlaylist(Integer.parseInt(st.nextToken()));
        }
        else
        {
            return null;
        }
    }
    
        /**
     * renames a playlist indicated by the playlist_id to the name given by the new_name
     **/
    public boolean renamePlaylist(String new_name, int playlist_id) throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=update&action=rename&type=playlist&name=" + new_name + "&playlist_id=" + playlist_id));
        response = httpclient.execute(httpost);
        String temp = responseToString(response);
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); boolean suceeded = Boolean.parseBoolean(st.nextToken());
        return suceeded;
    }
    
    /**
     * adds songs indicated by the song_id's in the song_id_array to the playlist indicated by the playlist_id
     **/
    public boolean addSongs(String[] song_id_array, int playlist_id) throws Exception
    {
        String temp = "http://khadajmcs.dyndns-free.com/creepers/Servlet?method=update&type=playlist&action=addsongs&song=[";
        for(int i = 0; i < song_id_array.length; i++)
        {
            temp += song_id_array;
            if (i != song_id_array.length-1)
                temp += ",";
        }
        temp += "]";
        HttpPost httpost = new HttpPost(webFriendly(temp));
        response = httpclient.execute(httpost);
        String temp2 = responseToString(response);
        StringTokenizer st = new StringTokenizer(temp2, "{\":[],;}");
        st.nextToken(); boolean suceeded = Boolean.parseBoolean(st.nextToken());
        return suceeded;
    }
    
    /**
     * Removes songs indicated by the song_is's in the song_id_array from the playlist indicated by the playlist_id
     **/
    public boolean removeSongs(int[] song_id_array, int playlist_id) throws Exception
    {
        String temp = "http://khadajmcs.dyndns-free.com/creepers/Servlet?method=update&type=playlist&action=removesongs&song=[";
        for(int i = 0; i < song_id_array.length; i++)
        {
            temp += song_id_array[i];
            if (i != song_id_array.length-1)
                temp += ",";
        }
        temp += "]";
        HttpPost httpost = new HttpPost(webFriendly(temp));
        response = httpclient.execute(httpost);
        String temp2 = responseToString(response);
        StringTokenizer st = new StringTokenizer(temp2, "{\":[],;}");
        st.nextToken(); boolean suceeded = Boolean.parseBoolean(st.nextToken());
        return suceeded;
    }
    
    /**
     * logs in a user with thie given username and password 
     **/
    public boolean login(String username, String password) throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=update&type=user&action=login&username=" + username + "&password=" + password));
        response = httpclient.execute(httpost);
        String temp = responseToString(response);
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); loggedIn = Boolean.parseBoolean(st.nextToken());
        return loggedIn;
    }
    
    /**
     * logs the current user out of their session 
     **/
    public boolean logout() throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=update&type=user&action=logout"));
        response = httpclient.execute(httpost);   
        String temp = responseToString(response);
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); loggedIn = !Boolean.parseBoolean(st.nextToken());
        return !loggedIn;
    }
     
    /**
     * deletes a playlist with thie given playlist_id 
     **/
    public boolean deletePlaylist(int playlist_id) throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=delete&type=playlist&playlist_id=" + playlist_id));
        response = httpclient.execute(httpost);
        String temp2 = responseToString(response);
        StringTokenizer st = new StringTokenizer(temp2, "{\":[],;}");
        st.nextToken(); boolean suceeded = Boolean.parseBoolean(st.nextToken());
        return suceeded;
    }
    
    public ArrayList<Member> getMember() throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=members"));
        response = httpclient.execute(httpost);
        ArrayList<Member> myMembers = new ArrayList<Member>();
        String temp = responseToString(response); String tempstr,laststr;
        int members = 0,member_id=0;
		String member_name = "";
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if ((tempstr.equalsIgnoreCase("playlists") || tempstr.equalsIgnoreCase("songs")) && st.hasMoreTokens())
				{
					tempstr = st.nextToken();
				}
                if (tempstr.equalsIgnoreCase("member_id")) //type of result (playlist, album, ect)
                {
                    member_id = Integer.parseInt(st.nextToken());st.nextToken();
                    member_name = st.nextToken();  st.nextToken(); 
                    myMembers.add(members, new Member(member_id, member_name));
                    System.out.println("added member " + member_name + " " + member_id);
                    members++;
                }
                else if(tempstr.equalsIgnoreCase("playlist_id"))
                {
                    int playlist_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String playlist_name = st.nextToken();  st.nextToken();
					st.nextToken();st.nextToken();
                    myMembers.get(members-1).addPlaylist(new Playlist(playlist_id,member_id,playlist_name,member_name));
                    System.out.println("adding playlist " + playlist_name + " " + playlist_id); 
                }
                else if(tempstr.equalsIgnoreCase("song_id")) //should never be used, but its still there.
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String album_name = st.nextToken(); st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    myMembers.get(members-1).playlists.get(myMembers.get(members-1).playlists.size()-1).addSong(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id); 
                }
            }
        }
        return myMembers;
    }
    
    public ArrayList<Member> getMember(String user_name)throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=members&search=" + user_name));
        response = httpclient.execute(httpost);
        ArrayList<Member> myMembers = new ArrayList<Member>();
        String temp = responseToString(response); String tempstr,laststr;
        int members = 0,member_id=0;
		String member_name = "";
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if ((tempstr.equalsIgnoreCase("playlists") || tempstr.equalsIgnoreCase("songs")) && st.hasMoreTokens())
				{
					tempstr = st.nextToken();
				}
                if (tempstr.equalsIgnoreCase("member_id")) //type of result (playlist, album, ect)
                {
                    member_id = Integer.parseInt(st.nextToken());st.nextToken();
                    member_name = st.nextToken();  st.nextToken(); 
                    myMembers.add(members, new Member(member_id, member_name));
                    System.out.println("added member " + member_name + " " + member_id);
                    members++;
                }
                else if(tempstr.equalsIgnoreCase("playlist_id"))
                {
                    int playlist_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String playlist_name = st.nextToken();  st.nextToken();
					st.nextToken();st.nextToken();
                    myMembers.get(members-1).addPlaylist(new Playlist(playlist_id,member_id,playlist_name,member_name));
                    System.out.println("adding playlist " + playlist_name + " " + playlist_id); 
                }
                else if(tempstr.equalsIgnoreCase("song_id")) //should never be used, but its still there.
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String album_name = st.nextToken(); st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    myMembers.get(members-1).playlists.get(myMembers.get(members-1).playlists.size()-1).addSong(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id); 
                }
            }
        }
        return myMembers;
    }
    
    public Member getMember(int user_id)throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=members&id=" + user_id));
        response = httpclient.execute(httpost);
        ArrayList<Member> myMembers = new ArrayList<Member>();
        String temp = responseToString(response); String tempstr,laststr;
        int members = 0,member_id=0;
		String member_name = "";
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if ((tempstr.equalsIgnoreCase("playlists") || tempstr.equalsIgnoreCase("songs")) && st.hasMoreTokens())
				{
					tempstr = st.nextToken();
				}
                if (tempstr.equalsIgnoreCase("member_id")) //type of result (playlist, album, ect)
                {
                    member_id = Integer.parseInt(st.nextToken());st.nextToken();
                    member_name = st.nextToken();  st.nextToken(); 
                    myMembers.add(members, new Member(member_id, member_name));
                    System.out.println("added member " + member_name + " " + member_id);
                    members++;
                }
                else if(tempstr.equalsIgnoreCase("playlist_id"))
                {
                    int playlist_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String playlist_name = st.nextToken();  st.nextToken();
					st.nextToken();st.nextToken();
                    myMembers.get(members-1).addPlaylist(new Playlist(playlist_id,member_id,playlist_name,member_name));
                    System.out.println("adding playlist " + playlist_name + " " + playlist_id); 
                }
                else if(tempstr.equalsIgnoreCase("song_id")) //should never be used, but its still there.
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String album_name = st.nextToken(); st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    myMembers.get(members-1).playlists.get(myMembers.get(members-1).playlists.size()-1).addSong(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id); 
                }
            }
        }
		if (myMembers.size() > 0)
        	return myMembers.get(0);
		else
			return null;
    }
    
    public ArrayList<Artist> getArtist() throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=artists"));
        response = httpclient.execute(httpost);
        ArrayList<Artist> myArtists= new ArrayList<Artist>();
        String temp = responseToString(response); String tempstr,laststr;
        int artists = 0;
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if ((tempstr.equalsIgnoreCase("albums") || tempstr.equalsIgnoreCase("songs")) && st.hasMoreTokens())
				{
					tempstr = st.nextToken();
				}
                if (tempstr.equalsIgnoreCase("artist_id")) //type of result (playlist, album, ect)
                {
                    int artist_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String artist_name = st.nextToken();  st.nextToken(); 
                    myArtists.add(artists, new Artist(artist_id, artist_name));
                    System.out.println("added artist " + artist_name + " " + artist_id);
                    artists++;
                }
                else if(tempstr.equalsIgnoreCase("album_id"))
                {
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String artist_name = st.nextToken();  st.nextToken(); 
                    String album_name = st.nextToken();
                    myArtists.get(artists-1).addAlbum(new Album(album_id,artist_id,artist_name,album_name));
                    System.out.println("adding album " + album_name + " " + album_id); 
                }
                else if(tempstr.equalsIgnoreCase("song_id"))
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String album_name = st.nextToken(); st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    myArtists.get(artists-1).albums.get(myArtists.get(artists-1).albums.size()-1).addSong(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id); 
                }
            }
        }
        return myArtists;
    }
    
    public ArrayList<Artist> getArtist(String art_name) throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=artists&search=" + art_name));
        response = httpclient.execute(httpost);
        ArrayList<Artist> myArtists= new ArrayList<Artist>();
        String temp = responseToString(response); String tempstr,laststr;
        int artists = 0;
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if ((tempstr.equalsIgnoreCase("albums") || tempstr.equalsIgnoreCase("songs")) && st.hasMoreTokens())
				{
					tempstr = st.nextToken();
				}
                if (tempstr.equalsIgnoreCase("artist_id")) //type of result (playlist, album, ect)
                {
                    int artist_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String artist_name = st.nextToken();  st.nextToken(); 
                    myArtists.add(artists, new Artist(artist_id, artist_name));
                    System.out.println("added artist " + artist_name + " " + artist_id);
                    artists++;
                }
                else if(tempstr.equalsIgnoreCase("album_id"))
                {
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String artist_name = st.nextToken();  st.nextToken(); 
                    String album_name = st.nextToken();
                    myArtists.get(artists-1).addAlbum(new Album(album_id,artist_id,artist_name,album_name));
                    System.out.println("adding album " + album_name + " " + album_id); 
                }
                else if(tempstr.equalsIgnoreCase("song_id"))
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String album_name = st.nextToken(); st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    myArtists.get(artists-1).albums.get(myArtists.get(artists-1).albums.size()-1).addSong(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id); 
                }
            }
        }
        return myArtists;
    }
    
    public Artist getArtist(int art_id) throws Exception    
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=artists&id=" + art_id));
        response = httpclient.execute(httpost);
        Artist myArtists = new Artist(0,"");
        String temp = responseToString(response); String tempstr,laststr;
		int artists = 0;
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if ((tempstr.equalsIgnoreCase("albums") || tempstr.equalsIgnoreCase("songs")) && st.hasMoreTokens())
				{
					tempstr = st.nextToken();
				}
                if (tempstr.equalsIgnoreCase("artist_id")) //type of result (playlist, album, ect)
                {
                    int artist_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String artist_name = st.nextToken();  st.nextToken(); 
                    myArtists = new Artist(artist_id, artist_name);
                    System.out.println("added artist " + artist_name + " " + artist_id);
                    artists++;
                }
                else if(tempstr.equalsIgnoreCase("album_id"))
                {
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String artist_name = st.nextToken();  st.nextToken(); 
                    String album_name = st.nextToken();
                    myArtists.addAlbum(new Album(album_id,artist_id,artist_name,album_name));
                    System.out.println("adding album " + album_name + " " + album_id); 
                }
                else if(tempstr.equalsIgnoreCase("song_id"))
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String album_name = st.nextToken(); st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    myArtists.albums.get(myArtists.albums.size()-1).addSong(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id); 
                }
            }
        }
        return myArtists;
    }
    
    public ArrayList<Song> getSong() throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=songs"));
        response = httpclient.execute(httpost);
        ArrayList<Song> mySongs= new ArrayList<Song>();
        String temp = responseToString(response); String tempstr,laststr;
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if(tempstr.equalsIgnoreCase("song_id"))
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String album_name = st.nextToken(); st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    mySongs.add(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id); 
                }
            }
        }
        return mySongs;
    }
    
    public ArrayList<Song> getSong(String s_name) throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=songs&search="+ s_name));
        response = httpclient.execute(httpost);
        ArrayList<Song> mySongs= new ArrayList<Song>();
        String temp = responseToString(response); String tempstr,laststr;
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if(tempstr.equalsIgnoreCase("song_id"))
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String album_name = st.nextToken(); st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    mySongs.add(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id); 
                }
            }
        }
        return mySongs;
    }
    
    public Song getSong(int s_id) throws Exception
    {
        ArrayList<Song> mySongs = getSong();
        for (int x=0;x<mySongs.size();x++)
        {
            if (s_id == mySongs.get(x).song_id)
                return mySongs.get(x);
        }
        return null;
    }
    
    public ArrayList<Playlist> getPlaylist() throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=playlists"));
        response = httpclient.execute(httpost);
        ArrayList<Playlist> myPlaylists= new ArrayList<Playlist>();
        String temp = responseToString(response); String tempstr,laststr;
        int playlists = 0;
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if ((tempstr.equalsIgnoreCase("songs")) && st.hasMoreTokens())
				{
					tempstr = st.nextToken();
				}
                if (tempstr.equalsIgnoreCase("playlist_id")) //type of result (playlist, album, ect)
                {
                    int playlist_id = Integer.parseInt(st.nextToken());st.nextToken();
					System.out.println (playlist_id);
                    String playlist_name = st.nextToken();  st.nextToken();
					int member_id = Integer.parseInt(st.nextToken());st.nextToken();
					String member_name = st.nextToken();
                    myPlaylists.add(playlists, new Playlist(playlist_id, member_id, playlist_name, member_name));
                    System.out.println("added playlist " + playlist_name + " " + playlist_id);
                    playlists++;
                }
				else if(tempstr.equalsIgnoreCase("song_id"))
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String album_name = st.nextToken(); st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    myPlaylists.get(playlists-1).addSong(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id + " " +track_number); 
                }
            }
        }
        return myPlaylists;
    }
    
    public ArrayList<Playlist> getPlaylist(String playlist_n) throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=playlists&search=" + playlist_n));
        response = httpclient.execute(httpost);
        ArrayList<Playlist> myPlaylists= new ArrayList<Playlist>();
        String temp = responseToString(response); String tempstr,laststr;
        int playlists = 0;
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if ((tempstr.equalsIgnoreCase("songs")) && st.hasMoreTokens())
				{
					tempstr = st.nextToken();
				}
                if (tempstr.equalsIgnoreCase("playlist_id")) //type of result (playlist, album, ect)
                {
                    int playlist_id = Integer.parseInt(st.nextToken());st.nextToken();
					System.out.println (playlist_id);
                    String playlist_name = st.nextToken();  st.nextToken();
					int member_id = Integer.parseInt(st.nextToken());st.nextToken();
					String member_name = st.nextToken();
                    myPlaylists.add(playlists, new Playlist(playlist_id, member_id, playlist_name, member_name));
                    System.out.println("added playlist " + playlist_name + " " + playlist_id);
                    playlists++;
                }
				else if(tempstr.equalsIgnoreCase("song_id"))
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String album_name = st.nextToken(); st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    myPlaylists.get(playlists-1).addSong(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id + " " +track_number); 
                }
            }
        }
        return myPlaylists;
    }
    
    public Playlist getPlaylist(int pl_id) throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=playlists&id=" + pl_id));
        response = httpclient.execute(httpost);
        ArrayList<Playlist> myPlaylists= new ArrayList<Playlist>();
        String temp = responseToString(response); String tempstr,laststr;
        int playlists = 0;
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if ((tempstr.equalsIgnoreCase("songs")) && st.hasMoreTokens())
				{
					tempstr = st.nextToken();
				}
                if (tempstr.equalsIgnoreCase("playlist_id")) //type of result (playlist, album, ect)
                {
                    int playlist_id = Integer.parseInt(st.nextToken());st.nextToken();
					System.out.println (playlist_id);
                    String playlist_name = st.nextToken();  st.nextToken();
					int member_id = Integer.parseInt(st.nextToken());st.nextToken();
					String member_name = st.nextToken();
                    myPlaylists.add(playlists, new Playlist(playlist_id, member_id, playlist_name, member_name));
                    System.out.println("added playlist " + playlist_name + " " + playlist_id);
                    playlists++;
                }
				else if(tempstr.equalsIgnoreCase("song_id"))
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String album_name = st.nextToken(); st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    myPlaylists.get(playlists-1).addSong(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id + " " +track_number); 
                }
            }
        }
		if (myPlaylists.size() > 0)
        	return myPlaylists.get(0);
		else
			return null;
    }
    
    public ArrayList<Playlist> getMemberPlaylist(int member_id) throws Exception
    {
        ArrayList<Playlist> myPlaylists = getPlaylist();
		ArrayList<Playlist> membersPlaylists = getPlaylist();
        for (int x=0;x<myPlaylists.size();x++)
        {
            if (member_id == myPlaylists.get(x).member_id)
                membersPlaylists.add(myPlaylists.get(x));
        }
		if (membersPlaylists.size() > 0)
        	return membersPlaylists;
		else
			return null;
    }
    
    public ArrayList<Playlist> getMemberPlaylist(String member_name) throws Exception
    {
        ArrayList<Playlist> myPlaylists = getPlaylist();
		ArrayList<Playlist> membersPlaylists = getPlaylist();
        for (int x=0;x<myPlaylists.size();x++)
        {
            if (member_name == myPlaylists.get(x).username)
                membersPlaylists.add(myPlaylists.get(x));
        }
		if (membersPlaylists.size() > 0)
        	return membersPlaylists;
		else
			return null;
    }
    
    public ArrayList<Album> getAlbum() throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=albums"));
        response = httpclient.execute(httpost);
        ArrayList<Album> myAlbums= new ArrayList<Album>();
        String temp = responseToString(response); String tempstr,laststr;
        int albums = 0; String album_name = "";
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if ((tempstr.equalsIgnoreCase("songs")) && st.hasMoreTokens())
				{
					tempstr = st.nextToken();
				}
                if(tempstr.equalsIgnoreCase("album_id"))
                {
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String artist_name = st.nextToken();  st.nextToken(); 
                    album_name = st.nextToken();
                    myAlbums.add(new Album(album_id,artist_id,artist_name,album_name));
                    System.out.println("adding album " + album_name + " " + album_id); 
					albums++;
                }
                else if(tempstr.equalsIgnoreCase("song_id"))
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    myAlbums.get(albums-1).addSong(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id); 
                }
            }
        }
        return myAlbums;
    }
    
    public ArrayList<Album> getAlbum(String album_n) throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=albums&search=" + album_n));
        response = httpclient.execute(httpost);
        ArrayList<Album> myAlbums= new ArrayList<Album>();
        String temp = responseToString(response); String tempstr,laststr;
        int albums = 0; String album_name = "";
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if ((tempstr.equalsIgnoreCase("songs")) && st.hasMoreTokens())
				{
					tempstr = st.nextToken();
				}
                if(tempstr.equalsIgnoreCase("album_id"))
                {
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String artist_name = st.nextToken();  st.nextToken(); 
                    album_name = st.nextToken();
                    myAlbums.add(new Album(album_id,artist_id,artist_name,album_name));
                    System.out.println("adding album " + album_name + " " + album_id); 
					albums++;
                }
                else if(tempstr.equalsIgnoreCase("song_id"))
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    myAlbums.get(albums-1).addSong(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id); 
                }
            }
        }
        return myAlbums;
    }
    
    public Album getAlbum(int alb_id) throws Exception
    {
        HttpPost httpost = new HttpPost(webFriendly("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=albums&id=" + alb_id));
        response = httpclient.execute(httpost);
        ArrayList<Album> myAlbums= new ArrayList<Album>();
        String temp = responseToString(response); String tempstr,laststr;
        int albums = 0; String album_name = "";
        StringTokenizer st = new StringTokenizer(temp, "{\":[],;}");
        st.nextToken(); //success:
        
        if (st.nextToken().equalsIgnoreCase("true")) //true or false
        {
            st.nextToken(); //results:
            while(st.hasMoreTokens())
            {
                tempstr = st.nextToken();
				if ((tempstr.equalsIgnoreCase("songs")) && st.hasMoreTokens())
				{
					tempstr = st.nextToken();
				}
                if(tempstr.equalsIgnoreCase("album_id"))
                {
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String artist_name = st.nextToken();  st.nextToken(); 
                    album_name = st.nextToken();
                    myAlbums.add(new Album(album_id,artist_id,artist_name,album_name));
                    System.out.println("adding album " + album_name + " " + album_id); 
					albums++;
                }
                else if(tempstr.equalsIgnoreCase("song_id"))
                {
                    int song_id = Integer.parseInt(st.nextToken());st.nextToken();
                    String song_name = st.nextToken();  st.nextToken(); 
                    int album_id = Integer.parseInt(st.nextToken());st.nextToken();
                    int artist_id = Integer.parseInt(st.nextToken()); st.nextToken();
                    String artist_name = st.nextToken(); st.nextToken();
                    int track_number = Integer.parseInt(st.nextToken());
                    myAlbums.get(albums-1).addSong(new Song(song_id,song_name,track_number, album_id, album_name, artist_id, artist_name));
                    System.out.println("adding song " + song_name + " " + song_id); 
                }
            }
        }
		if (myAlbums.size() > 0)
        	return myAlbums.get(0);
		else
			return null;
    }
}