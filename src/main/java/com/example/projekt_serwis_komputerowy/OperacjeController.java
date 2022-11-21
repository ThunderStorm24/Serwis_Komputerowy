package com.example.projekt_serwis_komputerowy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@org.springframework.stereotype.Controller
public class OperacjeController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/Dodajj")
    public String Dodajj(HttpSession httpSession, Model mod) {
        if (httpSession.getAttribute("Login") != null) {
            String IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);
        }

        String SQL = "select * from uzytkownicy";
        List<Uzytkownicy> uzytkownik = jdbcTemplate.query(SQL, new UzytkownicyMapper());
        mod.addAttribute("uzytkownicy",uzytkownik);

        return "Dodaj";
    }

    @GetMapping("/Delete")
    public String Delete(HttpSession httpSession, @RequestParam(value = "ID_U") Integer ID_U, Model mod) {
        String IdSession = null;
        if (httpSession.getAttribute("Login") != null) {
            IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);
        }
        String ID = ID_U.toString();
        if (IdSession.equals(ID) || ID.equals("1")) {
            return "redirect:Profil";
        }
            String query = "DELETE FROM uzytkownicy where ID_Uzytkownika=?";
            jdbcTemplate.update(query, ID_U);

        return "redirect:Profil";

    }

    @GetMapping("/Edit")
    public String Edycja(HttpSession httpSession, @RequestParam(value = "ID_U") Integer ID_U, Model mod) {
        if (httpSession.getAttribute("Login") != null) {
            String IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);
        }

        String SQL = "select * from uzytkownicy";
        List<Uzytkownicy> uzytkownik = jdbcTemplate.query(SQL, new UzytkownicyMapper());
        mod.addAttribute("uzytkownicy",uzytkownik);
        mod.addAttribute("ID_U",ID_U);

        return "Edycja";
    }

}
