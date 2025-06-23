// 📌 이메일 형식 유효성 검사
function validateEmail(email) {
    const pattern = /^[\w.-]+@[\w.-]+\.\w+$/;
    return pattern.test(email);
}

// 📌 비밀번호와 확인란 일치 검사
function validatePasswordMatch(pw1, pw2) {
    return pw1 === pw2;
}

// 📌 생년월일 select 동적 생성
function populateBirthSelects() {
    const yearSelect = document.querySelector("select[name='birthYear']");
    const monthSelect = document.querySelector("select[name='birthMonth']");
    const daySelect = document.querySelector("select[name='birthDay']");
    const thisYear = new Date().getFullYear();

    for (let y = thisYear; y >= 1940; y--) {
        yearSelect.innerHTML += `<option value="${y}">${y}년</option>`;
    }
    for (let m = 1; m <= 12; m++) {
        monthSelect.innerHTML += `<option value="${m}">${m}월</option>`;
    }
    for (let d = 1; d <= 31; d++) {
        daySelect.innerHTML += `<option value="${d}">${d}일</option>`;
    }
}

// 📌 전체 동의 체크박스 → 하위 체크 연동
function setupAgreementCheck() {
    const agreeAll = document.querySelector("input[name='agreeAll']");
    const subChecks = document.querySelectorAll(".agreements input[type='checkbox']:not([name='agreeAll'])");

    // 전체동의 체크 시 → 하위 전체 선택/해제
    agreeAll.addEventListener("change", function () {
        subChecks.forEach(chk => {
            chk.checked = agreeAll.checked;
        });
    });

    // 하위 체크박스 변경 시 → 전체동의 상태 갱신
    subChecks.forEach(chk => {
        chk.addEventListener("change", function () {
            const allChecked = Array.from(subChecks).every(c => c.checked);
            agreeAll.checked = allChecked;
        });
    });
}

// 📌 이메일 입력 시 형식 검증 메시지 표시
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("email").addEventListener("input", function () {
        const email = this.value;
        const msg = document.getElementById("emailError");
        if (!validateEmail(email)) {
            msg.textContent = "이메일 형식이 아닙니다.";
        } else {
            msg.textContent = "";
        }
    });

    document.getElementById("pw2").addEventListener("input", function () {
        const pw1 = document.getElementById("pw1").value;
        const pw2 = this.value;
        const msg = document.getElementById("pwError");
        if (pw1 !== pw2) {
            msg.textContent = "비밀번호가 일치하지 않습니다.";
        } else {
            msg.textContent = "";
        }
    });
});

// 📌 우편번호 API 연동
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            let addr = data.userSelectedType === 'R' ? data.roadAddress : data.jibunAddress;
            let extraAddr = '';
            if (data.userSelectedType === 'R') {
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }
            }
            const receiverAddressInput = document.getElementById('receiver_address');
            if (receiverAddressInput) {
                receiverAddressInput.value = `(${data.zonecode}) ${addr}${extraAddr}`;
            }
            const detailAddressInput = document.getElementById('receiver_detail_address');
            if (detailAddressInput) {
                detailAddressInput.focus();
            }
        },
        width: '100%',
        height: '100%'
    }).open();
}

// 📌 폼 유효성 검사 통합 (submit 전에 검사)
function validateForm() {
    const email = document.querySelector("input[type='email']").value;
    const pw1 = document.getElementById("pw1").value;
    const pw2 = document.getElementById("pw2").value;

    if (!validateEmail(email)) {
        alert("올바른 이메일 형식을 입력해주세요.");
        return false;
    }
    if (!validatePasswordMatch(pw1, pw2)) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }
    return true;
}

// 📌 초기 실행
window.addEventListener("DOMContentLoaded", function () {
    populateBirthSelects();
    setupAgreementCheck();

    // 폼 제출 유효성 검사
    document.querySelector("form").addEventListener("submit", function (e) {
        if (!validateForm()) {
            e.preventDefault();
        }
    });

    // 우편번호 검색 버튼
    const zipBtn = document.querySelector("button");
    if (zipBtn) {
        zipBtn.addEventListener("click", function (e) {
            e.preventDefault();
            execDaumPostcode();
        });
    }
});