package sejong.europlanner.service.serviceinterface;

import sejong.europlanner.dto.CommentsDto;
import sejong.europlanner.vo.request.comments.RequestCreateComments;

import java.util.List;

public interface CommentsService {
    List<CommentsDto> getCommentsList(Long boardId);

    CommentsDto createComments(Long boardId, RequestCreateComments requestCreateComments);
}
