package si.um.feri.WiseWallet.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uporabniki")
public class UporabnikController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
