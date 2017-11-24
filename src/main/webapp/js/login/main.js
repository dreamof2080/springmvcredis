$(document).ready(function () {
    /** button点击事件 **/
    $('.log-btn').click(function () {
        getData();
    });
    $('.form-control').keypress(function (event) {
        $('.log-status').removeClass('wrong-entry');
        if(event.keyCode == "13"){
            getData();
        }
    });

});

function getData() {
    var userName = $('#UserName').val();
    var password = $('#Passwod').val();
    var msg = '';
    if(userName && password){
        var htmlobj = $.ajax({url: '/main/loginCheck',data:{userName:userName,password:password}, dataType: 'json', async: false});
        var json = JSON.parse(htmlobj.responseText);
        msg = json.msg;
    }else{
        if(!userName){
            msg = 'error_name';
        }else{
            msg = 'error_password';
        }
    }

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
}

