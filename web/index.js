// JavaScript Document
var currentPage = "./home.html";
var currentPlaylistID = 0;
var currentAlbumID = 0;
var currentArtistID = 0;
var currentMemberID = 0;
var selectSongs = [];
var login;
var loginUser="";
var loginID;

$(document).ready(function()
{
	$("a.login").click(function(event)
	{
		loadPage("./login.html");
		event.preventDefault();
	});
	$("a.register").click(function(event)
	{
		loadPage("./register.html");
		event.preventDefault();
	});
	$("a.about").click(function(event)
	{
		loadPage("./about.html");
		event.preventDefault();
	});
	$("a.help").click(function(event)
	{
		loadPage("./help.html");
		event.preventDefault();
	});
	$("button#searchbtn").click(function(event)
	{
		loadPage("./search.html");
		event.preventDefault();
	});
	$(".mainlogo").click(function(event)
	{
		loadPage("./home.html");
		event.preventDefault();
	});
	$("a#albums").click(function(event)
	{
		$("input.search_param").each(function(){this.checked = false});
		$("input#search_albums").attr("checked",true);
		loadPage("./search.html");
	});
	$("a#artists").click(function(event)
	{
		$("input.search_param").each(function(){this.checked = false});
		$("input#search_artists").attr("checked",true);
		loadPage("./search.html");
	});
	$("a#playlists").click(function(event)
	{
		$("input.search_param").each(function(){this.checked = false});
		$("input#search_playlists").attr("checked",true);
		loadPage("./search.html");
	});
	$("a#songs").click(function(event)
	{
		$("input.search_param").each(function(){this.checked = false});
		$("input#search_songs").attr("checked",true);
		loadPage("./search.html");
	});
	$("a#members").click(function(event)
	{
		$("input.search_param").each(function(){this.checked = false});
		$("input#search_members").attr("checked",true);
		loadPage("./search.html");
	});
	
	
								   
});


function loadPage(page)
{
	if(currentPage != page || page == "./search.html")
	{
		$(".content").hide("slow", function()
		{
        	$(".content").load(page, function()
			{
            	$(".content").show("slow");
				currentPage=page;
        	});
    	});
	}
}