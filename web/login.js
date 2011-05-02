// JavaScript Document
$("document").ready(function()
{
	$("form").submit(function(event)
	{
             event.preventDefault();
			 return false;
    });
	$("button#loginbtn").click(function(event)
	{
		event.preventDefault();
		var user = $("input#username").val();
		var pass = $("input#password").val();
		
		var checkuser;
		$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=update&type=user&action=login&username="+user+"&password="+pass,function(data)
 		{
			if(data.success)
			{
				login=true;
				loginUser=user;
				$("li#first").html('<a class="login">'+user+'\'s profile</a> ');
				$("a.login").click(function(event)
				{
					loadPage("./user.html");
					event.preventDefault();
				});
				$("li#second").html('<a href="#" class="logout">logout</a>');
				$("a.logout").click(function(event)
				{
					$.getJSON("http://khadajmcs.dyndns-free.com/creepers/Servlet?method=update&type=user&action=logout",function(data)
					{
						login=false;
						loginUser="";
						loadPage("./home.html");
						$("li#first").html('<a href="#" class="login">login</a>');
						$("a.login").click(function(event)
						{
							loadPage("./login.html");
							event.preventDefault();
						});
						
						$("li#second").html('<a href="#" class="register">register</a>');
						$("a.register").click(function(event)
						{
							loadPage("./register.html");
							event.preventDefault();
						});
						
					});
				});
				
				loadPage("./user.html");
				return;
			}
			else
			{
				alert('Error: '+data.error);
				return;
			}
		});
	});
});