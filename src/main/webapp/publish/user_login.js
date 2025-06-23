document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("loginForm");
    const emailInput = document.getElementById("email");
    const pwInput = document.getElementById("password");

    loginForm.addEventListener("submit", function (e) {
        const email = emailInput.value.trim();
        const password = pwInput.value.trim();

        // 1. 이메일 입력 확인
        if (email === "") {
            alert("이메일을 입력하세요.");
            emailInput.focus();
            e.preventDefault();  // submit 중단
            return;
        }

        // 2. 이메일 형식 확인
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert("올바른 이메일 형식을 입력하세요.");
            emailInput.focus();
            e.preventDefault();
            return;
        }

        // 3. 비밀번호 입력 확인
        if (password === "") {
            alert("비밀번호를 입력하세요.");
            pwInput.focus();
            e.preventDefault();
        }
    });
});