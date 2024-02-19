package book.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@ToString
public class ArticleForm {
    private long id;
    private String title;
    private String content;
}