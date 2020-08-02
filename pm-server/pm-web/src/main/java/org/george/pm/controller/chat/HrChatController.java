package org.george.pm.controller.chat;

import org.george.pm.model.Hr;
import org.george.pm.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class HrChatController {
    @Autowired
    private HrService hrService;

    @GetMapping("/")
    public List<Hr> getAllHrs(){
        List<Hr> hrs = hrService.getAllHrWithoutCurrentHr();
        return hrs;
    }
}
