package base.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${user.name}")
    private String username;

    @GetMapping("/hello/config")
    public String config(){
        System.out.println("用户名："+username);
        return username;
    }
}
