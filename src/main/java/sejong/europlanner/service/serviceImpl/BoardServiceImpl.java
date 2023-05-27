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
import sejong.europlanner.vo.request.board.RequestUpdateBoard;
import sejong.europlanner.vo.response.board.ResponseDeleteBoard;

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
            Optional<UserEntity> findUser = userRepository.findById(be.getUser().getId());
            BoardDto mappedDto = mapper.map(be, BoardDto.class);
            mappedDto.setGender(findUser.get().getGender());
            mappedDto.setBirthDate(findUser.get().getBirthdate());
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
        Optional<UserEntity> savedUser = userRepository.findById(savedBoard.get().getUser().getId());

        if (savedBoard.isEmpty())
            throw new BoardNotFoundException("존재하지 않는 게시판입니다.");

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        BoardDto mappedDto = mapper.map(savedBoard.get(), BoardDto.class);
        mappedDto.setBirthDate(savedUser.get().getBirthdate());
        mappedDto.setGender(savedUser.get().getGender());

        return mappedDto;
    }

    @Override
    public BoardDto updateBoard(RequestUpdateBoard requestUpdateBoard) {
        Optional<BoardEntity> savedBoard = boardRepository.findById(requestUpdateBoard.getId());

        if(savedBoard.isEmpty())
            throw new BoardNotFoundException("존재하지 않는 게시판입니다.");

        savedBoard.get().setTitle(requestUpdateBoard.getTitle());
        savedBoard.get().setContent(requestUpdateBoard.getContent());

        BoardEntity newSavedBoard = boardRepository.save(savedBoard.get());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return mapper.map(newSavedBoard, BoardDto.class);
    }

    @Override
    public ResponseDeleteBoard deleteBoard(Long boardId) {
        Optional<BoardEntity> savedBoard = boardRepository.findById(boardId);

        if(savedBoard.isEmpty())
            throw new BoardNotFoundException("존재하지 않는 게시판입니다.");

        boardRepository.delete(savedBoard.get());

        ResponseDeleteBoard responseDeleteBoard = new ResponseDeleteBoard();
        responseDeleteBoard.setMessage("게시판이 삭제되었습니다.");

        return responseDeleteBoard;
    }
}
