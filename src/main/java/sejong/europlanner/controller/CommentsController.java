package sejong.europlanner.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sejong.europlanner.dto.CommentsDto;
import sejong.europlanner.service.serviceinterface.CommentsService;
import sejong.europlanner.vo.response.comments.ResponseGetComments;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CommentsController {
    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/comments/read/{boardId}")
    public ResponseEntity<List<ResponseGetComments>> getComments(@PathVariable Long boardId){
        List<CommentsDto> commentsDtoList = commentsService.getCommentsList(boardId);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<ResponseGetComments> responseGetCommentsList = new ArrayList<>();

        for(CommentsDto cd : commentsDtoList){
            ResponseGetComments responseGetComments = mapper.map(cd, ResponseGetComments.class);
            responseGetComments.setCommentsId(cd.getId());
            responseGetCommentsList.add(responseGetComments);
        }

        return ResponseEntity.ok().body(responseGetCommentsList);
    }
}
