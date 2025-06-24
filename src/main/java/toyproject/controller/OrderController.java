package toyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import toyproject.controller.dto.OrderItemRequestDto;
import toyproject.controller.dto.OrderRequestDto;
import toyproject.controller.dto.OrderResponseDto;
import toyproject.controller.viewmodel.OrderListViewModel;

import toyproject.service.OrderService;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 페이지: 세션에 저장된 주문 정보를 기반으로 ViewModel 구성
    @GetMapping("")
    public String order(HttpSession session, Model model){
        OrderRequestDto orderRequestDto = (OrderRequestDto) session.getAttribute("orderRequestDto");

        // 상품 id 만 추출해서 DB 조회
        List<OrderResponseDto> orderResponseDtoList = orderService.searchProducts(orderRequestDto);

        // session에서 정보 꺼내서 response랑 조합해서 조합데이터 만들기
        // 상품 수량,사이즈 정보는 세션에 존재 db 조회 정보랑 세션 정보 합쳐서 리스트 만들기

        // OrderRequestDto에서 productId를 키로 하는 Map 생성 (수량, 사이즈 정보)
        Map<Integer, OrderItemRequestDto> sessionDataMap = new HashMap<>();
        for (OrderItemRequestDto item : orderRequestDto.getProductId()) {
            sessionDataMap.put(item.getProductId(), item);
        }

        // DB 조회 결과와 세션 데이터를 합쳐서 최종 주문 리스트 생성
        List<OrderResponseDto> finalOrderList = new ArrayList<>();
        for (OrderResponseDto dbProduct : orderResponseDtoList) {
            OrderItemRequestDto sessionItem = sessionDataMap.get(dbProduct.getProductId());

            if (sessionItem != null) {
                // DB 정보 + 세션 정보를 합친 새로운 OrderResponseDto 생성
                OrderResponseDto mergedOrder = OrderResponseDto.builder()
                        .productId(dbProduct.getProductId())
                        .productImg(dbProduct.getProductImg())
                        .productName(dbProduct.getProductName())
                        .productPrice(dbProduct.getProductPrice())
                        .quantity(sessionItem.getQuantity())
                        .size(sessionItem.getSize())
                        .build();

                finalOrderList.add(mergedOrder);
            }
        }

        // ViewModel 구성 후 뷰에 전달 - 합쳐진 데이터 사용
        OrderListViewModel orderListViewModel = OrderListViewModel.builder()
                .orderList(finalOrderList)
                .build();
        model.addAttribute("orderListViewModel", orderListViewModel);

        return "order";
    }

    // 주문 정보 전처리용 엔드포인트: JSON으로 받은 주문 정보를 세션에 저장
    @PostMapping("/prepare")
    public String prepareOrder(@RequestBody OrderRequestDto requestDto, HttpSession session) {
        session.setAttribute("orderRequestDto", requestDto);
        return "redirect:/order"; // GET 요청으로 리디렉션
    }

    @PostMapping("/summary")
    public String orderSummary(@RequestParam Map<String, String> params, Model model) {
        // params에 JSON 문자열로 넘어온 'products'가 포함되어 있습니다.
        // 이 'products'를 파싱해서 View로 넘겨주어야 합니다.
        // 여기서는 JSP/Thymeleaf에서 직접 처리할 수 있도록 그대로 넘깁니다.
        model.addAttribute("orderSummary", params);
        return "order_summary";
    }
}
