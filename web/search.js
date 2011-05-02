// JavaScript Document
$("document").ready(function()
{
	var a = $("input.search_param:checked");
	var d = 0;
	if(a.length < 1)
	{
		$("div#albums_results").before('<h4>Error: Please select a search category</h4>');	
		return;
	}
	for(d = 0; d < a.length; d++) //For each search parameter checked
	{
		if(a[d].name == "playlists")
		{
			$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=playlists&search="+$("input#searchbox").val(), function(data)
			{
				$("div#playlists_results").append('<br /><h4>playlists</h4>');
				if(data.results.length < 1)
				{
					$("div#playlists_results").append('No playlists to display!<br />');
				}
				else
				{
					$("div#playlists_results").append( //Create the table
						'<table id="playlists_table">'+
						  '<thead>'+
							'<tr>'+
							  '<th>Playlist Name</th>'+
							  '<th>Creator</th>'+
							  '<th>Song Count</th>'+
							'</tr>'+
						  '</thead>'+
						  '<tbody>'+
						  '</tbody>'+
						'</table>'+
						'<br style="clear:both" />');
					var i = 0;
					for(i = 0; i < data.results.length; i++) //Add Each Playlist
					{
						var id = data.results[i].playlist_id;
						$("div#playlists_results tbody").append(
							'<tr>'+
							  '<td><a class="playlist_link" id="'+id+'">'+data.results[i].name+'</a></td>'+
							  '<td>'+data.results[i].username+'</td>'+
							  '<td>'+data.results[i].songs.length+'</td>'+
							'</tr>'
						);
					}
					$("a.playlist_link").click(function(event)
					{
						currentPlaylistID = $(this).attr('id');
						loadPage("./playlist.html");
						event.preventDefault();
					});
					$("#playlists_table").dataTable(
						{"sPaginationType": "full_numbers"}
					);
					
				}
			});
		}
		if(a[d].name == "artists")
		{
			$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=artists&search="+$("input#searchbox").val(), function(data)
			{
				$("div#artists_results").append('<br /><h4>artists</h4>');
				if(data.results.length < 1)
				{
					$("div#artists_results").append('No artists to display!<br />');
				}
				else
				{
					$("div#artists_results").append( //Create the table
						'<table id="artists_table">'+
						  '<thead>'+
							'<tr>'+
							  '<th>Artist</th>'+
							  '<th>Album Count</th>'+
							'</tr>'+
						  '</thead>'+
						  '<tbody>'+
						  '</tbody>'+
						'</table>'+
						'<br style="clear:both" />');
					
					for(var i = 0; i < data.results.length; i++) //Add Each Playlist
					{
						var id = data.results[i].artist_id;
						$("div#artists_results tbody").append(
							'<tr>'+
							  '<td><a class="artist_link" id="'+id+'">'+data.results[i].name+'</a></td>'+
							  '<td>'+data.results[i].albums.length+'</td>'+
							'</tr>'
						);
					}
					$("a.artist_link").click(function(event)
					{
						currentArtistID = $(this).attr('id');
						loadPage("./artist.html");
						event.preventDefault();
					});
					$("#artists_table").dataTable(
						{"sPaginationType": "full_numbers"}
					);
					
				}	
			});
		}
		if(a[d].name == "songs")
		{
			$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=songs&search="+$("input#searchbox").val(), function(data)
			{
				console.log(0);
				$("div#songs_results").append('<br /><h4>songs</h4>');
				if(data.results.length < 1)
				{
					console.log('a');
					$("div#songs_results").append('No songs to display!<br />');
				}
				else
				{
					$("div#songs_results").append( //Create the table
						'<button title="Add selected songs to a playlist" type="submit" id="addbtn" value="add">Add Selected to Playlist</button>'+
						'<table id="songs_table">'+
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
				
					for(var i = 0; i < data.results.length; i++) //Add Each Playlist
					{
						var id = data.results[i].song_id;
						$("div#songs_results tbody").append(
							'<tr>'+
							  '<td><input type="checkbox" class="song" value="'+id+'"></td>'+
							  '<td>'+data.results[i].name+'</td>'+
							  '<td>'+data.results[i].album_name+'</td>'+
							  '<td>'+data.results[i].track_number+'</td>'+
							  '<td>'+data.results[i].artist_name+'</td>'+
							'</tr>');
					}
					$("#songs_table").dataTable(
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
		}
		if(a[d].name == "albums")
		{
			$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=albums&search="+$("input#searchbox").val(), function(data)
			{
				$("div#albums_results").append('<br /><h4>albums</h4>');
				if(data.results.length < 1)
				{
					$("div#albums_results").append('No albums to display!<br />');
				}
				else
				{
					$("div#albums_results").append( //Create the table
						'<table id="albums_table">'+
						  '<thead>'+
							'<tr>'+
							  '<th>Album Name</th>'+
							  '<th>Artist</th>'+
							  '<th>Song Count</th>'+
							'</tr>'+
						  '</thead>'+
						  '<tbody>'+
						  '</tbody>'+
						'</table>'+
						'<br style="clear:both" />');
					
					
					for(var i = 0; i < data.results.length; i++) //Add Each album
					{
						var id = data.results[i].album_id;
						$("div#albums_results tbody").append(
							'<tr>'+
							  '<td><a class="album_link" id="'+id+'">'+data.results[i].name+'</a></td>'+
							  '<td>'+data.results[i].artist+'</td>'+
							  '<td>'+data.results[i].songs.length+'</td>'+
							'</tr>');
					}
					$("a.album_link").click(function(event)
					{
						currentAlbumID = $(this).attr('id');
						loadPage("./album.html");
						event.preventDefault();
					});
					
					$("#albums_table").dataTable(
						{"sPaginationType": "full_numbers"}
					);
					
				}
			});
		}
		if(a[d].name =="members")
		{
			$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=members&search="+$("input#searchbox").val(), function(data)
			{
				$("div#members_results").append('<br /><h4>members</h4>');
				if(data.results.length < 1)
				{
					$("div#members_results").append('No members to display!<br />');
				}
				else
				{
					$("div#members_results").append( //Create the table
						'<table id="members_table">'+
						  '<thead>'+
							'<tr>'+
							  '<th>Member Name</th>'+
							  '<th>Playlist Count</th>'+
							'</tr>'+
						  '</thead>'+
						  '<tbody>'+
						  '</tbody>'+
						'</table>'+
						'<br style="clear:both" />');
					
					for(var i = 0; i < data.results.length; i++) //Add Each members playlists results
					{
						var id = data.results[i].member_id;
						$("div#members_results tbody").append(
							'<tr>'+
							  '<td><a class="member_link" id="'+id+'">'+data.results[i].username+'</a></td>'+
							  '<td>'+data.results[i].playlists.length+'</td>'+
							'</tr>');
						
						
					}
					$("a.member_link").click(function(event)
					{
						currentMemberID = $(this).attr('id');
						loadPage("./member.html");
						event.preventDefault();
					});
					
					$("#members_table").dataTable(
						{"sPaginationType": "full_numbers"}
					);
					
				}
			});
		}
	}
});