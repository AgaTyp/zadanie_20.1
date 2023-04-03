package pl.agntyp.zadanie20_1;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

@Controller
public class UsersController implements ErrorController {

    private UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
        User user = new User("Marek","Wilk",30);
        User user1 = new User("Zofia","Kowalska",27);
        User user2 = new User("Wojciech","Mak",36);
        userRepository.add(user);
        userRepository.add(user1);
        userRepository.add(user2);
    }

    @RequestMapping("/add")
    public String users(@RequestParam(name = "imie") String firstName,
                        @RequestParam(name = "nazwisko", required = false, defaultValue = "") String lastName,
                        @RequestParam(name = "wiek", required = false, defaultValue = "0") Integer age) {

        if (Objects.equals(firstName, "")) {
            return "redirect:/error";
        } else {
            User user = new User(firstName,lastName,age);
            userRepository.add(user);

            return "redirect:/success.html";
        }
    }

    @RequestMapping("/error")
    public String users(HttpServletRequest request) {
        return "redirect:/err.html";
    }

    @ResponseBody
    @RequestMapping("/users")
    public String users() {

        List<User> userList = userRepository.getAll();
        StringBuilder result = new StringBuilder();
        for (User user1 : userList) {
            result.append(user1.getFirstName()).append(" ").append(user1.getLastName()).append(" ").append(user1.getAge()).append("<br/>");
        }
        return result.toString();
    }
}
