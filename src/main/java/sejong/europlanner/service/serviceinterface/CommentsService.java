package sejong.europlanner.service.serviceinterface;

import sejong.europlanner.dto.CommentsDto;
import sejong.europlanner.vo.request.comments.RequestCreateComments;
import sejong.europlanner.vo.request.comments.RequestUpdateComments;
import sejong.europlanner.vo.response.comments.ResponseDeleteComments;

import java.util.List;

public interface CommentsService {
    List<CommentsDto> getCommentsList(Long boardId);

    CommentsDto createComments(Long boardId, RequestCreateComments requestCreateComments);

    CommentsDto updateComments(Long commentsId, RequestUpdateComments requestUpdateComments);

    ResponseDeleteComments deleteComments(Long commentsId);
}
