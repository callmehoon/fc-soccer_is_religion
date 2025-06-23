package toyproject.mapper;

import java.util.List;
import toyproject.dto.BigCategoryDto;

public interface CategoryMapper {
    List<BigCategoryDto> selectAllBig();
    List<toyproject.dto.MiddleCategoryDto> selectMiddleByBigId(int majorCategoryId);
    List<toyproject.dto.SmallCategoryDto>  selectSmallByMiddleId(int midCategoryId);
}