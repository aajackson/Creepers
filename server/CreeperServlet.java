import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import java.util.ArrayList;

import com.google.gson.Gson;

public class CreeperServlet extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		doStuff(req, res);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		doStuff(req, res);
	}
	public void doStuff(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		/*
		HttpSession session = req.getSession(true);
		session.setAttribute("key", "value");
		(String)session.getAttribute("key")
		*/
		/*
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.close();
		*/
		/*
		res.sendRedirect("success.html");
		*/
		Gson gson = new Gson();
		res.setContentType("text/html");
		//res.setContentType("application/json");
		
		PrintWriter out = res.getWriter();
		DBAL db = DBAL.getInstance(out);
		
		HttpSession session = req.getSession(true);
		
		String uidString = (String)session.getAttribute("user_id");
		int uid = 0; //user id is 0 when not logged in
		if (uidString != null)
			uid = Integer.parseInt(uidString);
		
		String method = req.getParameter("method");
		String type = req.getParameter("type");
		
		if (method == null || type == null)
		{ 
			out.println("{success:false,error:\"Missing a method or type parameter. Request aborted.\"}");
			return;
		}
		
		if (method.equalsIgnoreCase("create"))
		{
			//check for login when needed
			if (!type.equalsIgnoreCase("member") && uid == 0)
			{
				out.println("{success:false,error:\"You need to be logged in to do this action.\"}");
				return;
			}
			else if (type.equalsIgnoreCase("member") && uid != 0)
			{
				out.println("{success:false,error:\"You need to be logged out to do this action.\"}");
				return;
			}
			if (type.equalsIgnoreCase("song"))
			{
				//create song, assigned to album and artist
				String name = req.getParameter("name");
				String album_id = req.getParameter("album_id");
				String artist_id = req.getParameter("artist_id");
				String track_number = req.getParameter("track_number");
				// Do we have all parameters?
				if (name == null || album_id == null || track_number == null || artist_id == null) 
				{
					out.println("{success:false,error:\"Missing a parameter needed for song creation.\"}");
					return;
				}
				try 
				{
					// Check all numbers are in range or actually integers
					if (Integer.parseInt(artist_id) < 1 || Integer.parseInt(album_id) < 1 || Integer.parseInt(track_number) < 1)
					{
						throw new NumberFormatException();
					}
					// Check if valid IDs
					ResultSet rs = db.query("SELECT album_id FROM album WHERE album_id = " + Integer.parseInt(album_id));
					if (!rs.next())
					{
						out.println("{success:false,error:\"There is no album associated with the album id specified.\"}");
						return;
					}
					rs = db.query("SELECT artist_id FROM artist WHERE artist_id = " + Integer.parseInt(artist_id));
					if (!rs.next())
					{
						out.println("{success:false,error:\"There is no artist associated with the artist id specified.\"}");
						return;
					}
					// Insertion time!
					PreparedStatement query = db.preparedStatement("INSERT INTO song (name, album_id, artist_id, track_number) VALUES (?, ?, ?, ?)");
					query.setString(1, name);
					query.setInt(2, Integer.parseInt(album_id));
					query.setInt(3, Integer.parseInt(artist_id));
					query.setInt(4, Integer.parseInt(track_number));
					query.executeUpdate();
					rs = db.query("SELECT LAST_INSERT_ID()");
					rs.next();
					out.println("{success:true;id:"+rs.getInt(1)+"}");
					return;
				}
				// Invalid numbers!
				catch (NumberFormatException e)
				{
					out.println("{success:false,error:\"One of the integer parameters was out of range or an invalid number.\"}");
					return;
				}
				// Oh noes!
				catch (Exception e)
				{
					out.println("{success:false,error:\"There was an error in running the insertion.\"}");
					//out.println(e);
					return;
				}
			}
			else if (type.equalsIgnoreCase("album"))
			{
				//create album, assigned to artist
				String name = req.getParameter("name");
				String artist_id = req.getParameter("artist_id");
				// Do we have all parameters?
				if (name == null || artist_id == null) 
				{
					out.println("{success:false,error:\"Missing a parameter needed for album creation.\"}");
					return;
				}
				try 
				{
					// Check all numbers are in range or actually integers
					if (Integer.parseInt(artist_id) < 1)
					{
						throw new NumberFormatException();
					}
					// Check if valid IDs
					ResultSet rs = db.query("SELECT artist_id FROM artist WHERE artist_id = " + Integer.parseInt(artist_id));
					if (!rs.next())
					{
						out.println("{success:false,error:\"There is no artist associated with the artist id specified.\"}");
						return;
					}
					// Insertion time!
					PreparedStatement query = db.preparedStatement("INSERT INTO album (name, artist_id) VALUES (?, ?)");
					query.setString(1, name);
					query.setInt(2, Integer.parseInt(artist_id));
					query.executeUpdate();
					rs = db.query("SELECT LAST_INSERT_ID()");
					rs.next();
					out.println("{success:true;id:"+rs.getInt(1)+"}");
					return;
				}
				// Invalid numbers!
				catch (NumberFormatException e)
				{
					out.println("{success:false,error:\"One of the integer parameters was out of range or an invalid number.\"}");
					return;
				}
				// Oh noes!
				catch (Exception e)
				{
					out.println("{success:false,error:\"There was an error in running the insertion.\"}");
					//out.println(e);
					return;
				}
			}
			else if (type.equalsIgnoreCase("artist"))
			{
				//create artist
				String name = req.getParameter("name");
				// Do we have all parameters?
				if (name == null) 
				{
					out.println("{success:false,error:\"Missing a parameter needed for artist creation.\"}");
					return;
				}
				try 
				{
					// Insertion time!
					PreparedStatement query = db.preparedStatement("INSERT INTO artist (name) VALUES (?)");
					query.setString(1, name);
					query.executeUpdate();
					ResultSet rs = db.query("SELECT LAST_INSERT_ID()");
					rs.next();
					out.println("{success:true;id:"+rs.getInt(1)+"}");
					return;
				}
				// Oh noes!
				catch (Exception e)
				{
					out.println("{success:false,error:\"There was an error in running the insertion.\"}");
					//out.println(e);
					return;
				}
			}
			else if (type.equalsIgnoreCase("playlist"))
			{
				//create playlist (from list of songs)
				String name = req.getParameter("name");
				String songs = req.getParameter("songs");
				// Do we have all parameters?
				if (name == null || songs == null) 
				{
					out.println("{success:false,error:\"Missing a parameter needed for playlist creation.\"}");
					return;
				}
				int[] song_ids;
				try
				{
					//get the integer array of song ids
					song_ids = gson.fromJson(songs, int[].class); 
					for (int i = 0; i < song_ids.length; i++)
					{
						//check if each song id valid
						if (song_ids[i] < 1) throw new Exception();
					}
					//clip off the square brackets
					String song_id_list = songs.substring(1, songs.length() - 1);
					//check all song ids are valid
					ResultSet rs = db.query("SELECT COUNT(*) FROM song WHERE song_id IN ("+song_id_list+")");
					rs.next();
					if (rs.getInt(1) != song_ids.length) //check length of results
					{
						out.println("{success:false,error:\"At least one of the provided songs does not exist.\"}");
						return;
					}
					//all good, insertion time!
					PreparedStatement query = db.preparedStatement("INSERT INTO playlist (name, member_id) VALUES (?, ?)");
					query.setString(1, name);
					query.setInt(2, uid);
					query.executeUpdate();
					rs = db.query("SELECT LAST_INSERT_ID()");
					rs.next();
					int playlist_id = rs.getInt(1);
					for (int i = 0; i < song_ids.length; i++)
					{
						query = db.preparedStatement("INSERT INTO playlistsong (playlist_id, song_id, track_number) VALUES (?, ?, ?)");
						query.setInt(1, playlist_id);
						query.setInt(2, song_ids[i]);
						query.setInt(3, i);
						query.executeUpdate();
					}
					out.println("{success:true;id:"+playlist_id+"}");
					return;
				}
				catch (Exception e)
				{
					out.println("{success:false,error:\"The list of song ids is malformed, contains invalid numbers, or has numbers outside the valid range.\"}");
					//out.println(e.getMessage());
					return;
				}
			}
			else if (type.equalsIgnoreCase("member"))
			{
				//create a new member
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				// Do we have all parameters?
				if (username == null || password == null) 
				{
					out.println("{success:false,error:\"Missing a parameter needed for member creation.\"}");
					return;
				}
				try 
				{
					PreparedStatement query = db.preparedStatement("SELECT username FROM member WHERE username = ?");
					query.setString(1, username);
					ResultSet rs = query.executeQuery();
					if (rs.next())
					{
						out.println("{success:false,error:\"There is already a user with this username.\"}");
						return;
					}
					query = db.preparedStatement("INSERT INTO member (username, password) VALUES (?, ?)");
					query.setString(1, username);
					query.setString(2, password);
					query.executeUpdate();
					rs = db.query("SELECT LAST_INSERT_ID()");
					rs.next();
					uid = rs.getInt(1);
					session.setAttribute("user_id", "" + uid);
					out.println("{success:true}");
				}
				catch (Exception e)
				{
					out.println("{success:false,error:\"There was an error in creating a new member.\"}");
					//out.println(e.getMessage());
					return;
				}
			}
		}
		else if (method.equalsIgnoreCase("read"))
		{
			if (type.equalsIgnoreCase("playlists"))
			{
				//get list of playlists [name, list of songs]
				//	can search by member or random
				//	can sort by playlist name
				//	can also specify by id to return that playlist
			}
			else if (type.equalsIgnoreCase("artists"))
			{
				//get list of artists [name, list of albums]
				//	can search by artist info
				//	can sort by artist name
				//	can also specify by id to return that artist
			}
			else if (type.equalsIgnoreCase("albums"))
			{
				//get list of albums [name, artist info, list of songs]
				//	can search by album name
				//	can sort by album name, artist name 
				//	can also specify an id to return that album
			}
			else if (type.equalsIgnoreCase("members"))
			{
				//get list of members [info, list of playlists]
				//	can search by member name
				//	can sort by member name
				//	can also specify an id to return that member
			}
		}
		else if (method.equalsIgnoreCase("update"))
		{
			String action = req.getParameter("action");
			if (type.equalsIgnoreCase("playlist"))
			{
				String playlist_id = req.getParameter("playlist_id");
				if (playlist_id == null)
				{
					out.println("{success:false,error:\"No playlist was specified.\"}");
					return;
				}
				try
				{
					PreparedStatement query = db.preparedStatement("SELECT member_id FROM playlist WHERE playlist_id = ? AND member_id = ?");
					query.setInt(1, Integer.parseInt(playlist_id));
					query.setInt(2, uid);
					ResultSet rs = query.executeQuery();
					if (!rs.next())
					{
						out.println("{success:false,error:\"The specified playlist could not be found.\"}");
						return;
					}
				}
				catch (Exception e)
				{
					out.println("{success:false,error:\"There was an error in checking the playlist.\"}");
					//out.println(e);
					return;
				}
				if (action.equalsIgnoreCase("rename"))
				{
					//renames playlist
					String name = req.getParameter("name");
					// Do we have all parameters?
					if (name == null) 
					{
						out.println("{success:false,error:\"Missing a parameter needed for playlist update.\"}");
						return;
					}
					try 
					{
						PreparedStatement query = db.preparedStatement("UPDATE playlist SET name = ? WHERE playlist_id = ?");
						query.setString(1, name);
						query.setInt(2, Integer.parseInt(playlist_id));
						query.executeUpdate();
						out.println("{success:true}");
					}
					catch (Exception e)
					{
						out.println("{success:false,error:\"There was an error in renaming the playlist.\"}");
						//out.println(e);
						return;
					}
					out.println("{success:true}");
					return;
				}
				else if (action.equalsIgnoreCase("addsongs"))
				{
					//adds list of song ids to playlist
					String songs = req.getParameter("songs");
					// Do we have all parameters?
					if (songs == null) 
					{
						out.println("{success:false,error:\"Missing a parameter needed for playlist update.\"}");
						return;
					}
					try 
					{
						int[] song_ids = gson.fromJson(songs, int[].class);
						for (int i = 0; i < song_ids.length; i++)
						{
							//check if each song id valid
							if (song_ids[i] < 1) throw new Exception();
						}
						//clip off the square brackets
						String song_id_list = songs.substring(1, songs.length() - 1);
						//check all songs exist
						ResultSet rs = db.query("SELECT COUNT(*) FROM song WHERE song_id IN ("+song_id_list+")");
						rs.next();
						if (rs.getInt(1) != song_ids.length) //check length of results
						{
							out.println("{success:false,error:\"At least one of the provided songs does not exist.\"}");
							return;
						}
						//remove any songs already in the playlist
						rs = db.query("SELECT song_id FROM playlistsong WHERE playlist_id = "+Integer.parseInt(playlist_id)+" AND song_id IN ("+song_id_list+")");
						ArrayList<Integer> existing = new ArrayList<Integer>();
						while (rs.next())
						{
							existing.add(new Integer(rs.getInt("song_id")));
						}
						ArrayList<Integer> adding = new ArrayList<Integer>();
						for (int i = 0; i < song_ids.length; i++)
						{
							//only add songs to the add list if they don't already exist
							if (!existing.contains(new Integer(song_ids[i])))
								adding.add(new Integer(song_ids[i]));
						}
						//get the current highest value of track num
						rs = db.query("SELECT MAX(track_number) FROM playlistsong WHERE playlist_id = "+Integer.parseInt(playlist_id));
						rs.next();
						int nextTrackNum = rs.getInt(1) + 1;
						
						//finally add the songs to the playlist
						for (int i = 0; i < adding.size(); i++)
						{
							PreparedStatement query = db.preparedStatement("INSERT INTO playlistsong (playlist_id, song_id, track_number) VALUES (?, ?, ?)");
							query.setInt(1, Integer.parseInt(playlist_id));
							query.setInt(2, adding.get(i).intValue());
							query.setInt(3, nextTrackNum++);
							query.executeUpdate();
						}
						out.println("{success:true}");
						return;
					}
					catch (Exception e)
					{
						out.println("{success:false,error:\"The list of song ids is malformed, contains invalid numbers, or has numbers outside the valid range.\"}");
						//out.println(e);
						return;
					}
				}
				else if (action.equalsIgnoreCase("removesongs"))
				{
					//removes list of song ids from playlist
					String songs = req.getParameter("songs");
					// Do we have all parameters?
					if (songs == null) 
					{
						out.println("{success:false,error:\"Missing a parameter needed for playlist update.\"}");
						return;
					}
					try 
					{
						
					}
					catch (Exception e)
					{
						out.println("{success:false,error:\"There was an error in removing songs from the playlist.\"}");
						//out.println(e);
						return;
					}
				}
			}
			else if (type.equalsIgnoreCase("user"))
			{
				if (action.equalsIgnoreCase("login") && uid != 0)
				{
					out.println("{success:false,error:\"You are already logged in.\"}");
					return;
				}
				if (action.equalsIgnoreCase("logout") && uid == 0)
				{
					out.println("{success:false,error:\"You are not logged in.\"}");
					return;
				}
				if (action.equalsIgnoreCase("login"))
				{
					//login user
					String username = req.getParameter("username");
					String password = req.getParameter("password");
					// Do we have all parameters?
					if (username == null || password == null) 
					{
						out.println("{success:false,error:\"Missing a parameter needed for login.\"}");
						return;
					}
					try
					{
						PreparedStatement query = db.preparedStatement("SELECT member_id FROM member WHERE username = ? AND password = ?");
						query.setString(1, username);
						query.setString(2, password);
						ResultSet rs = query.executeQuery();
						if (rs.next())
						{
							session.setAttribute("user_id", "" + rs.getInt("member_id"));
							out.println("{success:true}");
							return;
						}
						else
						{
							session.setAttribute("user_id", "" + 0);
							out.println("{success:false,error:\"Invalid username/password combination.\"}");
							return;
						}
					}
					catch (Exception e)
					{
						out.println("{success:false,error:\"There was an error in logging in.\"}");
						//out.println(e);
						return;
					}
				}
				else if (action.equalsIgnoreCase("logout"))
				{
					//logout user
					session.setAttribute("user_id", "" + 0);
					out.println("{success:true}");
				}
			}
		}
		else if (method.equalsIgnoreCase("delete"))
		{
			if (uid == 0)
			{
				out.println("{success:false,error:\"You need to be logged in to do this action.\"}");
				return;
			}
			if (type.equalsIgnoreCase("playlist"))
			{
				//deletes playlist with given id 
				String playlist_id = req.getParameter("playlist_id");
				// Do we have all parameters?
				if (playlist_id == null) 
				{
					out.println("{success:false,error:\"Missing a parameter needed for playlist deletion.\"}");
					return;
				}
				try
				{
					ResultSet rs = db.query("SELECT playlist_id FROM playlist WHERE playlist_id = " + Integer.parseInt(playlist_id) + " AND member_id = " + uid);
					if (!rs.next())
					{
						out.println("{success:false,error:\"The specified playlist does not exist.\"}");
						return;
					}
					db.query("DELETE FROM playlist WHERE playlist_id = " + Integer.parseInt(playlist_id) + " AND member_id = " + uid);
					db.query("DELETE FROM playlistsong WHERE playlist_id = " + Integer.parseInt(playlist_id));
					out.println("{success:true}");
				}
				catch (NumberFormatException e)
				{
					out.println("{success:false,error:\"One of the integer parameters was out of range or an invalid number.\"}");
					return;
				}
				// Oh noes!
				catch (Exception e)
				{
					out.println("{success:false,error:\"There was an error in running the deletion.\"}");
					//out.println(e);
					return;
				}
			}
		}
		out.println("{success:false,error:\"This action does not exist.\"}");
		out.close();
	}
}