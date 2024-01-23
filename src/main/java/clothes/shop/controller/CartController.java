package clothes.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class CartController {
    @RequestMapping("/cart/cart") // https
    public String cart() {
        log.info("cart controller");
        return "cart/cart";
    }
}
