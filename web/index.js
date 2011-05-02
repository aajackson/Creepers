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
			
			/*
			var url = 'http://khadajmcs.dyndns-free.com/creepers/Servlet', d;
var s = $.getJSON(url, 'method=create&type=member', function(data) {d = data;});

			
			*/
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