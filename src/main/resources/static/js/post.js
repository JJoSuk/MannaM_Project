let postObject = {
    // init() 함수선언
    init: function () {
        let _this = this;

        $("#btn-save").on("click", () => {
            _this.insertPost();
        });
    },
    insertPost: function () {
        alert("포스트 등록 요청!!!");

        let post = {
            title: $("#title").val(),
            content: $("#content").val()
        }

        $.ajax({
           type:"post",
           url:"/post",
           data:JSON.stringify(post),
           contentType: "application/json; charset=utf-8"
        }).done(function(response){
            let msg = response["data"];
            alert(msg);
            // location = "/";
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
}

// userObject의 init함수 호출하기
postObject.init();