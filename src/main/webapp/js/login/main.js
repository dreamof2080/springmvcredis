$(document).ready(function () {
    $('.log-btn').click(function () {
        var htmlobj = $.ajax({url: '/main/loginCheck',data:{userName:$('#UserName').val(),password:$('#Passwod').val()}, dataType: 'json', async: false});
        var json = JSON.parse(htmlobj.responseText);
        var msg = json.msg;
        if(msg=='ok'){
            window.location.href = '/main/index';
        }else if(msg=='error_name'){
            $('.alert').text('Invalid UserName');
            $('.log-status').eq(0).addClass('wrong-entry');
        }else{
            $('.alert').text('Invalid Password');
            $('.log-status').eq(1).addClass('wrong-entry');
        }
        $('.alert').fadeIn(500);
        setTimeout("$('.alert').fadeOut(1500);", 3000);
    });
    $('.form-control').keypress(function () {
        $('.log-status').removeClass('wrong-entry');
    });
});

