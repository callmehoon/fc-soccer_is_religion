<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/Drop.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>축구는 종교다</title>
  <link rel="stylesheet" href="<c:url value='/publish/new.css'/>">
</head>
<body>
<main>
  <h2 class="section-title">${pageTitle}</h2>

  <div class="product-grid">
    <c:forEach var="p" items="${productList}">
      <div class="product-card">
        <img src="${p.img}" alt="${p.productName}" />
        <div class="product-info">
          <h3 class="brand">${p.brandId}</h3>
          <p class="product-name">${p.productName}</p>
          <p class="price">${p.price}원</p>
          <c:if test="${not empty p.size}">
            <p class="sizes">${p.size}</p>
          </c:if>
        </div>
      </div>
    </c:forEach>
  </div>

  <!-- 페이징 -->
  <div class="pagination">
    <c:if test="${page > 1}">
      <a href="?page=${page-1}&amp;size=${size}">이전</a>
    </c:if>
    <c:forEach var="i" begin="1" end="${totalPages}">
      <c:choose>
        <c:when test="${i == page}">
          <span class="current">${i}</span>
        </c:when>
        <c:otherwise>
          <a href="?page=${i}&amp;size=${size}">${i}</a>
        </c:otherwise>
      </c:choose>
    </c:forEach>
    <c:if test="${page < totalPages}">
      <a href="?page=${page+1}&amp;size=${size}">다음</a>
    </c:if>
  </div>

</main>
</body>
</html>
