package example.day01.webMvc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 스프링부트 주입
public class AppStart {//class start

    public static void main(String[] args) {
        // ** 스프링 시작
        SpringApplication.run(AppStart.class);
        // http://localhost:8080

    }
}
