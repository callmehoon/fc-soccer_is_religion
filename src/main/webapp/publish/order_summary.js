document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);

    generateOrderInfo();
    displayProductInfo(urlParams);
    displayOrdererInfo(urlParams);
    displayShippingInfo(urlParams);
    displayPaymentInfo(urlParams);
    displayFinalPayment(urlParams);
});

function generateOrderInfo() {
    const now = new Date();
    const orderNumber = now.getFullYear() +
        String(now.getMonth() + 1).padStart(2, '0') +
        String(now.getDate()).padStart(2, '0') +
        String(now.getHours()).padStart(2, '0') +
        String(now.getMinutes()).padStart(2, '0') +
        String(now.getSeconds()).padStart(2, '0');
    const orderDate = now.getFullYear() + '년 ' +
        (now.getMonth() + 1) + '월 ' +
        now.getDate() + '일 ' +
        String(now.getHours()).padStart(2, '0') + ':' +
        String(now.getMinutes()).padStart(2, '0');

    document.getElementById('order_number').textContent = orderNumber;
    document.getElementById('order_date').textContent = orderDate;
}

function displayProductInfo(urlParams) {
    const productName = urlParams.get('productName') || '팬텀 GX II 엘리트 AG-PRO (FJ2554800)';
    const productOption = urlParams.get('productOption') || '사이즈: 275';
    const productQuantity = urlParams.get('productQuantity') || '1개';
    const productPrice = urlParams.get('productPrice') || '299,900원';

    document.getElementById('summary_product_name').textContent = productName;
    document.getElementById('summary_product_option').textContent = productOption;
    document.getElementById('summary_product_quantity').textContent = productQuantity;
    document.getElementById('summary_product_price').textContent = productPrice;
}

function displayOrdererInfo(urlParams) {
    const ordererName = urlParams.get('ordererName') || '-';
    const ordererAddress = urlParams.get('ordererAddress') || '';
    const ordererDetailAddress = urlParams.get('ordererDetailAddress') || '';
    const ordererPhone = urlParams.get('ordererPhone') || '-';
    const ordererEmail = urlParams.get('ordererEmail') || '-';

    let fullAddress = ordererAddress;
    if (ordererDetailAddress) {
        fullAddress += (ordererAddress ? ', ' : '') + ordererDetailAddress;
    }
    if (!fullAddress) fullAddress = '-';

    document.getElementById('display_orderer_name').textContent = ordererName;
    document.getElementById('summary_orderer_name').textContent = ordererName;
    document.getElementById('display_orderer_address').textContent = fullAddress;
    document.getElementById('display_orderer_phone').textContent = ordererPhone;
    document.getElementById('display_orderer_email').textContent = ordererEmail;
}

function displayShippingInfo(urlParams) {
    const receiverName = urlParams.get('receiverName') || '-';
    const receiverAddress = urlParams.get('receiverAddress') || '';
    const receiverDetailAddress = urlParams.get('receiverDetailAddress') || '';
    const receiverPhone = urlParams.get('receiverPhone') || '-';
    const deliveryMessage = urlParams.get('deliveryMessage') || '-';

    let fullAddress = receiverAddress;
    if (receiverDetailAddress) {
        fullAddress += (receiverAddress ? ', ' : '') + receiverDetailAddress;
    }
    if (!fullAddress) fullAddress = '-';

    document.getElementById('display_receiver_name').textContent = receiverName;
    document.getElementById('display_receiver_address').textContent = fullAddress;
    document.getElementById('display_receiver_phone').textContent = receiverPhone;
    document.getElementById('display_delivery_message').textContent = deliveryMessage;
}

function displayPaymentInfo(urlParams) {
    const paymentMethod = urlParams.get('paymentMethod') || '-';
    const useBonusPoint = urlParams.get('useBonusPoint') || '0';
    const bonusPointDisplay = useBonusPoint && parseInt(useBonusPoint) > 0
        ? formatPrice(useBonusPoint) + '원'
        : '0원';

    document.getElementById('display_payment_method').textContent = paymentMethod;
    document.getElementById('summary_payment_method').textContent = paymentMethod;
    document.getElementById('display_bonus_point').textContent = bonusPointDisplay;
}

function displayFinalPayment(urlParams) {
    const totalProductAmount = parseInt(urlParams.get('totalProductAmount')) || 299900;
    const totalDiscountAmount = parseInt(urlParams.get('totalDiscountAmount')) || 2990;
    const totalShippingFee = parseInt(urlParams.get('totalShippingFee')) || 3000;
    const finalAmount = parseInt(urlParams.get('finalAmount')) || (totalProductAmount - totalDiscountAmount + totalShippingFee);

    document.getElementById('display_total_product_amount').textContent = formatPrice(totalProductAmount) + '원';
    document.getElementById('display_total_discount_amount').textContent = '-' + formatPrice(totalDiscountAmount) + '원';
    document.getElementById('display_total_shipping_fee').textContent = '+' + formatPrice(totalShippingFee) + '원';
    document.getElementById('display_final_amount').textContent = formatPrice(finalAmount) + '원';
}

function formatPrice(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}