package toyproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import toyproject.controller.dto.TestReponseDto;
import toyproject.controller.dto.TestRequestDto;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestReponseDto> searchTests(TestRequestDto request);
}
