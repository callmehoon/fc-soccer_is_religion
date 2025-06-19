<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>축구는 종교다</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/publish/cart.css"/>
</head>
<body>
<div class="container">
    <h1 class="title">장바구니 ${cartListViewModel.cartList}</h1>
    <div class="cart-wrapper">
        <!-- 상품 목록 -->
        <div class="cart-items">
            <table class="cart-table">
                <thead>
                <tr>
                    <td><input type="checkbox"/></td>
                    <td>상품/옵션 정보</td>
                    <td>수량</td>
                    <td>상품금액</td>
                    <td>할인/적립</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="cart" items="${cartListViewModel.cartList}">
                    <tr>
                        <td><input type="checkbox" checked/></td>
                        <td>
                            <div class="product-info">
                                <img src="${cart.productImg}" alt="상품 이미지">
                                <div>
                                    <strong><c:out value="${cart.productName}"/></strong><br/>
                                    사이즈 :
                                    <c:choose>
                                        <c:when test="${cart.size == 0}">Free</c:when>
                                        <c:otherwise><c:out value="${cart.size}"/></c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </td>
                        <td>
                            <c:out value="${cart.productQuantity}"/>개<br/>
                            <a href="#" onclick="openModal(event)">옵션/수정변경</a>
                        </td>
                        <td>
                            <strong>
                                <fmt:formatNumber value="${cart.productPrice * cart.productQuantity}" type="currency"
                                                  currencySymbol="₩"/>
                            </strong>
                        </td>
                        <td>
                            할인 -
                            <fmt:formatNumber value="${cart.productPrice * cart.productQuantity * 0.01}" type="number"/>원<br/>
                            적립 +
                            <fmt:formatNumber value="${cart.productPrice * cart.productQuantity * 0.01}" type="number"/>원
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- 결제 정보 -->
        <div class="summary-box">
            <h3>총 <strong>6</strong>개의 상품</h3>
            <div class="summary-row">총 상품금액 <span>530,500원</span></div>
            <div class="summary-row">총 할인금액 <span>-5,280원</span></div>
            <div class="summary-row">총 배송비 <span>+3,000원</span></div>
            <hr/>
            <div class="total-price">결제예상금액 <strong>528,220원</strong></div>
            <button class="btn black">전체상품 구매하기</button>
            <button class="btn white">선택상품 구매하기</button>
        </div>
    </div>
</div>

<!-- 옵션 모달 -->
<div class="modal" id="optionModal">
    <div class="modal-content">
        <button class="close-btn" onclick="closeModal()">×</button>
        <h2 class="popup-title">옵션선택</h2>
        <strong>리가 베이스 레이어 숏 타이츠</strong> <span class="code">(65592403)</span><br/>

        <div class="product-info">
            <img src="https://capostoreimg.godohosting.com/store5/PMW/NPMW65592403.jpg" alt="상품 이미지">
            <div class="info-text"></div>
        </div>

        <hr/>

        <div class="option-select">
            <label>사이즈</label>
            <button class="size-btn">L</button>
            <button class="size-btn">XL</button>
            <button class="size-btn selected">2XL</button>
        </div>

        <div class="selected-option">
            <span>2XL</span>
            <div class="quantity">
                <button class="qty-btn">-</button>
                <input type="text" value="1"/>
                <button class="qty-btn">+</button>
            </div>
            <span class="price">27,300원</span>
        </div>

        <div class="popup-footer">
            <button class="btn cancel">취소</button>
            <button class="btn confirm">확인</button>
        </div>
    </div>
</div>
<script src="<%= request.getContextPath() %>/publish/cart.js" defer></script>
</body>
</html>
