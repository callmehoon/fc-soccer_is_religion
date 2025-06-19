package toyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import toyproject.dto.OrderRequestDto;
import toyproject.dto.OrderResponseDto;
import toyproject.service.OrderService;
import toyproject.viewmodel.OrderListViewModel;

import java.util.ArrayList;
import java.util.List;

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
}
