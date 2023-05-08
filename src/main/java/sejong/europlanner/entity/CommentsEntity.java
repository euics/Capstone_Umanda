package sejong.europlanner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sejong.europlanner.dto.CommentsDto;
import sejong.europlanner.global.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    private Long id;

    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public static CommentsEntity commentsEntity(CommentsDto commentDto, BoardEntity board, UserEntity user) {
        CommentsEntity commentEntity = new CommentsEntity();
        commentEntity.setComments(commentDto.getComments());
        commentEntity.setBoard(board);
        commentEntity.setUser(user);
        commentEntity.setCreatedBy(user.getUsername());
        commentEntity.setModifiedBy(user.getUsername());

        return commentEntity;
    }
}
