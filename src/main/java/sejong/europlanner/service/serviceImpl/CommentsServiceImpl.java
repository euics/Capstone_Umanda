package sejong.europlanner.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.europlanner.dto.CommentsDto;
import sejong.europlanner.entity.BoardEntity;
import sejong.europlanner.entity.CommentsEntity;
import sejong.europlanner.exception.customexception.BoardNotFoundException;
import sejong.europlanner.repository.BoardRepository;
import sejong.europlanner.service.serviceinterface.CommentsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentsServiceImpl implements CommentsService {

    private final BoardRepository boardRepository;

    @Autowired
    public CommentsServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<CommentsDto> getCommentsList(Long boardId) {
        Optional<BoardEntity> savedBoard = boardRepository.findById(boardId);

        if(savedBoard.isEmpty())
            throw new BoardNotFoundException("존재하지 않는 게시판입니다.");

        List<CommentsEntity> commentsEntityList = savedBoard.get().getComments();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<CommentsDto> commentsDtoList = new ArrayList<>();
        for(CommentsEntity cm : commentsEntityList){
            CommentsDto mappedDto = mapper.map(cm, CommentsDto.class);
            commentsDtoList.add(mappedDto);
        }

        return commentsDtoList;
    }
}
