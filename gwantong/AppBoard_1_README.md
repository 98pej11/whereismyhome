# AppBoard
AppBoard는 router에서 redirect설정이 board-list로 되어있어, 자동으로 boadr-list를 불러와 AppBoard에 있는 router-view에 board-list를 매핑시킨다.


<br>

# board-list

board-list는 크게 세게의 섹션으로 나뉘어져있다.


* id = default-list, id = left-list, id = right-detail이다.

* 이 세개의 섹션은 isShow값에 따라 화면에 default-list만 보이거나, left-list와 right-detail이 동시에 보이게 된다.


* isShow의 값은 기본으로 false이나, board-list가 AppMain의 b-table row-clicked 이벤트로 불려왔다면, global_isShow값의 영향을받아 초기값을 true로 받게된다. 초기값을 true로 설정하고 난 뒤엔 CLEAN_GLOBAL_ISSHOW를 이용하여 global-isShow의 값을 다시 false로 초기화시킨다.

* 우측 상단엔 register 버튼이 있는데, 누르게 되면 board-write로 뷰를 이동시킨다.


* # board-write & board-modify

두개는 모두 board-input-item이라는 컴포넌트를 사용한다는점은 같지만, board-input-item이 props로 받는 type값을 다르게 준다는 점으로 작성인지, 수정인지를 구분하게 만들었다.


<br>

# default-list

* default-list는 b-table을 활용하여 articles의 요소 하나하나를 출력한다. 각 요소는 row-clicked의 viewArticled 이벤트 호출을 통해 api 서버에 articleno값을 보내고, 그에 맞는 article을 저장하게된다. 
* 저장을 성공했다면, 해당하는 article의 comment또한 getComments을 이용해 받아온다. 받아온 comments는 commentStore의 comments에 저장해 추후에 board-comment 컴포넌트에서 활용하게 된다.


<br>

# left-list

* left-list는 b-table이 아닌 board-list-item의 v-for활용으로 게시판 글 리스트를 보여준다. 각 board-list-item은 click 이벤트의 viewArticled를 역시 활용해 board-view에 사용할 article props 정보를 바인딩한다.


<br>

# right-detail

* right-detail은 board-view 컴포넌트를 보여주는 섹션으로, props로 article을 전달한다. 만약 viewArticled를 통해 건내받은 article의 fileInfo 정보가 있다면, 해당하는 fileInfo의 savefile 내역을 받아 props로 전달한다.

* 만약 fileInfo 정보가 없다면, assets에 기본으로 저장되어있는 ssafy_logo.png를 건내주게 된다.



# ps
언급되지 않은 board 컴포넌트는 AppBoard_2에서 다룰것이다.
