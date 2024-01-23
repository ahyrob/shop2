package clothes.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class ShopController {
    @RequestMapping("/shop/shop")
    public String home() {
        log.info("shop controller");
        return "shop/shop";
    }
}
