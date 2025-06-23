<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="Drop.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>축구는 종교다</title>
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
<main>
  <div class="filter-tags">
    <div class="filter-tag"><a href="/brand.html">브랜드</a></div>
    <div class="filter-tag"><a href="/silo.html">사일로</a></div>
    <div class="filter-tag"><a href="/ground.html">그라운드</a></div>
  </div>

  <section class="filter-sort-section">
    <aside class="filter-menu">
      <h4>필터</h4>
      <div class="filter-category">
        <details open><summary>브랜드</summary></details>
        <details><summary>가격</summary></details>
        <details><summary>용도</summary></details>
        <details><summary>연령</summary></details>
        <details><summary>색상</summary></details>
        <details><summary>사일로</summary></details>
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
    <div class="product-card">
      <img src="https://via.placeholder.com/300x300" alt="상품 이미지">
      <p>미즈노</p>
      <p>모렐리아 네오 IV β JAPAN(JPN/060)</p>
      <p>305,100원</p>
    </div>
    <div class="product-card">
      <img src="https://via.placeholder.com/300x300" alt="상품 이미지">
      <p>미즈노</p>
      <p>모렐리아 네오 IV JAPAN(JPN/060)</p>
      <p>273,100원</p>
    </div>
    <div class="product-card">
      <img src="https://via.placeholder.com/300x300" alt="상품 이미지">
      <p>미즈노</p>
      <p>모렐리아 네오 IV 프로 AS(560)</p>
      <p>144,000원</p>
    </div>
  </section>
</main>
</body>
</html>
