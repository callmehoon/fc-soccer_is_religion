package toyproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import toyproject.dto.RegisterRequestDto;

@Mapper
public interface UserMapper {
    void insertUser(RegisterRequestDto dto);
}