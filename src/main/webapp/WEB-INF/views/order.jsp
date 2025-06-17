<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>축구는 종교다</title>
    <style>
        label {
            display: block;
            font-weight: bold;
        }

        .input-field {
            display: block;
        }
    </style>
</head>
<body>
    <form>
        <div class="title">주문하기</div>
        <h3>상품 정보</h3>

        <h3>주문자 정보</h3>
        <label for="">주문하시는 분</label>
        <input class="input-field" type="text" name="order_member" required>
        <label for="">주소</label>
        <input class="input-field" type="text" name="address">
        <label for="">휴대폰 번호</label>
        <input class="input-field" type="text" name="phone_number" required>
        <label for="">이메일</label>
        <input class="input-field" type="text" name="email" required>

        <h3>배송 정보</h3>
        <label for="">배송지 확인</label>
        <select name="address_check">
            <option value="1">기본 배송지</option>
            <option value="2">최근 배송지</option>
            <option value="3">직접 입력</option>
            <option value="4">주문자 정보와 동일</option>
        </select>
        <label for="">받으실 분</label>
        <input class="input-field" type="text" name="receiver" required>
        <label for="">받으실 곳</label>
        <input class="input-field" type="text" name="receiver_address" required>
        <button>우편번호검색</button>
        <label for="">휴대폰번호</label>
        <input class="input-field" type="text" name="receiver_phone_number" required>
        <label for="">남기실 말씀</label>
        <input class="input-field" type="text" name="delivery_message">

        <h3>결제 정보</h3>
        <label for="">할인 및 적립</label>
<%--        <p>적용된 할인: ${}원</p>--%>
        <p>적용된 할인: 10,000원</p>
<%--        <p>발생 적립금: ${}원</p>--%>
        <p>발생 적립금: 10,000원</p>
        <label for="">쿠폰 사용</label>
        <button>쿠폰 조회 및 적용</button>
        <label for="">적립금 사용</label>
        <input class="input-field" type="text" name="use_bonuspoint">원
<%--        <label><input type="checkbox" name="use_all_point" value="is_all"/>전액 사용하기(보유 적립금: ${}원)</label>--%>
        <label><input type="checkbox" name="use_all_point" value="is_all"/>전액 사용하기(보유 적립금: 10,000원)</label>
<%--        <p>※ 1,000원부터 ${}원까지 사용 가능합니다.</p>--%>
        <p>※ 1,000원부터 10,000원까지 사용 가능합니다.</p>
        <label for="">총 결제 금액</label>
<%--        <p>${}원</p>--%>
        <p>10,000원</p>

        <h3>결제 수단 선택</h3>
        <label for="">결제수단</label>
        <select name="payment_method">
            <option value="1">간편결제</option>
            <option value="2">무통장 입금</option>
            <option value="3">신용카드</option>
            <option value="4">휴대폰 결제</option>
        </select>

<%--        <h4>총 결제 금액: ${}원</h4>--%>
        <h3>총 결제 금액: 10,000원</h3>
    </form>

    <form>
        <p>총 n개 상품</p>
        <p>총 상품 금액</p>
        <p>총 할인 금액</p>
        <p>총 배송액</p>
        <p>최종 결제 금액</p>
        <label><input type="checkbox" name="order_agree" value="is_agree"/>(필수) 구매하실 상품의 결제정보를 확인하였으며, 구매진행에 동의합니다.</label>
        <button type="submit">결제하기</button>
    </form>

</body>
</html>
