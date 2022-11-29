# AppMain
AppMain은 가로로 꽉차있는 이미지와 더불어 하단에 각 기능을 설명하는 b-card 요소로 구성되어있다.

맨 왼쪽부터 차례대로 설명하자면,

<br>

# 첫번째 b-card

첫번째 b-card는 게시판 내용을 미리보여준다. b-card 내부엔 b-table로 최대 4개의 게시글을 미리 보여주고, 해당하는 게시글의 행을 클릭하면, goBoardView함수를 실행하여, router를 통해 현재 AppMain으로 매핑되어있는 routerView를 AppBoard로 전환시킨다.


* AppBoard는 isShow를 통해서 default_view인 b-table 로 보여지는 게시판 리스트와, 좌측엔 board-list-item으로 보여지는 게시판 리스트, 우측에는 board-detail을 보여주는 분할게시판을 선택하여 보여준다.

* AppMain에서 row-clicked 이벤트를 통해 AppBoard로 전환시킨다면, isShow를 True로 만들어야 하기 때문에 SET_GLOBAL_ISSHOW를 이용하여 vuex의 global_ishow를 true로 만든다. 

* 역시 마찬가지로 global_article또한 SET_GLOBAL_ARTICLE을 사용하여 바인딩 시켜준다.


<br>

# 두번째 b-card

두번째 b-card는 db에 sample 테이블에 저장되어있는 대표 아파트 매매정보 3개를 가져와 보여주는 섹션이다. v-for를 통해 house-list-item을 출력하도록 만들었고, house-detail 모달창을 숨겨놔 house-list-item 클릭시 vuex의 house가 변하도록 설정되어있는 로직을 그대로 사용했다. house-detail은 vuex의 house를 받아와 정보를 출력하므로, 로직의 변화없이 그대로 사용가능했다.

<br>

# 세번째 b-card

세번째 b-card는 별다른 기능이 없다.
