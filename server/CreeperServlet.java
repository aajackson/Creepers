import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

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
		
		HttpSession session = req.getSession(true);
		boolean isLoggedIn = (boolean)session.getAttribute("logged-in")
		
		String method = req.getParameter("method");
		String type = req.getParameter("type");
		switch (method)
		{
			case "create":
				switch (type)
				{
					case "song":
						//create song, assigned to album
						break;
					case "album":
						//create album, assigned to artist
						break;
					case "artist":
						//create artist
						break;
					case "playlist":
						//create playlist (from list of songs)
						break;
				}
				break;
			case "read":
				switch (type)
				{
					case "playlists":
						//get list of playlists [name, list of songs]
						//	can search by member or random
						//	can sort by playlist name
						//	can also specify by id to return that playlist
						break;
					case "artists":
						//get list of artists [name, list of albums]
						//	can search by artist info
						//	can sort by artist name
						//	can also specify by id to return that artist
						break;
					case "albums":
						//get list of albums [name, artist info, list of songs]
						//	can search by album name
						//	can sort by album name, artist name 
						//	can also specify an id to return that album
						break;
					case "members":
						//get list of members [info, list of playlists]
						//	can search by member name
						//	can sort by member name
						//	can also specify an id to return that member
						break;
				}
				break;
			case "update":
				String action = req.getParameter("action");
				switch (type)
				{
					case "playlist":
						switch (action)
						{
							case "rename":
								//renames playlist
								break;
							case "addsongs":
								//adds list of song ids to playlist
								break;
							case "removesongs":
								//removes list of song ids from playlist
								break;
						}
						break;
					//placeholders
					/*case "artist":
						
						break;
					case "album":
						
						break;
					case "member":
						
						break;*/
				}
				break;
			case "delete":
				switch (type)
				{
					case "playlist":
						//deletes playlist with given id 
						break;
					//placeholders
					/*case "artist":
						
						break;
					case "album":
						
						break;
					case "member":
						
						break;*/
				}
				break;
		}
	}
}