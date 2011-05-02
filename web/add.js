// JavaScript Document
$("document").ready(function()
{	
	$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=members&search="+loginUser, function(data)
	{
		var member = data.results[0];
		
		var playlists = member.playlists;
		if(playlists.length > 0)
		{			
			for(var i = 0; i < playlists.length; i++) //Add Each Playlist
			{
				var id = playlists[i].playlist_id;
				$("tbody").append(
					'<tr>'+
					  '<td><input type="checkbox" class="playlist" id="'+id+'"></td>'+
					  '<td><a class="playlist_link" id="'+id+'">'+playlists[i].name+'</a></td>'+
					'</tr>');
			}
			$("a.playlist_link").click(function(event)
			{
				currentPlaylistID = $(this).attr('id');
				loadPage("./playlist.html");
				event.preventDefault();
			});
		}
			
		$("#add_table").dataTable(
		{
			"aoColumnDefs": [{ "bSortable": false, "aTargets": [ 0 ] }],
			"sPaginationType":"full_numbers"
		});
		
		$("button#addbtn").click(function()
		{
			var pl = $("input.playlist:checked");
			for(var i=0; i < pl.length; i++)
			{
				pl[i].id;
				$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=update&type=playlist&playlist_id="+pl[i].id+"&action=addsongs&songs=["+selectSongs+"]",function(data)																																													
{
});
			}
			
			if($("input#new").attr("checked"))
			{
				$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=playlist&name="+$("input#newname").attr("value")+"&songs=["+selectSongs+"]");
			}
			
			alert("Successfully added to playlist!");
			$(this).hide();
			loadPage("./user.html");
		});
		
	});
	
});