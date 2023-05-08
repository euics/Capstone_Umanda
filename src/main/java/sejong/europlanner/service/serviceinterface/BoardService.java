package sejong.europlanner.service.serviceinterface;

import sejong.europlanner.dto.BoardDto;
import sejong.europlanner.vo.request.board.RequestCreateBoard;
import sejong.europlanner.vo.request.board.RequestUpdateBoard;
import sejong.europlanner.vo.response.board.ResponseDeleteBoard;

import java.util.List;

public interface BoardService {
    List<BoardDto> getBoardList();

    BoardDto createBoard(RequestCreateBoard requestCreateBoard);

    BoardDto getBoardById(Long boardId);

    BoardDto updateBoard(RequestUpdateBoard requestUpdateBoard);

    ResponseDeleteBoard deleteBoard(Long boardId);
}
