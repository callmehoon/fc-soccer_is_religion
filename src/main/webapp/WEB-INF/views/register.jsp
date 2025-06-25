



    <link rel="stylesheet" href="publish/register.css"/>
    <script src="publish/register.js"></script>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <%@ include file="header.jsp" %>

<div class="main-body">
<div class="container">
    <form action="/register" method="post">
    <h2>회원가입</h2>
    <div class="step">
        <span>01</span> 정보입력 및 약관동의 &nbsp; &gt; &nbsp; <span style="background:#eee; color:#999;">02</span> 가입완료
    </div>

    <div class="section-title">기본정보</div>
        <p class="note">※필수입력사항</p>

        <label class="required">아이디(이메일)</label>
        <input type="email" name="email" id="email" placeholder="이메일 입력">
        <p class="error-msg" id="emailError"></p>
    <p class="note">※ 수신가능한 이메일 주소로 입력해주세요.</p>

        <label class="required">비밀번호</label>
        <input type="password" id="pw1">

        <label class="required">비밀번호 확인</label>
        <input type="password" id="pw2">
        <p class="error-msg" id="pwError"></p>

    <label class="required">이름</label>
    <input type="text">

    <label class="required">휴대전화</label>
    <input type="text" placeholder="- 없이 입력해주세요.">

        <label>주소</label>
        <div class="row">
            <input type="text" name="zipcode" id="receiver_address">
            <button type="button" onclick="execDaumPostcode()">우편번호 검색</button>
        </div>
        <input type="text" name="address" id="receiver_detail_address">


        <div class="section-title">부가 정보</div>

    <label class="required">성별</label>
    <div class="radio-group">
        <label><input type="radio" name="gender"> 남자</label>
        <label><input type="radio" name="gender"> 여자</label>
    </div>

    <label class="required">생일</label>
    <div class="row">
        <select name="birthYear" id="birthYear"><option>년</option></select>
        <select name="birthMonth" id="birthMonth"><option>월</option></select>
        <select name="birthDay" id="birthDay"><option>일</option></select>
    </div>

    <label class="required">휴면회원 방지기간</label>
    <div class="radio-group">
        <label><input type="radio" name="rest"> 1년</label>
        <label><input type="radio" name="rest"> 3년</label>
        <label><input type="radio" name="rest"> 5년</label>
        <label><input type="radio" name="rest"> 탈퇴시-평생회원</label>
    </div>

    <div class="section-title">약관동의</div>
    <div class="agreements">
        <label>
            <input type="checkbox" name="agreeAll"> 카포스토어의 모든 약관을 확인하고 전체 동의합니다.
            <span class="note">(선택항목도 포함)</span>
        </label>

        <div class="agreement-item">
            <label><input type="checkbox"> [필수] 이용약관</label>
            <button class="toggle-btn" onclick="toggleTerms('termsContent1')">전체보기 ▼</button>
            <div id="termsContent1" class="terms-content">
                <p><strong>제1조 (목적)</strong></p>
                <p>이 약관은 카포스토어가 제공하는 서비스 이용에 관한 조건 및 절차 등 필요한 사항을 규정합니다.</p>
                <p><strong>제2조 (정의)</strong></p>
                <p>... 이하 생략 ...</p>
            </div>
        </div>

        <label><input type="checkbox"> [필수] 개인정보 수집 및 이용</label>
        <label><input type="checkbox"> [필수] 개인정보 처리 위탁 동의</label>
        <label><input type="checkbox"> [선택] SMS 수신을 동의하시겠습니까?</label>
    </div>

    <div class="buttons">
        <button>취소</button>
        <button class="join">회원가입</button>
    </div>
    </form>
</div>
</div>




<%@ include file="footer.jsp" %>
