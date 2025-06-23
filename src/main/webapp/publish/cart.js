let sizeStockMap = new Map();

function openModal(e) {
    e.preventDefault();
    const modal = document.getElementById("optionModal");
    currentRow = e.target.closest('tr');

    const productId = e.target.getAttribute("data-product-id");
    const prevSize = e.target.getAttribute("data-prev-size");
    const prevQuantity = e.target.getAttribute("data-prev-quantity");

    // 👉 모달 내부 hidden input 또는 JS 전역 변수로 저장 (예시)
    modal.setAttribute("data-product-id", productId);
    modal.setAttribute("data-prev-size", prevSize);
    modal.setAttribute("data-prev-quantity", prevQuantity);

    // 기존 이미지 동기화 (생략 가능)
    const productImg = currentRow.querySelector('.product-info img');
    const modalImg = modal.querySelector('.product-info img');
    if (productImg && modalImg) {
        modalImg.src = productImg.src;
        modalImg.alt = productImg.alt;
    }

    // 사이즈 버튼 새로 그리기 (생략: 이미 작성된 경우)
    fetch(`/cart/option/size?productId=${productId}`)
        .then(res => res.json())
        .then(data => {
            renderSizeButtons(data, prevSize, prevQuantity);
            modal.classList.add("show");
        })
        .catch(err => {
            console.error("사이즈 요청 실패", err);
            alert("옵션 정보를 불러오는 데 실패했습니다.");
        });
}

function closeModal() {
    const modal = document.getElementById("optionModal");
    modal.classList.remove("show");
}

// 모든 사이즈 버튼에 클릭 이벤트 추가
document.querySelectorAll('.size-btn').forEach(btn => {
    btn.addEventListener('click', function () {
        // 모든 버튼에서 selected 클래스 제거
        document.querySelectorAll('.size-btn').forEach(b => b.classList.remove('selected'));

        // 클릭된 버튼에만 selected 클래스 추가
        this.classList.add('selected');

        const selectedSize = this.textContent.trim();

        const displaySpan = document.querySelector('.selected-option span');
        if (displaySpan) {
            displaySpan.textContent = selectedSize;
        }

        const qtyInput = document.querySelector('.selected-option .quantity input');
        if (qtyInput) {
            qtyInput.value = 1;
        }
    });
});

document.querySelectorAll('.qty-btn').forEach(btn => {
    btn.addEventListener('click', function () {
        const input = this.parentElement.querySelector('input');
        let value = parseInt(input.value, 10);

        const selectedSize = parseInt(document.querySelector(".size-btn.selected")?.innerText);
        const maxStock = sizeStockMap.get(selectedSize) || 0;

        if (this.textContent === '+') {
            if (value < maxStock) {
                value++;
            } else {
                console.log("재고 수량을 초과할 수 없습니다.");
            }
        } else if (this.textContent === '-') {
            value = Math.max(1, value - 1);
        }

        input.value = value;
    });
});


// 취소 버튼
document.querySelector('.btn.cancel').addEventListener('click', () => {
    closeModal();
});

function getQueryParams() {
    const urlParams = new URLSearchParams(window.location.search);
    return {
        page: urlParams.get("page") || 1,
        size: urlParams.get("size") || 10
    };
}


document.querySelector(".btn.confirm").addEventListener("click", function () {
    const modal = document.getElementById("optionModal");

    const productId = modal.getAttribute("data-product-id");
    const prevSize = modal.getAttribute("data-prev-size");
    const prevQuantity = modal.getAttribute("data-prev-quantity");

    const newSize = document.querySelector(".size-btn.selected").innerText;
    const newQuantity = document.querySelector(".quantity input").value;

    const payload = {
        productId: parseInt(productId),
        prevSize: parseInt(prevSize),
        prevQuantity: parseInt(prevQuantity),
        newSize: parseInt(newSize),
        newQuantity: parseInt(newQuantity)
    };

    fetch("/cart/update", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    })
        .then(res => {
            if (res.ok) {
                window.location.reload(); // 또는 성공 메시지
            } else {
                alert("장바구니 수정 실패");
            }
        })
        .catch(err => {
            console.error(err);
            alert("요청 실패");
        });
});

function renderSizeButtons(dataList, prevSize, prevQuantity) {
    sizeStockMap.clear(); // 기존 데이터 초기화

    const optionModal = document.getElementById("optionModal");

    // 1. productName 설정
    const productName = dataList[0]?.productName || "상품명 없음";
    const productNameStrong = optionModal.querySelector(".popup-title + strong");
    if (productNameStrong) {
        productNameStrong.textContent = productName;
    }

    // 2. productId 저장용 hidden input 추가 (없으면 생성)
    let hiddenInput = document.getElementById("modalProductId");
    if (!hiddenInput) {
        hiddenInput = document.createElement("input");
        hiddenInput.type = "hidden";
        hiddenInput.id = "modalProductId";
        optionModal.appendChild(hiddenInput);
    }
    hiddenInput.value = dataList[0]?.productId || "";

    // 3. 사이즈 버튼 초기화
    const container = optionModal.querySelector(".option-select");
    container.innerHTML = "<label>사이즈</label>";

    dataList.forEach(item => {
        const size = parseInt(item.size);
        const stockQty = parseInt(item.stockQuantity);

        sizeStockMap.set(size, stockQty);

        const btn = document.createElement("button");
        btn.classList.add("size-btn");
        btn.textContent = item.size;

        if (item.stockQuantity === 0) {
            btn.disabled = true;
            btn.style.backgroundColor = "#ccc";
            btn.title = "품절";
        } else {
            // 정상 버튼일 때만 클릭 이벤트 부여
            btn.addEventListener('click', function () {
                document.querySelectorAll('.size-btn').forEach(b => b.classList.remove('selected'));
                this.classList.add('selected');

                currentStock = item.stockQuantity;
                document.querySelector('.selected-option span').textContent = item.size;
                document.querySelector('.selected-option .quantity input').value = 1;
            });

            if (size === parseInt(prevSize)) {
                btn.classList.add('selected');

                const selectedSpan = document.querySelector('.selected-option span');
                const qtyInput = document.querySelector('.selected-option .quantity input');

                document.querySelector('.selected-option span').textContent = prevSize;
                document.querySelector('.selected-option .quantity input').value = prevQuantity;
            }
        }

        container.appendChild(btn);
    });


    // ✅ selected-option 영역에 값 초기화
    const selectedSpan = document.querySelector('.selected-option span');
    const qtyInput = document.querySelector('.selected-option .quantity input');

    if (selectedSpan) selectedSpan.textContent = prevSize;
    if (qtyInput) qtyInput.value = prevQuantity;
}