package pl.agntyp.zadanie20;

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

    }

    @RequestMapping("/add")
    public String addUsers(@RequestParam(name = "imie") String firstName,
                           @RequestParam(name = "nazwisko", defaultValue = "") String lastName,
                           @RequestParam(name = "wiek", defaultValue = "0") Integer age) {

        if (Objects.equals(firstName, "") || firstName == null) {
            return "redirect:/error";
        } else {
            User user = new User(firstName, lastName, age);
            userRepository.add(user);

            return "redirect:/success.html";
        }
    }

    @RequestMapping("/error")
    public String error() {
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
