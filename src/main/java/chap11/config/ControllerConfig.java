package chap11.config;

import chap11.MemberRegisterService;
import chap11.controller.HelloController;
import chap11.controller.RegisterController;
import chap11.survey.SurveyController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ControllerConfig {

    @Autowired
    private MemberRegisterService memberRegisterService;

    @Bean
    public HelloController helloController() {
        log.info("컨트롤러 빈 등록!!!!");
        return new HelloController();
    }

    @Bean
    public RegisterController registerController() {
        return new RegisterController(memberRegisterService);
    }

    @Bean
    public SurveyController surveyController() {
        return new SurveyController();
    }
}
