const sizeButtons = document.querySelectorAll('.size-btn');

sizeButtons.forEach(btn => {
    btn.addEventListener('click', () => {
        sizeButtons.forEach(b => b.classList.remove('active')); // 모든 버튼 비활성화
        btn.classList.add('active'); // 클릭된 버튼만 활성화
    });
});


document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".scroll_move").forEach(function (el) {
        el.addEventListener("click", function (e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute("href"));
            if (target) {
                window.scrollTo({
                    top: target.offsetTop,
                    behavior: "smooth"
                });
            }
        });
    });
});



const cartBtn = document.querySelector('.btn.cart');

cartBtn.addEventListener('click', () => {
    alert('사이즈가 선택되지 않았습니다');
});

const buyBtn = document.querySelector('.btn.buy');

buyBtn.addEventListener('click', () => {
    alert('사이즈가 선택되지 않았습니다');
});


// ✅ 공통 DOM
// const cartBtn = document.querySelector('.btn.cart');
// const buyBtn = document.querySelector('.btn.buy');
// const cartModal = document.getElementById('cartModal');
// const goCartBtn = document.getElementById('goCart');
// const closeModalBtn = document.getElementById('closeModal');

// ✅ 선택된 상품이 있는지 확인하는 함수
// function hasSelectedProduct() {
//     return document.querySelector('.size-btn.active') !== null;
// }

// ✅ 장바구니 버튼 클릭 시
// cartBtn.addEventListener('click', () => {
//     if (!hasSelectedProduct()) {
//         alert('사이즈가 선택되지 않았습니다.');
//         return;
//     }
//
//     cartModal.style.display = 'flex'; // 모달 열기
// });
//
// // ✅ 구매하기 버튼 클릭 시
// buyBtn.addEventListener('click', () => {
//     if (!hasSelectedProduct()) {
//         alert('사이즈가 선택되지 않았습니다.');
//         return;
//     }
//
//     // 구매 페이지로 이동
//     location.href = '/order'; // 필요에 따라 URL 수정
// });
//
// // ✅ 모달 닫기
// closeModalBtn.addEventListener('click', () => {
//     cartModal.style.display = 'none';
// });
//
// // ✅ 장바구니로 이동
// goCartBtn.addEventListener('click', () => {
//     location.href = '/cart'; // 필요에 따라 URL 수정
// });