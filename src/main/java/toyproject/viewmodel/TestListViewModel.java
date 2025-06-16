package toyproject.viewmodel;

import lombok.*;
import toyproject.dto.TestReponseDto;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestListViewModel {
        private List<TestReponseDto> tests;

}
