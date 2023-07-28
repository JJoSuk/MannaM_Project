let loginObject = {
    // init() 함수선언
    init: function () {
        let _this = this;

        $("#btn-login").on("click", () => {
            _this.login();
        });
    },
    login: function () {
        alert("로그인 요청!!!");

        let user = {
            username: $("#username").val(),
            password: $("#password").val()
        }

        $.ajax({
           type:"post",
           url:"/auth/login",
           data:JSON.stringify(user),
           contentType: "application/json; charset=utf-8"
        }).done(function(response){
            console.log(response);
            location = "/";
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
}

loginObject.init();