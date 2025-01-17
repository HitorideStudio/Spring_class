<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>마커 클러스터러 사용하기</title>
    
</head>
<body>


<div id="map" style="width:100%;height:350px;"></div>
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6e0cc7f923dfb2d33aa1685a125ad6cb&libraries=clusterer"></script>
<script>
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(37.55608, 126.9234), // 지도의 중심좌표 
        level : 6 // 지도의 확대 레벨 
    });
    
    // 마커 클러스터러를 생성합니다 
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 2 // 클러스터 할 최소 지도 레벨 
    });
 
    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
	var content = '<div class="customoverlay">' +
    '  <a href="https://map.kakao.com/link/map/11394059" target="_blank">' +
    '    <span class="title">구의야구공원</span>' +
    '  </a>' +
    '</div>';
  
    $.get("../data/hongdaefinal_0902.json", function(data) {
        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
      	test(data);
      	
      	//var t = Object.keys($(data.positions.title)).length;
      	//console.log(t);
		
		function test(data){
	    	//json타이틀 길이로 반복해서 마커 찍어준다
	    	
		    for(k = 0 ; k < Object.keys($(data.positions.title)).length ; k++){
		   
			    var markers = $(data.positions).map(function(i,position) {
			    	
		    	//console.log('=='+position.x[k]); //테스트확인용
		    	//console.log('==='+$(data.positions.title)[k]);
		        return new kakao.maps.Marker({
		        	position : new kakao.maps.LatLng(position.x[k], position.y[k]),
		        	title : $(data.positions.title)[k],
		        	
		        });	
		    });
		    // 클러스터러에 마커들을 추가합니다 
		    clusterer.addMarkers(markers);
		    }
	    }
    });
   
    
</script>

</body>
</html>




