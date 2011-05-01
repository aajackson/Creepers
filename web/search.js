// JavaScript Document
$("document").ready(function()
{
	$("#results").dataTable(
	{
		"aoColumnDefs": [{ "bSortable": false, "aTargets": [ 0 ] }]
	});
	
});