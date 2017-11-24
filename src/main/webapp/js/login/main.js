$(document).ready(function () {
    $('.log-btn').click(function () {
        var htmlobj = $.ajax({url: '/main/loginCheck',data:{userName:$('#UserName').val(),password:$('#Passwod').val()}, dataType: 'json', async: false});
        console.log(htmlobj.responseText);
        var json = JSON.parse(htmlobj.responseText);
        var msg = json.msg;
        if(msg=='ok'){
            console.log('ok');
        }else{
            console.log(msg);
        }
        $('.log-status').addClass('wrong-entry');
        $('.alert').fadeIn(500);
        setTimeout("$('.alert').fadeOut(1500);", 3000);
    });
    $('.form-control').keypress(function () {
        $('.log-status').removeClass('wrong-entry');
    });
});

