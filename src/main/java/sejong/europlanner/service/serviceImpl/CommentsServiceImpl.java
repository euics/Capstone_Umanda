package sejong.europlanner.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.europlanner.dto.CommentsDto;
import sejong.europlanner.entity.BoardEntity;
import sejong.europlanner.entity.CommentsEntity;
import sejong.europlanner.entity.UserEntity;
import sejong.europlanner.exception.customexception.BoardNotFoundException;
import sejong.europlanner.exception.customexception.CommentsNotFoundException;
import sejong.europlanner.repository.BoardRepository;
import sejong.europlanner.repository.CommentsRepository;
import sejong.europlanner.service.serviceinterface.CommentsService;
import sejong.europlanner.vo.request.comments.RequestCreateComments;
import sejong.europlanner.vo.request.comments.RequestUpdateComments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentsServiceImpl implements CommentsService {

    private final BoardRepository boardRepository;
    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsServiceImpl(BoardRepository boardRepository, CommentsRepository commentsRepository) {
        this.boardRepository = boardRepository;
        this.commentsRepository = commentsRepository;
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

    @Override
    public CommentsDto createComments(Long boardId, RequestCreateComments requestCreateComments) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CommentsDto mappedDto = mapper.map(requestCreateComments, CommentsDto.class);

        Optional<BoardEntity> savedBoard = boardRepository.findById(boardId);
        if(savedBoard.isEmpty())
            throw new BoardNotFoundException("존재하지 않는 게시판입니다.");

        UserEntity savedUser = savedBoard.get().getUser();

        CommentsEntity commentsEntity = CommentsEntity.commentsEntity(mappedDto, savedBoard.get(), savedUser);
        CommentsEntity savedComments = commentsRepository.save(commentsEntity);

        return mapper.map(savedComments, CommentsDto.class);
    }

    @Override
    public CommentsDto updateComments(Long commentsId, RequestUpdateComments requestUpdateComments) {
        Optional<CommentsEntity> savedComments = commentsRepository.findById(commentsId);

        if(savedComments.isEmpty())
            throw new CommentsNotFoundException("존재하지 않는 댓글입니다.");

        savedComments.get().setComments(requestUpdateComments.getComments());

        CommentsEntity savedNewComments = commentsRepository.save(savedComments.get());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return mapper.map(savedNewComments, CommentsDto.class);
    }
}
