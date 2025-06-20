document.addEventListener("DOMContentLoaded", function () {
    const sizeButtons = document.querySelectorAll('.size-btn');
    const selectedProductContainer = document.querySelector('.selected-product');
    const productPriceInput = document.getElementById('productPrice');
    const basePrice = parseInt(productPriceInput.value, 10);  // 원가격
    const cartBtn = document.querySelector('.btn.cart');
    const buyBtn = document.querySelector('.btn.buy');
    const totalPriceEl = document.querySelector('.total-price');

    // 사이즈 버튼 클릭 시 상품 추가
    sizeButtons.forEach(btn => {
        btn.addEventListener('click', function () {
            const selectedSize = this.innerText.trim();
            const stock = parseInt(this.dataset.stock, 10);

            if (selectedProductContainer.querySelector(`[data-size="${selectedSize}"]`)) return;

            sizeButtons.forEach(b => b.classList.remove('active'));
            this.classList.add('active');

            const item = document.createElement('div');
            item.className = 'selected-info';
            item.setAttribute('data-size', selectedSize);
            item.setAttribute('data-stock', stock);

            item.innerHTML = `
                <span>${selectedSize} (0원)</span>
                <div class="quantity">
                    <button class="minus">-</button>
                    <span class="count">1</span>
                    <button class="plus">+</button>
                </div>
                <span class="price">${basePrice.toLocaleString()}원</span>
                <button class="remove">×</button>
            `;
            selectedProductContainer.appendChild(item);
            updateTotal();
        });
    });

    // 수량 변경 및 삭제 처리
    selectedProductContainer.addEventListener('click', function (e) {
        const parent = e.target.closest('.selected-info');
        if (!parent) return;

        const countEl = parent.querySelector('.count');
        let count = parseInt(countEl.innerText, 10);
        const stock = parseInt(parent.dataset.stock, 10);
        const priceEl = parent.querySelector('.price');

        if (e.target.classList.contains('plus')) {
            if (count < stock) {
                count++;
                countEl.innerText = count;
                priceEl.innerText = (basePrice * count).toLocaleString() + '원';
                updateTotal();
            } else {
                alert(`최대 수량은 ${stock}개입니다.`);
            }
        }

        if (e.target.classList.contains('minus')) {
            if (count > 1) {
                count--;
                countEl.innerText = count;
                priceEl.innerText = (basePrice * count).toLocaleString() + '원';
                updateTotal();
            }
        }

        if (e.target.classList.contains('remove')) {
            parent.remove();
            updateTotal();
        }
    });

    // 총 상품 금액 계산
    function updateTotal() {
        const items = document.querySelectorAll('.selected-info');
        let total = 0;
        items.forEach(item => {
            const count = parseInt(item.querySelector('.count').innerText, 10);
            total += basePrice * count;
        });
        totalPriceEl.innerText = total.toLocaleString() + '원';
    }

    // 장바구니/구매 버튼 클릭 시 검증
    function hasSelectedItems() {
        return document.querySelectorAll('.selected-info').length > 0;
    }

    cartBtn.addEventListener('click', () => {
        if (!hasSelectedItems()) {
            alert('상품이 선택되지 않았습니다.');
            return;
        }
        alert('장바구니에 추가되었습니다.');
        // 여기서 스프링으로 전송하려면 fetch() 또는 form으로 처리
    });

    buyBtn.addEventListener('click', () => {
        if (!hasSelectedItems()) {
            alert('상품이 선택되지 않았습니다.');
            return;
        }
        alert('구매 페이지로 이동합니다.');
        // location.href = "/order"; // 필요시 경로 수정
    });

    // 탭 이동 (상세정보/리뷰/문의)
    document.querySelectorAll(".scroll_move").forEach(function (el) {
        el.addEventListener("click", function (e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute("href"));
            if (target) {
                window.scrollTo({ top: target.offsetTop, behavior: "smooth" });
            }
        });
    });
});
