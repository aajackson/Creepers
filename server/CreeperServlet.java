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
		boolean isLoggedIn = (boolean)session.getAttribute("logged-in");
		
		String method = req.getParameter("method");
		String type = req.getParameter("type");
		
		if (method.equalsIgnoreCase("create"))
		{
			if (type.equalsIgnoreCase("song"))
			{
				//create song, assigned to album
			}
			else if (type.equalsIgnoreCase("album"))
			{
				//create album, assigned to artist
			}
			else if (type.equalsIgnoreCase("artist"))
			{
				//create artist
			}
			else if (type.equalsIgnoreCase("playlist"))
			{
				//create playlist (from list of songs)
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
				if (action.equalsIgnoreCase("rename"))
				{
					//renames playlist
				}
				else if (action.equalsIgnoreCase("addsongs"))
				{
					//adds list of song ids to playlist
				}
				else if (action.equalsIgnoreCase("removesongs"))
				{
					//removes list of song ids from playlist
				}
			}
			else if (type.equalsIgnoreCase("user"))
			{
				if (action.equalsIgnoreCase("login"))
				{
					String username = req.getParameter("username");
					String password = req.getParameter("password");
					//login user
				}
				else if (action.equalsIgnoreCase("logout"))
				{
					//logout user
				}
			}
		}
		else if (method.equalsIgnoreCase("delete"))
		{
			if (type.equalsIgnoreCase("playlist"))
			{
				//deletes playlist with given id 
			}
		}
	}
}