document.addEventListener("DOMContentLoaded", () => {
    const tabs = document.querySelectorAll(".tab");
    const icons = document.querySelectorAll(".faq-icon");
    const faqItems = document.querySelectorAll(".faq-item");
    const searchInput = document.getElementById("searchInput");

    tabs.forEach(tab => {
        tab.addEventListener("click", () => {
            tabs.forEach(t => t.classList.remove("active"));
            tab.classList.add("active");

            const category = tab.getAttribute("data-category");
            faqItems.forEach(item => {
                const matchesCategory = category === "전체" || item.getAttribute("data-category") === category;
                item.style.display = matchesCategory ? "" : "none";
            });
        });
    });

    searchInput.addEventListener("input", () => {
        const keyword = searchInput.value.toLowerCase();
        faqItems.forEach(item => {
            const text = item.innerText.toLowerCase();
            item.style.display = text.includes(keyword) ? "" : "none";
        });
    });

    icons.forEach(icon => {
        icon.addEventListener("click", () => {
            const category = icon.getAttribute("data-category");
            faqItems.forEach(item => {
                const matches = item.getAttribute("data-category") === category;
                item.style.display = matches ? "" : "none";
            });
        });
    });

    document.querySelectorAll(".faq-question").forEach(q => {
        q.addEventListener("click", () => {
            const parent = q.closest(".faq-item");
            parent.classList.toggle("active");
        });
    });
});

const modal = document.getElementById("inquiryModal");
const btn = document.getElementById("openModalBtn");
const span = modal.querySelector(".close");

btn.onclick = () => modal.style.display = "block";
span.onclick = () => modal.style.display = "none";
window.onclick = (e) => {
    if (e.target === modal) modal.style.display = "none";
};

document.querySelectorAll(".btn-wrap button").forEach(btn => {
    if (btn.textContent.includes("이전")) {
        btn.addEventListener("click", () => {
            modal.style.display = "none";
        });
    }
});