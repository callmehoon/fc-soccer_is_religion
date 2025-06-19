package toyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import toyproject.dto.CartRequestDto;
import toyproject.dto.CartResponseDto;
import toyproject.service.CartService;
import toyproject.viewmodel.CartListViewModel;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("")
    public String cart(@ModelAttribute CartRequestDto cartRequestDto, Model model) {

        System.out.println("카트 컨트롤러 진입");;

        CartRequestDto dummy = new CartRequestDto();
        dummy.setUserId("U01357");

        List<CartResponseDto> cartResponseDtoList = cartService.searchCart(dummy);

        System.out.printf("서비스단 완료, 리스트값 가지고 컨트롤러 돌아옴");

        CartListViewModel cartListViewModel = CartListViewModel.builder().cartList(cartResponseDtoList)
                .build();

        System.out.println(cartListViewModel.toString());

        model.addAttribute("cartListViewModel", cartListViewModel);

        return "cart";
    }


}
