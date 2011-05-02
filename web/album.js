// JavaScript Document
$("document").ready(function()
{
	$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=albums&id="+currentAlbumID, function(data)
	{
		var album = data.results[0];
		
		$("span#album_title").append(album.name);
		
		var songs = album.songs;
		if(songs.length < 1)
		{
			$("div#album_songs").append("No songs to display");
		}
		else
		{
			$("div#album_songs").append( //Create the table
						'<button title="Add selected songs to a playlist" type="submit" id="addbtn" value="add">Add Selected to Playlist</button>'+
						'<table id="album_songs_table">'+
						  '<thead>'+
							'<tr>'+
							  '<th><input id="select_all" type="checkbox" /></th>'+
							  '<th>Track #</th>'+
							  '<th>Song Name</th>'+
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
				$("div#album_songs tbody").append(
					'<tr>'+
					  '<td><input class="song" type="checkbox" value="'+songs[i].song_id+'"></td>'+
					  '<td>'+songs[i].track_number+'</td>'+
					  '<td>'+songs[i].name+'</td>'+
					  '<td>'+songs[i].artist_name+'</td>'+
					'</tr>');
				
				
			}
			
			$("#album_songs_table").dataTable(
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