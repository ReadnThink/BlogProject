
let index = {
    init: function () {
        $("#btn-save").on("click", () => { //function(){} 대신 ()=>{}를 사용한 이유는 this를 바인딩하기 위해서 입니다.
            this.save();
        }); //제이쿼리 on의 첫번째 파라민터가 실행되면 두번째 파라미터를 실행해라
    },

    save: function (){
        // alert('user의 save함수 호출됨');
        let data = { //id값에 있는 필드들을 찾아 변수에 바인딩을 한다. id값은 div로 감싸져있는 곳? 아니면 페이지 전체?
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        // console.log(data);

        //ajax 호출시 default가 비동기 호출입니다.
        //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        //ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8", //보낼 데이터의 정보를 입력
            dataType: "json" //응답받을 데이터의 형태
        }).done(function (resp){ //url에서 반환된 값을 가지고 있는다.
            alert("회원가입이 완료되었습니다.");
            console.log(resp)
            // alert(resp)
            location.href="/main"
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

}
index.init();



// $("#btn-login").on("click", () => { //function(){} 대신 ()=>{}를 사용한 이유는 this를 바인딩하기 위해서 입니다.
//     this.login();
// });

// login: function (){
//     // alert('user의 save함수 호출됨');
//     let data = { //id값에 있는 필드들을 찾아 변수에 바인딩을 한다. id값은 div로 감싸져있는 곳? 아니면 페이지 전체?
//         username: $("#username").val(),
//         password: $("#password").val()
//     }
//
//     $.ajax({
//         type: "POST",
//         url: "/api/user/login",
//         data: JSON.stringify(data),
//         contentType: "application/json; charset=utf-8", //보낼 데이터의 정보를 입력
//         dataType: "json" //응답받을 데이터의 형태
//     }).done(function (resp){
//         alert("로그인이 완료되었습니다.");
//         location.href="/main"
//     }).fail(function (error){
//         alert(JSON.stringify(error));
//     });
// }
