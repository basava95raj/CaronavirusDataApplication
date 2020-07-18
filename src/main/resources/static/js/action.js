$(document).ready(function () {
var table =$('#dtDynamicVerticalScroll').DataTable({
"scrollY": "43vh",
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
});