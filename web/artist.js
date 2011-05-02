// JavaScript Document
$("document").ready(function()
{
	$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=artists&id="+currentArtistID, function(data)
	{
		var artist = data.results[0];
		
		$("span#artist_name").append(artist.name);
		
		var albums = artist.albums;
		if(albums.length < 1)
		{
			$("div#artist_albums").append("No albums to display");
		}
		else
		{
			$("div#artist_albums").append( //Create the table
						'<table id="artist_albums_table">'+
						  '<thead>'+
							'<tr>'+
							  '<th>Album Name</th>'+
							'</tr>'+
						  '</thead>'+
						  '<tbody>'+
						  '</tbody>'+
						'</table>'+
						'<br style="clear:both" />');
			
			for(var i = 0; i < albums.length; i++) //Add Each Playlist
			{
				var id = albums[i].album_id;
				$("div#artist_albums tbody").append(
					'<tr>'+
					  '<td><a class="album_link" id="'+id+'">'+albums[i].name+'</a></td>'+
					'</tr>');
				
			}
			$("a.album_link").click(function(event)
			{
				currentAlbumID = $(this).attr('id');
				loadPage("./album.html");
				event.preventDefault();
			});
			
			$("#artist_albums_table").dataTable(
			{
				"sPaginationType":"full_numbers",
			});
		}
	});
	
});