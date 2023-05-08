package sejong.europlanner.vo.request.board;

import lombok.Data;

@Data
public class RequestCreateBoard {
    private Long userId;
    private String title;
    private String content;
}
