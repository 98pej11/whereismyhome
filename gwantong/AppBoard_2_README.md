# AppBoard2
추가적으로 다룰 컴포넌트는 board-input-item, board-comment 이다.

<br>


# board-input-item

board-input-item은 props로 type을 받아 현재 input 컴포넌트가 글 작성인지, 글 수정인지를 구분해 버튼을 따로 배치한다.

* selectFile : 파일타입의 input의 내용이 바뀔때 마다, 즉 추가적으로 파일을 업로드 할 때 마다 해당 메소드를 실행한다. 메소드는 e.target.files로 input에 들어와있는 모든 파일에 접근하여 for of문을 통해 하나하나를 FormData형태로 저장한다.


* 저장한 FormData는 다시 this.formData로 해당 컴포넌트에 저장한다.


onSubmit은 작성내용이 비어있는지 검사 후 props의 타입에 맞는 메소드를 실행하는 메소드이다.


registArticle와 modifyArticle은 각각 write와 update시 필요한 정보들을 post body에 담고, api로 각각의 요청을 처리하는 메소드이다.

<br>


# board-comment

* board-comment는commentStore에 저장되어있는 comments를 가져와 출력한다. 

* 현재 출력중인 댓글 중, 로그인 되어있는 사용자와 정보가 일치하는 댓글만을 수정, 삭제할 수 있으므로 memberStore에서 유저정보도 불러온다.


그후 작성은  wComment, 삭제는 dComment, update관련 내용을 보여주기 위해 showUpdate, update는 uComment을 통해서 crud패턴을 수행하게 된다.

* memono는 현재 댓글의 pk이고, c는 유저가 수정버튼을 선택한 댓글이다. 

![](./readmeImage/%EB%8C%93%EA%B8%80%20%EC%9D%B4%EB%AF%B8%EC%A7%80.png)

이미지에서 보이는 memono == c.memono는 하위 class= "card-body"로 설정되어있는 댓글 수정 엘리먼트를 수정버튼을 누른 단 하나의 댓글에서만 보이도록 하기 위한 로직이다.

