package toyproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import toyproject.dto.TestReponseDto;
import toyproject.dto.TestRequestDto;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestReponseDto> searchTests(TestRequestDto request);
}
