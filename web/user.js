// JavaScript Document
$("document").ready(function()
{
	$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=members&search="+loginUser,function(data)
	{
		var playlists = data.results[0].playlists;
		for(var i = 0; i < playlists.length; i++)
		{
			$("ul#user_playlists").append('<li><a class="playlist" value="'+playlists[i].playlist_id+'">'+playlists[i].name+' ('+playlists[i].songs.length+')</a></li>');
		}
		
		$("a.playlist").click(function(event)
		{
			var playlistID = $(this).attr('id');

			$("div.playlist_table").html("");
			$("div.playlist_table").append(
				'<table id="selected_playlist">'+
				'<thead>'+
				  '<tr>'+
					'<th><input id="select_all" type="checkbox" /></th>'+
					'<th>#</th>'+
					'<th>Song Title</th>'+
					'<th>Album</th>'+
					'<th>Artist</th>'+
				  '</tr>'+
				  '</thead>'+
				  '<tbody>'+
				  '</tbody>'+
				'</table>'
			);
			
			$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=playlists&id="+playlistID,function(data)
			{
				var songs = data.results[0].songs;
				for(var i = 0; i < songs.length; i++)
				{
					$("table#selected_playlist tbody").append(
					'<tr>'+
					  '<td><input class="song" type="checkbox" value="'+songs[i].song_id+'"></td>'+
					  '<td>'+songs[i].track_number+'</td>'+
					  '<td>'+songs[i].name+'</td>'+
					  '<td>'+songs[i].album_name+'</td>'+
					  '<td>'+songs[i].artist_name+'</td>'+
					'</tr>');
				}
				
				$("table#selected_playlist").dataTable(
				{
					"aoColumnDefs": [{ "bSortable": false, "aTargets": [ 0 ] }],
					"sPaginationType":"full_numbers"
				});
				
			});
			
			$("input#select_all").click(function(event)
			{				
				var c = this.checked;
				$("input.song").each(function()
				{
					this.checked = c;
				});
				
				event.preventDefault();
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
			event.preventDefault();
		});
	});
});