package lab.space.vilki_palki_rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Vilki_Palki_Api")
public class IndexController {
    @GetMapping({"/", ""})
    public String redirectAdmin() {
        return "redirect:/Vilki_Palki_Api/swagger-ui/index.html#/";
    }
}
