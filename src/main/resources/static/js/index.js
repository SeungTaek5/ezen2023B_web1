// 모든페이지에서 적용할 공통 JS

// 1. 로그인 여부 확인 요청
$.ajax({
    url : '/member/login/check',
    method : 'get',
    success : (r)=>{
        console.log(r);

        let login_menu = document.querySelector('#login_menu');
        let html = ``;

        if( r != '' ){
            html += `<li class="nav-item"> <a class="nav-link" onclick="logout()">로그아웃</a> </li>
                                     <li class="nav-item"> <a class="nav-link" href="#">내정보</a> </li>
                                     <li class="nav-item"> ${r} 님 </li>`;
        }
        else{
            html += `<li class="nav-item"> <a class="nav-link active" aria-current="page" href="/member/login">로그인</a> </li>
                                     <li class="nav-item"> <a class="nav-link" href="/member/signup">회원가입</a> </li>`;
        }

        login_menu.innerHTML = html;


    }
});

function logout(){

    $.ajax({
        url : 'member/logout',
        method : 'get',
        success : (r)=>{
            if(r){
                alert('로그아웃 성공');
                location.href = '/';
            }
            else{
                alert('로그아웃 실패');
            }
        }
    });

}