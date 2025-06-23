package toyproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import toyproject.dto.ProductDto;

@Mapper
public interface ProductMapper {
    // NEW
    int selectNewProductCount(); //NEW 상품 전체개수
    List<ProductDto> selectNewProductPage(@Param("offset") int offset,
                                          @Param("pageSize") int pageSize);

    //Brand
    int selectBrandProductCount(@Param("brandId")int brandId);
    List<ProductDto> selectBrandProductPage(@Param("brandId") int brandId,
                                            @Param("offset")   int offset,
                                            @Param("pageSize") int pageSize);

    // 축구화
    int selectFootballProductCount();
    List<ProductDto> selectFootballProductPage(@Param("offset")   int offset,
                                               @Param("pageSize") int pageSize);

    // 의류
    int selectApparelProductCount();
    List<ProductDto> selectApparelProductPage(@Param("offset")   int offset,
                                              @Param("pageSize") int pageSize);

    // 용품
    int selectGoodsProductCount();
    List<ProductDto> selectGoodsProductPage(@Param("offset")   int offset,
                                            @Param("pageSize") int pageSize);

    List<ProductDto> selectByMiddleCategory(@Param("midCategoryId") Integer midCategoryId
    ,@Param("offset") int offset, @Param("limit") int limit);

    int countByMiddleCategory(@Param("midCategoryId") Integer midCategoryId);


    List<ProductDto> selectByMiddleCategories(
            @Param("midCategoryIds") List<Integer> midCategoryIds,
            @Param("offset") int offset, @Param("limit") int limit
    );
    int countByMiddleCategories(@Param("midCategoryIds") List<Integer> midCategoryIds);
}
