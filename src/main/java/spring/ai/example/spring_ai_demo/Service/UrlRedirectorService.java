package spring.ai.example.spring_ai_demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class UrlRedirectorService {

    public String redirector(@RequestHeader(value = "Referer", required = false) String referer, int segment) {
        if (referer != null && !referer.contains("?")) {
            return "redirect:" + referer;
        } else {
            String[] segments = referer.split("/");
            return "redirect:/" + segments[segment] + "/";
        }

    }
}
