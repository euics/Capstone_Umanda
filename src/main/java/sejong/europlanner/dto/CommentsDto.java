package sejong.europlanner.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentsDto {
    private Long id;

    private String comments;

    private String createdBy;

    private String modifiedBy;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
