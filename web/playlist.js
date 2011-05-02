// JavaScript Document
$("document").ready(function()
{
	$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=playlists&id="+currentPlaylistID, function(data)
	{
		var playlist = data.results[0];
		
		$("span#playlist_title").append(playlist.name);
		$("span#playlist_creator").append("by: "+playlist.username);
		
		var songs = playlist.songs;
		if(songs.length < 1)
		{
			$("div#playlist_songs").append("No songs to display");
		}
		else
		{
			$("div#playlist_songs").append( //Create the table
						'<button title="Add selected songs to a playlist" type="submit" id="addbtn" value="add">Add Selected to Playlist</button>'+
						'<table id="playlist_songs_table">'+
						  '<thead>'+
							'<tr>'+
							  '<th><input id="select_all" type="checkbox" /></th>'+
							  '<th>Name</th>'+
							  '<th>Album</th>'+
							  '<th>Track #</th>'+
							  '<th>Artist</th>'+
							'</tr>'+
						  '</thead>'+
						  '<tbody>'+
						  '</tbody>'+
						'</table>'+
						'<button title="Add selected songs to a playlist" type="submit" id="addbtn" value="add">Add Selected to Playlist</button>'+
						'<br style="clear:both" />');
			
			for(var i = 0; i < songs.length; i++) //Add Each Playlist
			{
				$("div#playlist_songs tbody").append(
					'<tr>'+
					  '<td><input class="song" type="checkbox" value="'+songs[i].song_id+'"></td>'+
					  '<td>'+songs[i].name+'</td>'+
					  '<td>'+songs[i].album_name+'</td>'+
					  '<td>'+songs[i].track_number+'</td>'+
					  '<td>'+songs[i].artist_name+'</td>'+
					'</tr>');
			}
			
			$("#playlist_songs_table").dataTable(
			{
				"aoColumnDefs": [{ "bSortable": false, "aTargets": [ 0 ] }],
				"sPaginationType":"full_numbers"
			});
			$("input#select_all").click(function(event)
			{				
				var c = this.checked;
				$("input.song").each(function()
				{
					this.checked = c;
				});
			});
			
			$("button#addbtn").click(function(event)
			{
				if(login == true)
				{
					event.preventDefault();
					var ss = $("input.song:checked");
					var addSongs = [];
					
					for(var i = 0; i < ss.length; i++)
					{
						addSongs = addSongs.concat(ss[i].value);
					}
					
					selectSongs = addSongs;
					
					loadPage("./add.html");
				}
				else
				{
					alert("Please log in first!");
				}
			});
		}
	});
	
});