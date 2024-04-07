package helmes.proovitoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {

    private static final String HOME_VIEW = "home";



    @GetMapping("/home")
    public String loadHomePage() {
        return HOME_VIEW;
    }
}
