package toyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import toyproject.controller.dto.OrderRequestDto;
import toyproject.controller.dto.OrderResponseDto;
import toyproject.controller.viewmodel.OrderListViewModel;

import toyproject.service.OrderService;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("")
    public String order(@ModelAttribute OrderRequestDto orderRequestDto, Model model){

        OrderRequestDto dummy = new OrderRequestDto();
        List<Integer> dummylist = new ArrayList<>();
        dummylist.add(10000001);
        dummylist.add(10000002);
        dummylist.add(10000003);
        dummylist.add(10000004);
        dummylist.add(10000005);
        dummy.setProductId(dummylist);

        List<OrderResponseDto> orderResponseDtoList = orderService.searchProducts(dummy);

        OrderListViewModel orderListViewModel = OrderListViewModel.builder().orderList(orderResponseDtoList).build();

        model.addAttribute("orderListViewModel", orderListViewModel);

        return "order";
    }

    @PostMapping("/ordersummary")
    public String orderSummary(@RequestParam Map<String, String> params, Model model) {
        // params에 JSON 문자열로 넘어온 'products'가 포함되어 있습니다.
        // 이 'products'를 파싱해서 View로 넘겨주어야 합니다.
        // 여기서는 JSP/Thymeleaf에서 직접 처리할 수 있도록 그대로 넘깁니다.
        model.addAttribute("orderSummary", params);
        return "order_summary";
    }
}
