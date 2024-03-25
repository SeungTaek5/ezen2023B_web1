let lat = 0; //위도
let lng = 0; //경도



function onRegister(){

    // 1. 폼 가져오기
    let productRegisterForm = document.querySelector('.productRegisterForm');
    // 2. 폼 테이터 객체화
    let productRegisterFormData = new FormData(productRegisterForm);
    // 3. 폼 속성(위도, 경도) 추가
    console.log(productRegisterForm);
    console.log(lat, lng);
    if(lat==0 || lng==0){
        alert('제품 위치 선택 후 등록 가능합니다'); return;
    }

    productRegisterFormData.set('plat', lat);
    productRegisterFormData.set('plng', lng);


    $.ajax({
        url : "/product/register.do",
        method : "post",
        data : productRegisterFormData,
        processData : false,
        contentType : false,
        success : (r)=>{
            console.log(productRegisterFormData);
            console.log(r);
            location.href = "/product/list"
        }
    });
}





var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도를 클릭한 위치에 표출할 마커입니다
var marker = new kakao.maps.Marker({
    // 지도 중심좌표에 마커를 생성합니다
    position: map.getCenter()
});
// 지도에 마커를 표시합니다
marker.setMap(map);

// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {

    // 클릭한 위도, 경도 정보를 가져옵니다
    var latlng = mouseEvent.latLng;

    // 마커 위치를 클릭한 위치로 옮깁니다
    marker.setPosition(latlng);

    lat = latlng.getLat();
    console.log("lat : " + lat);
    lng = latlng.getLng();
    console.log("lng : " + lng)



});