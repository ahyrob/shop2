package clothes.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class CustomerCenterController {

    @RequestMapping("/customercenter/customercenter")
    public String customercenter() {
        log.info("customercenter controller");
        return "customercenter/customercenter";
    }
}
