/*

<<현재까지 넣은 기능>>
(1) 메인 페이지 - 지도 생성 & 현재 위치 마커, 인포윈도우 표시 (카카오 api)
(2) 키워드로 검색 : 검색한 장소 마커 띄우기
(3) 마커 클릭 이벤트 : 이름 + '위시 추가'버튼 인포윈도우 표시

<<해야 할 기능>>
(4) 위시 추가 클릭 이벤트 : 좌표, 주소 얻어내기
(5) DB에 저장된 장소를 특정 마커 이미지로 표시하기
*/



// (1) 메인 페이지 - 지도 생성 & 현재 위치 마커, 인포윈도우 표시 (카카오 api)
var container = document.getElementById('map');
var options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 9
};

var map = new kakao.maps.Map(container, options); //지도 생성

// (a)지도 타입 컨트롤 / 지도에 컨트롤을 추가해야 지도위에 표시(topRight, 오른쪽 위)
var mapTypeControl = new kakao.maps.MapTypeControl();
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
// (b)줌 컨트롤 생성
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);


if (navigator.geolocation) { // HTML5의 geolocation으로 사용할 수 있는지 확인

    // GeoLocation을 이용해서 접속 위치 얻어오기
    navigator.geolocation.getCurrentPosition(function(position) {

        var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도

        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성
            message = '<div style="padding:5px;">현재 위치예요. 여행을 시작합시다~</div>';

        displayCurrentMarker(locPosition, message); // 마커와 인포윈도우를 표시
    });

} else { // HTML5의 GeoLocation을 사용할 수 없을때, 마커 표시 위치와 인포윈도우 내용을 설정

    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667), //default 위치
        message = 'geolocation을 사용할수 없어요..'

    displayCurrentMarker(locPosition, message);
}

function displayCurrentMarker(locPosition, message) {

    var current_marker = new kakao.maps.Marker({ // 마커 생성
        map: map,
        position: locPosition
    });

    var iwContent = message, // 인포윈도우 표시
        iwRemoveable = true;

    var infowindow = new kakao.maps.InfoWindow({ // 인포윈도우 생성
        content : iwContent,
        removable : iwRemoveable
    });

    infowindow.open(map, current_marker); //인포윈도우를 마커위에 표시

    map.setCenter(locPosition); // 지도 중심좌표를 접속위치로 변경
}
// org.thymeleaf.exceptions.TemplateInputException: An error happened during template parsing (template: "class path resource [templates/main.html]"


    // (2) 키워드로 장소 검색
    var search_info = new kakao.maps.InfoWindow({zIndex:1}); // 마커를 클릭하면 장소명을 표출할 인포윈도우
    var ps = new kakao.maps.services.Places(); // 장소 검색 객체

    // 키워드 검색을 요청 함수
    function searchPlaces() {

        var keyword = document.getElementById('navbar-search-input').value;
        ps.keywordSearch(keyword, placesSearchCB); // 장소검색 객체를 통해 키워드로 장소검색을 요청
    }

    // 키워드 검색 완료 시, 호출되는 콜백함수
    function placesSearchCB (data, status, pagination) {
        if (status === kakao.maps.services.Status.OK) {

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해 LatLngBounds 객체에 좌표를 추가
        var bounds = new kakao.maps.LatLngBounds();

        for (var i=0; i<data.length; i++) {
        displaySearchMarker(data[i]);
        bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
    }

        map.setBounds(bounds); // 검색된 장소 위치를 기준으로 지도 범위를 재설정
    }
    }


    //(3) 마커 클릭 이벤트 : 이름 + '위시 추가'버튼 인포윈도우 표시
    // 지도에 마커를 표시하는 함수
    function displaySearchMarker(place) {

        var selc_marker = new kakao.maps.Marker({ // 마커를 생성하고 지도에 표시
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x)
    });

        // 마커에 클릭이벤트 등록
        kakao.maps.event.addListener(selc_marker, 'click', function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출
        search_info.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name +

        '<br> <a id="selc_place_a" style="color: #ce8483">위시 추가</a> </div>');
        search_info.open(map, selc_marker);
    });

        // $("#selc_place_a").click(function(mouseEvent){
        //     var latlng = mouseEvent.latLng;
        //     var message = '클릭한 위치의 좌표 (경도,위도) : ' + latlng.getLat() + ', ' + latlng.getLng();
        //     alert(message);
        // })

    }

    //---------------------------------------------------------------------------------------여기까지 실행 완료


    // (4) 위시 추가 클릭 이벤트 : 좌표, 주소 얻어내기 -> DB 저장
    // https://apis.map.kakao.com/web/sample/addMapClickEventWithMarker/
    // 아래 코드 단일로는 실행 가능하지만, a 태그 '위시 추가' 버튼을 눌렀을 때, 해당 마크에 알맞는 위도, 경도 주소를 뽑아내야 함

    // 지도를 클릭한 위치에 표출할 마커입니다
    var practice_marker = new kakao.maps.Marker({
    // 지도 중심좌표에 마커를 생성합니다
    position: map.getCenter()
    });
    // 지도에 마커를 표시합니다
    practice_marker.setMap(map);


    //(4) 위시 장소 클릭 이벤트 : 좌표 얻어내기
    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();

    // 지도에 클릭 이벤트를 등록합니다
    // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
    kakao.maps.event.addListener(map, 'click', function(mouseEvent) {

        // 클릭한 위도, 경도 정보를 가져옵니다
        var latlng = mouseEvent.latLng;

        // 마커 위치를 클릭한 위치로 옮깁니다
        practice_marker.setPosition(latlng);

        var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
        message += '경도는 ' + latlng.getLng() + ' 입니다';
        alert(message);
        var resultDiv = document.getElementById('clickLatlng');
        var message = '클릭한 위치의 좌표 (경도,위도) : ' + latlng.getLat() + ', ' + latlng.getLng();
        var resultDiv = document.getElementById('result');
        resultDiv.innerHTML = message;
    });




// 주소-좌표 변환 객체를 생성합니다
// var geocoder = new kakao.maps.services.Geocoder();
//
// kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
//
//     // 클릭한 위도, 경도 정보를 가져옵니다
//     var latlng = mouseEvent.latLng;
//     var message = '클릭한 위치의 좌표 (경도,위도) : ' + latlng.getLat() + ', ' + latlng.getLng();
//     var resultDiv = document.getElementById('result');
//     resultDiv.innerHTML = message;
//
//     console.log(message);
// });

// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록
// kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
//     searchDetailAddrFromCoords(mouseEvent.latLng, function(result2, status) {
//
//         if (status === kakao.maps.services.Status.OK) {
//             var detailAddr = !!result2[0].road_address ? '<div>도로명주소 : ' + result2[0].road_address.address_name + '</div>' : '';
//             detailAddr += '<div>지번 주소 : ' + result2[0].address.address_name + '</div>';
//
//             var content = '<div class="bAddr">' +
//                 '<span class="title">법정동 주소정보</span>' +
//                 detailAddr +
//                 '</div>';
//
//             marker.setPosition(mouseEvent.latLng); // 마커를 클릭한 위치에 표시
//             marker.setMap(map);
//
//             infowindow.setContent(content); // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시
//             infowindow.open(map, marker);
//         }
//     });
// });
//
// function searchAddrFromCoords(coords, callback) {
//     // 좌표로 행정동 주소 정보를 요청합니다
//     geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
// }
//
// function searchDetailAddrFromCoords(coords, callback) {
//     // 좌표로 법정동 상세 주소 정보를 요청합니다
//     geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
// }

                function searchDetailAddrFromCoords(coords, callback) {
            // 좌표로 법정동 상세 주소 정보를 요청합니다
            geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
        }



// // (5) DB에 저장된 장소를 특정 마커 이미지로 표시하기
// var imageSrc = '/images/wish_icon.svg', // 마커이미지의 주소
//     imageSize = new kakao.maps.Size(60, 64), // 마커이미지의 크기
//     imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션(마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정)
//
// var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption), // 해당 이미지로 마커 생성
//     markerPosition = new kakao.maps.LatLng(37.54699, 127.09598); // 마커가 표시될 위치 <- DB에서 받아와야 함
//
// var wish_marker = new kakao.maps.Marker({ // 마커 생성
//     position: markerPosition,
//     image: markerImage // 마커이미지 설정
// });
//
// wish_marker.setMap(map); // 마커가 지도 위에 표시되도록 설정