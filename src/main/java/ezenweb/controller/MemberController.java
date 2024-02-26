package ezenweb.controller;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



// 1단계. V<---->C 사이의 HTTP 통신 방식 설계
// 2단계. Controller mapping 함수 선언 하고 통신 체크 ( API Tester )
// 3단계. Controller request 매개변수 매핑
// -------------- Dto , Service ---------------//
// 4단계. 응답 : 1.뷰 반환 : text/html;  VS  2. 데이터/값 : @ResponseBody : Application/JSON


@Controller
public class MemberController {

    // *Http요청 객체
    @Autowired
    private HttpServletRequest request;
    @Autowired
    MemberDao memberDao;


    // 1.=========== 회원가입 처리 요청 ===============
    @PostMapping("/member/signup") // http://localhost:80/member/signup
    @ResponseBody // 응답 방식 application/json;
    public boolean doPostSignup( MemberDto memberDto ){
        /* params  {   id ="아이디" , pw ="비밀번호" , name="이름" ,   email="이메일" , phone="전화번호" , img ="프로필사진"   }  */
        System.out.println("MemberController.signup");
        System.out.println("memberDto = " + memberDto);
        // --
        boolean result = memberDao.doPostSignup(memberDto);//Dao처리;
        return result; // Dao 요청후 응답 결과를 보내기.
    }


    // 2. =========== 로그인 처리 요청 ===============
    @PostMapping("/member/login") // http://localhost:80/member/login
    @ResponseBody  // 응답 방식 application/json;
    public boolean doPostLogin( LoginDto loginDto ){
        /* params    { id ="아이디" , pw ="비밀번호"  }   */
        System.out.println("MemberController.login");
        System.out.println("loginDto = " + loginDto);

        // 로그인 성공시
            // 세션 저장소 : 톰캣서버에 *브라우저 마다의 메모리 할당
            // 세션 객체 타입 : Object( 여러가지의 타입들을 저장하려고 )

            // 1. Http요청 객체 호출. HttpServletRequest
            // 2. Http세션 객체 호출. .getSession()
            // 3. Http세션 데이터 저장. .setAttribute("세션속성명", "데이터");   --자동형변환
            // 4. Http세션 데이터 호출. .getAttribute("세션속성명")             --강제형변환

        boolean result = memberDao.doPostLogin(loginDto); // Dao처리

        if(result){
            request.getSession().setAttribute("loginDto", loginDto.getId());
        }

        return result; // Dao 요청후 응답 결과를 보내기
    }

    // 2-2. =========== 로그인 여부 확인 요청 / 세션 호출 ===============
    @GetMapping("/member/login/check")
    @ResponseBody
    public String doGetLoginCheck(){
        // 로그인 여부 확인 = 세션이 있다 없다 확인
            // 1-> http 요청 객체 호출, 2->http세션 객체 호출, 3->http 세션 데이터 호출
        String loginDto = null;
        Object sessionObj = request.getSession().getAttribute("loginDto");
        if( sessionObj !=null ){
            loginDto = (String)sessionObj;
        }
        return loginDto;
    }

    // 2-2. =========== 로그인 로그아웃 / 세션 초기화 ===============
    @GetMapping("/member/logout")
    @ResponseBody
    public boolean deGetLoginOut(){

        // 1. 모든 세션 초기화
        request.getSession().invalidate(); // 현재 요청 보낸 브라우저의 모든 세션 초기화
        // 2. 특정 세션속성 초기화
        // request.getSession().setAttribute("loginDto", null);

        return true;
    }

    // 3. =========== 회원가입 페이지 요청 ===============
    @GetMapping("/member/signup")
    public String viewSignup(){
        System.out.println("MemberController.viewSignup");
        return "ezenweb/signup";
    }
    // 4. =========== 로그인 페이지 요청 ===============
    @GetMapping("/member/login")
    public String viewLogin(){
        System.out.println("MemberController.viewLogin");
        return "ezenweb/login";
    }
}