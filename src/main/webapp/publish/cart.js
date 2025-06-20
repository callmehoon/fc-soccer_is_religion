function openModal(e) {

    console.log("===");

    e.preventDefault(); // 링크 이동 방지
    const modal = document.getElementById("optionModal");
    currentRow = e.target.closest('tr');

    // 이미지 동기화
    const productImg = currentRow.querySelector('.product-info img');
    const modalImg = modal.querySelector('.product-info img');

    if (productImg && modalImg) {
        modalImg.src = productImg.src;
        modalImg.alt = productImg.alt;
    }

    const productId = e.target.getAttribute("data-product-id");

    if (!productId) {
        alert("상품 정보를 불러올 수 없습니다.");
        return;
    }

    fetch(`/cart/option/size?productId=${productId}`)
        .then(res => res.json())
        .then(data => {
            renderSizeButtons(data.sizes); // 사이즈 버튼 생성
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
        // 수량 input 요소 찾기
        const input = this.parentElement.querySelector('input');
        let value = parseInt(input.value, 10);

        if (this.textContent === '+') {
            value++;
        } else if (this.textContent === '-') {
            value = Math.max(1, value - 1); // 최소 1개 유지
        }

        input.value = value;
    });
});


// 취소 버튼
document.querySelector('.btn.cancel').addEventListener('click', () => {
    closeModal();
});

// 확인 버튼
document.querySelector('.btn.confirm').addEventListener('click', () => {
    if (currentRow) {
        // 모달에서 선택한 수량 가져오기
        const quantity = document.querySelector('.selected-option .quantity input').value;

        // 해당 행의 수량 td 수정
        const qtyTd = currentRow.querySelector('td:nth-child(3)');
        qtyTd.innerHTML = `${quantity}개<br/><a href="#" onclick="openModal(event)">옵션/수정변경</a>`;
    }

    closeModal();
});

function renderSizeButtons(sizes) {
    const container = document.querySelector("#optionModal .option-select");
    container.innerHTML = "<label>사이즈</label>"; // 초기화

    sizes.forEach(size => {
        const btn = document.createElement("button");
        btn.classList.add("size-btn");
        btn.textContent = size;
        btn.addEventListener('click', function () {
            document.querySelectorAll('.size-btn').forEach(b => b.classList.remove('selected'));
            this.classList.add('selected');

            document.querySelector('.selected-option span').textContent = size;
            document.querySelector('.selected-option .quantity input').value = 1;
        });
        container.appendChild(btn);
    });
}