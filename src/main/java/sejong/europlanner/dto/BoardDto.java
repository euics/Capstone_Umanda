package sejong.europlanner.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
