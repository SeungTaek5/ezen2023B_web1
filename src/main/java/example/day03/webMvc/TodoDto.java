package example.day03.webMvc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 데이터 이동 객체    : 여러 데이터를 하나의 타입으로 묶어주는 역할.
// 한개              : TodoDto         vs   Map<String,String>
// 여러개 TodoDto    : List<TodoDto>   vs  List< Map<String,String> >
// member + todo    : todoMemberDto   vs  Map<String,String>
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class TodoDto {
    // 1. 필드 ( dto로 사용할 경우 db table 필드와 일치하고 추가적으로 추가가능 )
    private int id;
    private String content;
    private String deadline;
    private boolean state;
    // 2. 생성자 ( dto로 사용할경우 기본생성자 , 풀생성자 )

    // 3. 메소드 ( dto로 사용할경우 get/set 메소드 , toStrirng() 메소드 )

}