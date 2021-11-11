package chap11.survey;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    @PostMapping
    public String submit(@ModelAttribute("ansData") AnsweredData data) {
        return "survey/submitted";
    }

    @GetMapping
    public ModelAndView form() {
        List<Question> questions = createQuestions();
        ModelAndView mav = new ModelAndView();
        mav.addObject("questions", questions);
        mav.setViewName("survey/surveyForm");

        return mav;
    }

    private List<Question> createQuestions() {
        Question question1 = new Question("당신의 역할은 무엇입니까?", Arrays.asList("서버", "프론트", "풀스택"));
        Question question2 = new Question("많이 사용하는 개발 도구는?", Arrays.asList("VSCode", "Intellij", "이클립스"));
        Question question3 = new Question("하고 싶은 말은?");

        return Arrays.asList(question1, question2, question3);
    }
}
