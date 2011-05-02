// JavaScript Document
$("document").ready(function()
{
	$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=members&id="+currentMemberID, function(data)
	{
		var member = data.results[0];
		
		$("span#member_name").append(member.username);
		
		var playlists = member.playlists;
		if(playlists.length < 1)
		{
			$("div#member_playlists").append("No playlists to display");
		}
		else
		{
			$("div#member_playlists").append( //Create the table
						'<table id="member_playlists_table">'+
						  '<thead>'+
							'<tr>'+
							  '<th>Playlist Name</th>'+
							  '<th>Song Count</th>'+
							'</tr>'+
						  '</thead>'+
						  '<tbody>'+
						  '</tbody>'+
						'</table>'+
						'<br style="clear:both" />');
			
			for(var i = 0; i < playlists.length; i++) //Add Each Playlist
			{
				var id = playlists[i].playlist_id;
				$("div#member_playlists tbody").append(
					'<tr>'+
					  '<td><a class="playlist_link" id="'+id+'">'+playlists[i].name+'</a></td>'+
					  '<td>'+playlists[i].songs.length+'</td>'+
					'</tr>');
			}
			$("a.playlist_link").click(function(event)
			{
				currentPlaylistID = $(this).attr('id');
				loadPage("./playlist.html");
				event.preventDefault();
			});
			
			$("#member_playlists_table").dataTable(
			{
				"sPaginationType":"full_numbers"
			});
		}
	});
	
});