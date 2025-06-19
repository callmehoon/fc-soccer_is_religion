package toyproject.SON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class List_Controller {

    @RequestMapping("/list")
    private void main(){
        System.out.println("list");
    }
}
