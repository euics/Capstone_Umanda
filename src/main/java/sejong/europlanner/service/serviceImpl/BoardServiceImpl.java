package sejong.europlanner.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.europlanner.dto.BoardDto;
import sejong.europlanner.entity.BoardEntity;
import sejong.europlanner.repository.BoardRepository;
import sejong.europlanner.service.serviceinterface.BoardService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<BoardDto> getBoardList() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<BoardDto> boardDtoList = new ArrayList<>();
        for(BoardEntity be : boardEntityList){
            BoardDto mappedDto = mapper.map(be, BoardDto.class);
            boardDtoList.add(mappedDto);
        }

        return boardDtoList;
    }
}
