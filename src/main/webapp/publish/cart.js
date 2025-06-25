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

document.getElementById("purchaseSelectedBtn").addEventListener("click", () => {
    const selectedItems = [];

    document.querySelectorAll(".cart-item-checkbox:checked").forEach(cb => {
        const row = cb.closest("tr");

        const productId = parseInt(row.dataset.productId);
        const sizeText = row.querySelector(".product-info").innerText.match(/사이즈\s*:\s*(\d+|Free)/i);
        const size = sizeText && sizeText[1] === "Free" ? 0 : parseInt(sizeText[1]);
        const quantity = parseInt(row.querySelector("td:nth-child(3)").innerText);

        selectedItems.push({
            productId,
            size,
            quantity
        });
    });

    if (selectedItems.length === 0) {
        alert("상품을 선택해주세요.");
        return;
    }

    const payload = {
        productId: selectedItems
    };

    fetch("/order/prepare", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    })
        .then(res => {
            if (res.redirected) {
                window.location.href = res.url; // 보통은 /order 로 리디렉션됨
            } else {
                alert("주문 페이지 이동 실패");
            }
        })
        .catch(err => {
            console.error("주문 요청 실패", err);
            alert("요청 중 문제가 발생했습니다.");
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






function renderStockStatus(cart) {
    if (cart.stockQuantity === 0) {
        return `<span class="stock-warning">❌ 제품 품절</span>`;
    } else if (cart.cartProductQuantity > cart.stockQuantity) {
        return `<span class="stock-warning">⚠️ 현재 남은 재고수량: ${cart.stockQuantity}</span>`;
    } else if (cart.stockQuantity < 5) {
        return `<span class="stock-warning">🟡 남은 재고수량: ${cart.stockQuantity}</span>`;
    } else {
        return '';
    }
}

function updateSummaryInfo() {
    const checkboxes = document.querySelectorAll(".cart-item-checkbox:checked");

    let productCount = 0;
    let totalPrice = 0;
    let totalDiscount = 0;

    checkboxes.forEach(cb => {
        const row = cb.closest("tr");
        const quantity = parseInt(row.querySelector("td:nth-child(3)").innerText);

        // 단가 계산
        const unitPrice = parseInt(
            row.querySelector("td:nth-child(4) strong").innerText.replace(/[^0-9]/g, "")
        ) / quantity;

        // 할인금액 가져오기
        const discountText = row.querySelector("td:nth-child(5)").innerText;
        const matched = discountText.match(/할인\s*-\s*([\d,]+)원/);
        const discount = matched ? parseInt(matched[1].replace(/,/g, '')) : 0;

        productCount++;
        totalPrice += unitPrice * quantity;
        totalDiscount += discount;
    });

    const shipping = productCount > 0 ? 3000 : 0;

    document.getElementById("summary-count").textContent = productCount;
    document.getElementById("summary-price").textContent = totalPrice.toLocaleString();
    document.getElementById("summary-discount").textContent = totalDiscount.toLocaleString();
    document.getElementById("summary-shipping").textContent = `+${shipping.toLocaleString()}원`;
    document.getElementById("summary-total").textContent = (totalPrice - totalDiscount + shipping).toLocaleString();
}

// ✅ 체크박스 변경 시 반영
document.addEventListener("change", function (e) {
    if (e.target.classList.contains("cart-item-checkbox")) {
        updateSummaryInfo();
    }
});

// ✅ 초기 진입 시에도 반영
document.addEventListener("DOMContentLoaded", updateSummaryInfo);

// 전체 선택 체크박스 처리
const selectAll = document.getElementById("selectAllCheckbox");
selectAll.addEventListener("change", () => {
    document.querySelectorAll(".cart-item-checkbox").forEach(cb => cb.checked = selectAll.checked);
    updateSummaryInfo();
});

// 개별 체크박스 변경 시 전체 체크 상태 동기화
document.addEventListener("change", e => {
    if (e.target.classList.contains("cart-item-checkbox")) {
        const all = document.querySelectorAll(".cart-item-checkbox");
        const checked = document.querySelectorAll(".cart-item-checkbox:checked");
        selectAll.checked = all.length === checked.length;
        updateSummary();
    }
});


document.getElementById("deleteSelectedBtn").addEventListener("click", () => {
    const selectedItems = [];

    document.querySelectorAll(".cart-item-checkbox:checked").forEach(cb => {
        const row = cb.closest("tr");
        const productId = parseInt(row.dataset.productId);
        const sizeText = row.querySelector(".product-info").innerText.match(/사이즈\s*:\s*(\d+|Free)/i);
        const size = sizeText && sizeText[1] === "Free" ? 0 : parseInt(sizeText[1]);

        selectedItems.push({productId, size});
    });

    if (selectedItems.length === 0) {
        alert("삭제할 상품을 선택해주세요.");
        return;
    }

    if (!confirm("정말 선택한 상품들을 삭제하시겠습니까?")) return;

    fetch("/cart/delete", {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({items: selectedItems})
    })
        .then(res => {
            if (res.ok) {
                alert("선택된 상품이 삭제되었습니다.");
                window.location.reload();
            } else {
                alert("삭제 실패!");
            }
        })
        .catch(err => {
            console.error("삭제 요청 실패", err);
            alert("삭제 중 오류가 발생했습니다.");
        });
});

// 배송비 적용: 선택된 항목 1개 이상일 경우 3000원
function updateSummary() {
    const checkboxes = document.querySelectorAll(".cart-item-checkbox:checked");
    const isSelected = checkboxes.length > 0;
    const shipping = isSelected ? 3000 : 0;
    document.getElementById("summary-shipping").textContent = `+${shipping.toLocaleString()}원`;
    // 여기에 total 계산 등 추가 처리 가능
}

