package sejong.europlanner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sejong.europlanner.dto.BoardDto;
import sejong.europlanner.global.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public static BoardEntity boardEntity(BoardDto boardDto, UserEntity user) {
        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setTitle(boardDto.getTitle());
        boardEntity.setContent(boardDto.getContent());
        boardEntity.setUser(user);

        return boardEntity;
    }
}
