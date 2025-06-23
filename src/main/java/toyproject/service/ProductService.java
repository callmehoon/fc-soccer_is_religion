package toyproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import toyproject.dto.ProductDto;
import toyproject.mapper.ProductMapper;
import toyproject.mapper.ProductOptionMapper;

@Service
public class ProductService {
    private final ProductMapper mapper;
    private final ProductMapper productMapper;
    private final ProductOptionMapper productOptionMapper;

    @Autowired
    public ProductService(ProductMapper mapper, ProductMapper productMapper, ProductOptionMapper productOptionMapper) {
        this.mapper = mapper;
        this.productMapper = productMapper;
        this.productOptionMapper = productOptionMapper;
    }



    /** NEW 상품 전체 개수 */
    @Transactional(readOnly = true)
    public int getNewCount() {
        return mapper.selectNewProductCount();
    }

    /** NEW 상품 페이지 조회 */
    @Transactional(readOnly = true)
    public List<ProductDto> getNewPage(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        List<ProductDto> list = productMapper.selectNewProductPage(offset, pageSize);

        // 각 상품마다 옵션 사이즈를 "/"로 합쳐서 DTO에 세팅
        for (ProductDto p : list) {
            List<String> sizes = productOptionMapper.selectSizeByProductId(p.getProductId());
            String joined = sizes.stream().collect(Collectors.joining("/"));
            p.setSize(joined);
        }
        return list;

    }

    /** 브랜드별 전체 개수 */
    @Transactional(readOnly = true)
    public int getBrandCount(int brandId) {
        return mapper.selectBrandProductCount(brandId);
    }

    /** 브랜드별 페이징 상품 리스트 */
    @Transactional(readOnly = true)
    public List<ProductDto> getBrandPage(int brandId, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        List<ProductDto> list = mapper.selectBrandProductPage(brandId, offset, pageSize);

        // 옵션 사이즈 조립
        for (ProductDto p : list) {
            List<String> sizes = productOptionMapper.selectSizeByProductId(p.getProductId());
            String joined = sizes.stream().collect(Collectors.joining("/"));
            p.setSize(joined);
        }
        return list;
    }

    //중분류 ID로 축구화 상품을 조회
    @Transactional(readOnly = true)
    public List<ProductDto> getByMiddleCategory(Integer midCategoryId, int page, int size) {
        int offset = (page - 1) * size;
        if(offset < 0){
            offset=0;
        }
        return productMapper.selectByMiddleCategory(midCategoryId, offset, size);
    }
    public int countByMiddleCategory(Integer midCategoryId) {
        return productMapper.countByMiddleCategory(midCategoryId);
    }


        @Transactional(readOnly = true)
        public List<ProductDto> getByMiddleCategories(List<Integer> midCategoryIds, int page, int size) {
            int offset = (page - 1) * size;
            return mapper.selectByMiddleCategories(midCategoryIds, offset, size);
        }

        @Transactional(readOnly = true)
        public int countByMiddleCategories(List<Integer> midCategoryIds) {
            return mapper.countByMiddleCategories(midCategoryIds);
        }


}
