package toyproject.SON;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import toyproject.controller.dto.BigCategoryDto;
import toyproject.controller.dto.ProductDto;
import toyproject.service.CategoryService;
import toyproject.service.ListService;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class List_Controller {
    private static final Logger log = LoggerFactory.getLogger(List_Controller.class);
    private final ListService service;
    private final CategoryService categoryService;
    private final ListService listService;

    @Autowired
    public List_Controller(ListService service,
                           CategoryService categoryService, ListService listService) {
        this.service = service;
        this.categoryService = categoryService;
        this.listService = listService;
    }

    /** 모든 요청에 공통으로 넘어갈 카테고리 트리 */
    @ModelAttribute("categoryTree")
    public List<BigCategoryDto> categoryTree() {
        return categoryService.getFullCategoryTree();
    }

    /** 테스트용 */
    @GetMapping("/list_test")
    public void main() {
        System.out.println("list");
    }

    /** NEW 상품 리스트 */
    @GetMapping("/new")
    public void newList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "9") int size,
            Model model
    ) {
        int totalCount = service.getNewCount();
        List<ProductDto> list = service.getNewPage(page, size);
        int totalPages = (int) Math.ceil(totalCount / (double) size);

        model.addAttribute("productList", list);
        model.addAttribute("totalCount",  totalCount);
        model.addAttribute("page",        page);
        model.addAttribute("size",        size);
        model.addAttribute("totalPages",  totalPages);

        // layout.jsp 에서 viewName 으로 new.jsp를 include 하도록
        model.addAttribute("viewName",  "new.jsp");
        model.addAttribute("pageTitle", "NEW");
    }

    /** BRAND 메인 (브랜드 목록 페이지) */
    @GetMapping("/brand")
    public void brand() {
        System.out.println("brand");
    }

    /** 선택한 브랜드의 상품 리스트 (new.jsp 재활용) */
    @GetMapping("/brand/{brandId}")
    public String brandList(
            @PathVariable int brandId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "9") int size,
            Model model
    ) {
        // 전체 개수, 페이징 리스트 조회
        int totalCount = service.getBrandCount(brandId);
        List<ProductDto> list = service.getBrandPage(brandId, page, size);
        int totalPages = (int) Math.ceil(totalCount / (double) size);

        // 페이지 타이틀: 비어있으면 그냥 "BRAND"
        String pageTitle = list.isEmpty()
                ? "BRAND"
                : list.get(0).getBrandName();

        model.addAttribute("productList", list);
        model.addAttribute("totalCount",  totalCount);
        model.addAttribute("page",        page);
        model.addAttribute("size",        size);
        model.addAttribute("totalPages",  totalPages);

        // 레이아웃에서 include 할 뷰와 타이틀
        model.addAttribute("viewName",  "new.jsp");
        model.addAttribute("pageTitle", pageTitle);

        // layout.jsp 를 직접 렌더링
        return "new";
    }

    @GetMapping("/football_shoes")
    public String footballShoes(@RequestParam(required = false, defaultValue = "13") Integer midCategoryId,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "9") int size,
                                        Model model) {
                                int offset = (page - 1) * size;
                                int limit = size;
                                //midCategoryId 로 필터링된 상품 리스트
                                List<ProductDto> list =
                                        listService.getByMiddleCategory(midCategoryId, offset, limit);
                                int total = listService.countByMiddleCategory(midCategoryId);

                                model.addAttribute("productList", list);
                                model.addAttribute("midCategoryId", midCategoryId);
                                model.addAttribute("totalCount",  total);
                                model.addAttribute("page",        page);
                                model.addAttribute("size",        size);
                                return "football_shoes";

    }



    @GetMapping("/goods")
    public void goods() {
        System.out.println("goods");
    }


    @GetMapping("/clothes")
    public String clothes(
            @RequestParam(required = false) List<Integer> midCategoryIds,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "9") int size,
            Model model
    ) {
        // 만약 파라미터가 없으면 기본 의류 카테고리 리스트로 세팅
        if (midCategoryIds == null || midCategoryIds.isEmpty()) {
            midCategoryIds = List.of(11,12,15,18,19,20,22,23,24);
        }

        List<ProductDto> list = service.getByMiddleCategories(midCategoryIds, page, size);
        int total = service.countByMiddleCategories(midCategoryIds);

        model.addAttribute("productList", list);
        model.addAttribute("totalCount",   total);

        return "clothes";  // layout.jsp 에서 viewName include
    }
}
