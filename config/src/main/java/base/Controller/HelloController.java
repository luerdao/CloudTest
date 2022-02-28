package base.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope

public class HelloController {

    @Value("${user.name}")
    private String username;

    @GetMapping("/hello/config")
    public String config(){
        System.out.println("用户名："+username);
        return username;
    }

    @GetMapping("/test")
    @HystrixCommand(fallbackMethod = "testBreakFall")
    public String test(int id){
        System.out.println("进入test接口,id =" + id);
        if (id < 0){
            throw new RuntimeException("id<0 不合法");
        }
        return "当前接受商品Id:"+ id;
    }

    public String testBreakFall(int id){
        return "当前数据不合法" + id;
    }
}
