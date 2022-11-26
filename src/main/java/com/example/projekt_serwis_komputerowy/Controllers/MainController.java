package com.example.projekt_serwis_komputerowy.Controllers;

import com.example.projekt_serwis_komputerowy.GetSet.Uslugi;
import com.example.projekt_serwis_komputerowy.Mappers.UslugiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@org.springframework.stereotype.Controller
public class MainController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String ind(HttpSession httpSession, Model mod) {
        if(httpSession.getAttribute("Login")!=null) {
            String IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);
        }

        return "index";
    }

    @GetMapping("/index")
    public String index(HttpSession httpSession, Model mod) {
        if(httpSession.getAttribute("Login")!=null) {
            String IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);
        }
        return "index";
    }


    @GetMapping("/Cennik")
    public String Cennik(HttpSession httpSession, Model mod) {
        if(httpSession.getAttribute("Login")!=null) {
            String IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);

        }

        String SQL = "select * from uslugi";
        List<Uslugi> uslugi = jdbcTemplate.query(SQL, new UslugiMapper());
        mod.addAttribute("uslugi",uslugi);

        return "Cennik";
    }

    @GetMapping("/Onas")
    public String Onas(HttpSession httpSession, Model mod) {
        if(httpSession.getAttribute("Login")!=null) {
            String IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);
        }

        return "Onas";
    }
    @GetMapping("/Kontakt")
    public String Kontakt(HttpSession httpSession, Model mod) {
        if(httpSession.getAttribute("Login")!=null) {
            String IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);
        }

        return "Kontakt";
    }

    @GetMapping("/Zamow")
    public String Zamow(HttpSession httpSession, Model mod) {
        if(httpSession.getAttribute("Login")!=null) {
            String IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);
            if (RolaSession.equals("admin") || RolaSession.equals("user")) {
                String SQL = "select * from uslugi";
                List<Uslugi> uslugi = jdbcTemplate.query(SQL, new UslugiMapper());
                mod.addAttribute("uslugi",uslugi);
                return "Zamow";
            }
        }
        return"Login";
    }


}

