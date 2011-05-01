// JavaScript Document
$("document").ready(function()
{
	$("button#registerbtn").click(function(event)
	{
		event.preventDefault();
		var user = $("input#username").val();
		var pass = $("input#password").val();
		var conf = $("input#confirm").val();
		
		var checkuser;
		$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=read&type=members&search="+user,function(data){																				 		if(user.length <=2)
		{
			alert('Username needs to be longer than 2 characters!');
			event.preventDefault();
			return false;
		}
		
		for(var i = 0; i < data.results.length; i++)
		{
			if(user.toLowerCase() == data.results[i].username.toLowerCase())
			{
				alert('Sorry, that username is already taken!');
				event.preventDefault();
				return false;
			}
		}
		if(pass.length <= 2)
		{
			alert('Password needs to be longer than 2 characters!');
			event.preventDefault();
			return false;
		}
		if(pass != conf)
		{
			alert("Password doesn't match the confirmation");
			event.preventDefault();
			return false;
		}
		
		$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=create&type=member&username="+user+"&password="+pass,function(data)
		{
			alert("Successfully registered!");
			event.preventDefault();
			return true;
		});																														 });
		
		
	});
});