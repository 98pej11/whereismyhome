# AppUser

* AppUser는 router의 redirect 설정에 의해 user-login으로 매핑된다. 

# user-login

user-login은 memberStore에 저장되어있는 userInfo을 이용해 api에 비동기로 유저정보를 확인한다. 

![alt](./readmeImage/%EB%A1%9C%EA%B7%B8%EC%9D%B8.png)

로그인 버튼을 누르면 실행되는 confirm 메소드이다.

async - await로 로그인폼으로 받아온 유저 정보를 확인하는 작업과, vuex에 userInfo를 로그인 유저의 정보로 바인딩 시키는 작업을 비동기로 수행한다.


* 회원가입은 class = checkbox의 checked 이벤트를 통해서 login 섹션과 join 섹션의 y-rotate를 수행, 화면에 출력하게 된다.

<br>

# memberStore

* memberStore는 회원관리에 관한 모든 vuex정보를 담고있는 js파일이다. 

userConfirm을 통해 전달받은 userinfo를 서버로 전송, 로그인을 시도하는 유저의 정보가 올바른지 확인한다. 확인 결과에 따라서 SET_IS_LOGIN,SET_IS_LOGIN_ERROR,SET_IS_VALID_TOKEN 의 3개의 mutation을 이용해 로그인 성공여부를 저장하게 된다.