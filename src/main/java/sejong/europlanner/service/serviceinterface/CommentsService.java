package sejong.europlanner.service.serviceinterface;

import sejong.europlanner.dto.CommentsDto;

import java.util.List;

public interface CommentsService {
    List<CommentsDto> getCommentsList(Long boardId);
}
