# KakaoMap
KakaoMap은 카카오맵 api를 이용하여 화면에 지도를 띄워주는 id = map인 div안에 house-search-bar라는 검색 필터 컴포넌트와, house-detail을 배치시킨 뷰 화면이다.

<br>

# house-search-bar

house-search-bar는 시도,구군 정보를 불러와 각 input의 select 옵션으로 등록시켜주는 getSido,gugunList가 있고, 검색버튼 클릭시 searchApt메소드를 통해 vuex의 getHouseList2으로 검색조건에 맞는 아파트 정보를 비동기 통신으로 가져온다.

* 검색 조건을 설정하는 섹션 밑에 house-list 컴포넌트가 있다. 

<br>

# house-list
house-list는 v-for을 활용하여 house-list-item 컴포넌트에 vuex의 houses의 요소 하나하나를 house props로 넘겨줘 검색 아파트 리스트를 보여주는 컴포넌트이다.

* house-list-item : props로 house를 가져와 해당 아파트 매매정보를 출력한다. hover 이벤트로 colorChange를 사용해 엘리먼트의 색상을 변경한다. 
* click이벤트로 selectHouse을 활용해 선택한 아파트 매매정보를 vuex의 detailHouse을 호출, vuex의 house에 저장한다. 추후 vuex의 house는 house-detail의 v-if로 인한 출력조건과, 출력내용에 영향을 끼친다.


<br>

# house-detail
house-detail은 vuex의 house 객체를 가져와 출력해주는 컴포넌트이다. 내부에는 house-chart 컴포넌트를 가지고 있다.

<br>

# house-chart

house-chart는 vue-chartjs를 활용해 데이터에 맞는 그래프를 출력하는 컴포넌트이다.

![alt](./readmeImage/%EC%B0%A8%ED%8A%B81.png)
* bar는 vue-chartjs 컴포넌트이고, chart-data를 props로 받아 알맞는 그래프를 그려준다. props로 넘기는 chartDataSet은 vuex에 저장되어있기 때문에, computed에서 불러온다.

이 코드 밑의 30번째줄 까지는 vue-chartjs의 기본세팅이다.

<br>

![alt](./readmeImage/%EC%B0%A8%ED%8A%B82.png)

다음은 컴포넌트의 생명주기이다. house-chart는 house-detail에서만 등록되어있다. house-detail은 vuex의 house가 있을때만 하위 요소들을 렌더링 하게 되어있다. 즉, house-chart의 created는 vuex의 house에 새로운 값이 들어갈 때만 실행되게 된다.

* 또 house-chart는 vuex에 등록되어있는 houses를 불러와 chartDataSet의 값들을 변동시켜야 한다. houses의 recentPrice들을 범주에 맞는 index로 변경시켜 차트에 뿌릴 데이터를 만든다. 

* this.setData를 통해 그런 데이터를 생성, SET_LABEL,SET_DATA 을 사용해 vuex에 저장한다.

* 변경된 chartDataSet의 data값에 따라서 해당 bar의 투명도를 변경시키려 했다. 따라서 setBackgroundColor을 활용하여 chartDataSet의 data를 가져와 거기에 알맞는 rgba값을 생성한다.
*  생성한 rgba값을 다시 SET_COLOR을 활용해 vuex의 chartDataSet에 저장한다.

<br>

생성시킨 데이터를 직접 mapState로 가져온 chartDatSet으로 변경시키지 않는 이유는, 직접 변경을 시도하면 vuex의 life-cycle을 따라가지 않기 때문에, 생각했던것과 결과가 달라지게 된다. 아직 vuex의 chartDataSet이 원하는대로 변경되지 않았는데, 컴포넌트를 등록시켜 차트에 아무 데이터도 출력되지 않는다거나 하는 식이다.

<br>

따라서 추후에도 vuex를 활용해 컴포넌트를 만든다면, 반드시 vuex의 state값들은 내부적으로 mutation을 활용해 업데이트 해야한다.