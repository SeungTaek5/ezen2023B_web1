function index( result ){

    $.ajax({
        url : '/product/index',           // 어디에
        method : 'POST',                 // 어떻게
        data :  info  ,                   // 무엇을 보낼지. 입력받은값 보내기
        success : function ( result ){   // 무엇을 받을지. 통신후 응답받은 값
            console.log( result );
            // 4. 결과
            if( result ){
                alert('등록성공');
                const innerH = document.getElementById('insert');
                innerH.innerHTML = "<tr><td>${result.name}</td><td>123</td>"+
                "<td>123</td></tr>"
            }
            else{ alert('등록실패'); }
       } // success end
    }) // ajax end
}


function insert(){
    let name = document.querySelector('#name').value;   console.log( name );
    let price = document.querySelector('#price').value;   console.log( price );
    let amount = document.querySelector('#amount').value;   console.log( amount );

    let info = { name : name , price : price, amount : amount };               console.log( info );

    $.ajax({
           url : '/product/insert',           // 어디에
           method : 'POST',                 // 어떻게
           data :  info  ,                   // 무엇을 보낼지. 입력받은값 보내기
           success : function ( result ){   // 무엇을 받을지. 통신후 응답받은 값
                console.log( result );
                // 4. 결과
                if( result ){
                    alert('등록성공');
                    index();
                }
                else{ alert('등록실패'); }
           } // success end
        }) // ajax end
}
