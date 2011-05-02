// JavaScript Document
$("document").ready(function()
{
	$("button#addSong").click(function(event)
	{
		event.preventDefault();
		var name = $("input#song_name").val();
		var album = $("input#album_name").val();
		var artist = $("input#artist_name").val();
		var track = $("input#track_number").val();
		
		var artist_id = -1;
		
		$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=artists&search="+album,function(data)
		{
			for(var i = 0; i < data.results.length; i++)
			{
				if(artist==data.results[i].name)
				{
					artist_id = data.results[i].artist_id;
					break;
				}
			}
			
			if(artist_id < 0 && data.results.length == 1)
			{
				artist_id = data.results[0].artist_id;
			}
			
			if( artist_id < 0)
			{
				$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=artist&name="+artist,function(data)
				{
					artist_id = data.id;
				});
			}
			
			var album_id = -1;
			
			$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=albums&search="+album,function(data)
			{
				for(var i = 0; i < data.results.length; i++)
				{
					if(album==data.results[i].name)
					{
						album_id = data.results[i].album_id;
						break;
					}
				}
				if(album_id < 0 && data.results.length == 1)
				{
					album_id = data.results[0].album_id;
				}
				if(album_id < 0)
				{
					$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=album&name="+album+"&artist_id="+artist_id,function(data)
					{
						album_id = data.id;
					});
				}
				
				$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=song&name="+name+"&album_id="+album_id+"&artist_id="+artist_id+"&track_number="+track,function(data)
				{
					alert("Added a song?");
					console.log(data);
					console.log(album_id);
					console.log(artist_id);
					console.log(track);
					event.preventDefault();
					loadPage("./home.html");
					return true;
				});	
				
				
			});

			
		});
		
		
		
		
		
	});
});