package sejong.europlanner.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejong.europlanner.dto.BoardDto;
import sejong.europlanner.service.serviceinterface.BoardService;
import sejong.europlanner.vo.response.board.ResponseGetBoard;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boards/read")
    public ResponseEntity<List<ResponseGetBoard>> getBoardList(){
        List<BoardDto> boardDtoList = boardService.getBoardList();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<ResponseGetBoard> responseGetBoardList = new ArrayList<>();
        for(BoardDto bd : boardDtoList){
            ResponseGetBoard mappedResponseGetBoard = mapper.map(bd, ResponseGetBoard.class);
            responseGetBoardList.add(mappedResponseGetBoard);
        }

        return ResponseEntity.ok().body(responseGetBoardList);
    }
}
