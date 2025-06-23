<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="Drop.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>의류 모아보기</title>
  <link rel="stylesheet" href="/publish/new.css">
  <style>
    .filter-sort-section {
      display: flex;
      padding: 40px 60px;
      gap: 40px;
    }

    .filter-menu {
      width: 200px;
      flex-shrink: 0;
    }

    .filter-menu h4 {
      font-size: 16px;
      margin-bottom: 10px;
      border-bottom: 1px solid #ccc;
      padding-bottom: 5px;
    }

    .filter-category {
      margin-bottom: 20px;
    }

    .filter-category summary {
      font-weight: bold;
      cursor: pointer;
    }

    .sort-bar {
      margin-left: auto;
    }

    .sort-bar select {
      padding: 6px 12px;
      font-size: 14px;
    }

    .filter-tags {
      display: flex;
      gap: 10px;
      margin: 20px 60px 10px;
      justify-content: center;
    }

    .filter-tag a {
      display: block;
      background-color: #f3f3f3;
      border: 1px solid #ccc;
      border-radius: 4px;
      padding: 6px 12px;
      font-size: 14px;
      text-decoration: none;
      color: black;
    }

    .product-grid {
      display: grid;
      grid-template-columns: repeat(3, 1fr);
      gap: 40px;
      padding: 40px 60px;
    }

    .product-card {
      text-align: center;
    }

    .product-card img {
      width: 100%;
      max-width: 300px;
    }
  </style>
</head>
<body>
<main>
  <div class="filter-tags">
    <div class="filter-tag"><a href="#">상의</a></div>
    <div class="filter-tag"><a href="#">하의</a></div>
  </div>

  <section class="filter-sort-section">
    <aside class="filter-menu">
      <h4>필터</h4>
      <div class="filter-category">
        <details open><summary>브랜드</summary></details>
        <details><summary>가격</summary></details>
        <details><summary>연령</summary></details>
        <details><summary>색상</summary></details>
        <button style="margin-top:10px">필터 초기화</button>
        <button style="margin-top:10px">검색</button>
      </div>
    </aside>

    <div class="sort-bar">
      <label for="sort">추천순</label>
      <select id="sort">
        <option>추천순</option>
        <option>가격 높은순</option>
        <option>가격 낮은순</option>
        <option>판매인기순</option>
        <option>등록일순</option>
        <option>상품평순</option>
      </select>
    </div>
  </section>

  <section class="product-grid">
    <c:forEach var="p" items="${productList}">
      <div class="product-card">
        <img src="${p.img}" alt="${p.productName}">
        <p><c:out value="${p.brandName}" /></p>
        <p><c:out value="${p.productName}" /></p>
        <p>${p.price}원</p>
<%--        <p>--%>
<%--          <fmt:formatNumber value="${p.price}" type="currency" />--%>
        </p>
      </div>
    </c:forEach>

    <c:if test="${empty productList}">
      <p style="grid-column:1/-1; text-align:center;">의류 상품이 없습니다.</p>
    </c:if>
  </section>
</main>
</body>
</html>