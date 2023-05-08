package sejong.europlanner.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.europlanner.dto.BoardDto;
import sejong.europlanner.entity.BoardEntity;
import sejong.europlanner.entity.UserEntity;
import sejong.europlanner.exception.customexception.BoardNotFoundException;
import sejong.europlanner.exception.customexception.UserNotFoundException;
import sejong.europlanner.repository.BoardRepository;
import sejong.europlanner.repository.UserRepository;
import sejong.europlanner.service.serviceinterface.BoardService;
import sejong.europlanner.vo.request.board.RequestCreateBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
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

    @Override
    public BoardDto createBoard(RequestCreateBoard requestCreateBoard) {
        Optional<UserEntity> savedUser = userRepository.findById(requestCreateBoard.getUserId());

        if(savedUser.isEmpty())
            throw new UserNotFoundException("존재하지 않는 회원입니다.");

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        BoardDto mappedDto = mapper.map(requestCreateBoard, BoardDto.class);
        BoardEntity newBoardEntity = BoardEntity.boardEntity(mappedDto, savedUser.get());
        BoardEntity savedBoardEntity = boardRepository.save(newBoardEntity);

        return new ModelMapper().map(savedBoardEntity, BoardDto.class);
    }

    @Override
    public BoardDto getBoardById(Long boardId) {
        Optional<BoardEntity> savedBoard = boardRepository.findById(boardId);

        if (savedBoard.isEmpty())
            throw new BoardNotFoundException("존재하지 않는 게시판입니다.");

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return mapper.map(savedBoard.get(), BoardDto.class);
    }
}
