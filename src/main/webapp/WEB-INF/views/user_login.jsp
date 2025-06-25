
<div class="container">
    <h2>로그인</h2>
    <form action="/login" method="post" id="loginForm">
        <input type="email" name="email" id="email" placeholder="아이디">
        <input type="password" name="password" id="password" placeholder="비밀번호">
        <label><input type="checkbox" name="remember"> 아이디 저장</label>
        <button type="submit">로그인</button>
    </form>

    <button type="button" onclick="location.href='/register'">회원가입</button>

    <div class="sns">
        <p>SNS 로그인</p>
        <img src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" alt="kakao">
        <img src="https://ssl.pstatic.net/static/nid/login/naver_ico.png" alt="naver">
        <img src="https://upload.wikimedia.org/wikipedia/commons/5/51/Facebook_f_logo_%282019%29.svg" alt="facebook">
    </div>
</div>
