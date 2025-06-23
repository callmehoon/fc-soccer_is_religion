// src/main/java/toyproject/service/CategoryService.java
package toyproject.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.dto.BigCategoryDto;
import toyproject.dto.MiddleCategoryDto;
import toyproject.dto.SmallCategoryDto;
import toyproject.mapper.CategoryMapper;

@Service
public class CategoryService {
    private final CategoryMapper mapper;

    public CategoryService(CategoryMapper mapper) {
        this.mapper = mapper;
    }

    /** 모든 카테고리를 계층 구조로 조립해서 반환 */
    @Transactional(readOnly = true)
    public List<BigCategoryDto> getFullCategoryTree() {
        List<BigCategoryDto> bigs = mapper.selectAllBig();
        for (BigCategoryDto big : bigs) {
            // 미들
            List<MiddleCategoryDto> middles =
                    mapper.selectMiddleByBigId(big.getMajorCategoryId());
            for (MiddleCategoryDto mid : middles) {
                // 스몰
                List<SmallCategoryDto> smalls =
                        mapper.selectSmallByMiddleId(mid.getMidCategoryId());
                mid.setSmalls(smalls);
            }
            big.setMiddles(middles);
        }
        return bigs;
    }
}
