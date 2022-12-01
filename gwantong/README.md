# board-housedeal-todolist

# 실행 전
* npm install

# 프로젝트 구조

![](./readmeImage/%EB%A9%94%EC%9D%B8%ED%99%94%EB%A9%B4.png)
![](./readmeImage/%ED%8F%B4%EB%8D%94%EA%B5%AC%EC%A1%B0.png)
색으로 표시한 박스가 메인페이지에서 보이는 뷰 파일들과, 매핑되어있는 헤더 네비게이션의 영역이다.

프로젝트는 위와같이 크게 4개의 파트로 나뉘며, AppMain, AppBoard, KakaoMap, AppUser로 정의되어있다.

각 파트에 대한 설명은 파트명으로 분리되어있는 readme에 상세히 작성되어있다.


# 개선점
현재 프로젝트엔 3가지 개선점이 있다.

첫번째는 테이블간의 의존관계를 모두 반영하지 못한 crud 패턴이다.

* 게시글, 댓글, 업로드 파일, 유저가 각각의 fk로 연결되어있는 구조. 그런데 몇몇기능에서 각 table간의 의존관계를 고려하지 않은 backend 기능수행이 이뤄지고 있음.

* ssafy라는 아이디의 유저가 게시글을 삭제하려고 할 때, 게시글의 articleno에 맞는 file_info의 모든 데이터를 삭제해야함.
* articleno에 맞는 모든 comment를 삭제해야함.

* 만약 ssafy라는 유저가 회원탈퇴를 한다면, 연관되어있는 게시글, 파일정보, 댓글등이 전부 삭제되어야함. 하지만 탈퇴전에 아이디를 ssafy1로 수정한다면, ssafy1에 해당하는 데이터들만 삭제됨. ssafy로 작성한 모든 데이터는 삭제되지 않음.

<br>
<br>


두번째는 게시글 수정시, 업로드한 파일정보를 볼 수 없다는 것 이다.

* ssafy_logo 이미지를 업로드 한 뒤, 게시글을 작성하고 후에 수정하려하면, 업로드한 이미지가 board-input-item 컴포넌트에서 보이지 않음.

<br>
<br>

세번째는 vuex와 api의 기능들이 각 파트별로 분할되어있지 않다는 점이다.

* board관련한 crud패턴은 각 컴포넌트의 생명주기에 맞춰서 컴포넌트가 비동기 통신을 요청하고 수행한다.

    * 하지만 board 하위기능인 comment는 따로 commentStore와 comment.js로 기능이 분할되어있다.
    
    <br>


* user관련 crud 패턴은 memberStore와 member.js를 통해 분할되어있다.

* map관련 vuex데이터는 index.js에서 모두 관리하고 있다. 이를 houseStore.js등으로 분할해야한다.





