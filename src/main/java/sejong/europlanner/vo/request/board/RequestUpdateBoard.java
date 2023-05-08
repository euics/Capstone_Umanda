package sejong.europlanner.vo.request.board;

import lombok.Data;

@Data
public class RequestUpdateBoard {
    private Long id;
    private String title;
    private String content;
}
