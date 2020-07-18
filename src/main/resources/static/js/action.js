$(document).ready(function () {
var table =$('#dtDynamicVerticalScroll').DataTable({
"scrollY": "39vh",
"scrollCollapse": true,
"lengthChange": false,
"bInfo" : false,
"bPaginate": false,
"responsive": true,
"dom": '<"top"i>rt<"bottom"><"clear">'
});
$('.dataTables_length').addClass('bs-select');
$('#myInputTextField').on( 'keyup click', function () {
    table.search($('#myInputTextField').val()).draw();
  } );

$(".card" ).hover(
		  function() {
		    $(this).addClass('shadow-lg').css('cursor', 'pointer'); 
		  }, function() {
		    $(this).removeClass('shadow-lg');
		  }
		);
});

$('.count').each(function () {
    $(this).prop('Counter',0).animate({
        Counter: $(this).text()
    }, {
        duration: 1500,
        easing: 'swing',
        step: function (now) {
            $(this).text(Math.ceil(now));
        }
    });
});