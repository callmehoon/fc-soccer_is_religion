<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value='/publish/rolling.css'/>">


<style>
    .rolling-banner {
        width: 100%;
        overflow: hidden;
        position: relative;
        margin: 30px auto;
    }

    .rolling-wrapper {
        display: flex;
        animation: slide 30s infinite linear;
        width: max-content;
    }

    .rolling-wrapper img {
        width: 2500px;
        height: 900px;
        object-fit: cover;
        margin-right: 10px;
        border-radius: 8px;
    }

    @keyframes slide {
        0% { transform: translateX(0); }
        100% { transform: translateX(-50%); }
    }
</style>

<div class="rolling-banner">
    <div class="rolling-wrapper">
        <!-- 이미지 4개 x 2세트로 반복 -->
        <img src="/image/image1.png" alt="item1" />
        <img src="/image/image2.png" alt="item1" />
        <img src="/image/image3.png" alt="item1" />
        <img src="/image/image4.png" alt="item1" />
        <img src="/image/image5.png" alt="item1" />
        <img src="/image/image6.png" alt="item1" />

        <img src="/image/image1.png" alt="item1" />
        <img src="/image/image2.png" alt="item1" />
        <img src="/image/image3.png" alt="item1" />
        <img src="/image/image4.png" alt="item1" />
        <img src="/image/image5.png" alt="item1" />
        <img src="/image/image6.png" alt="item1" />
    </div>
</div>