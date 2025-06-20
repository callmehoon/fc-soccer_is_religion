package toyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toyproject.dto.ProductResponseDto;
import toyproject.service.ProductService;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/read")
    public String detail(@RequestParam("productID") Integer productId, Model m) {
        System.out.println("controller");
        try{
            ProductResponseDto paramDto= ProductResponseDto.builder()
            .productID(productId)
            .build();

            System.out.println("넘어온 productId = " + productId);

            ProductResponseDto productDto = productService.productDetail(paramDto);
            System.out.println("가져온 productDto = " + productDto);
            m.addAttribute("productDto", productDto);

        }catch(Exception e){
            e.printStackTrace();
        }
        return "product_detail";
    }
}



