package com.example.projekt_serwis_komputerowy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@org.springframework.stereotype.Controller

public class UslugiController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/edytuj_usluge")
    public String EdytujUsluge(HttpSession httpSession,
                               @RequestParam(value = "ID") Integer ID,
                               Model mod) {
        if (httpSession.getAttribute("Login") != null) {
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
        mod.addAttribute("ID",ID);

        return "Edytuj_Usluge";
    }

    @GetMapping("/usun_usluge")
    public String UsunUsluge(HttpSession httpSession,
                             @RequestParam(value = "ID") Integer ID,
                             Model mod) {
        if (httpSession.getAttribute("Login") != null) {
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

        String query = "DELETE FROM uslugi where ID_Uslugi=?";
        jdbcTemplate.update(query, ID);

        return "redirect:Cennik";
    }

    @GetMapping("/dodaj_usluge")
    public String DodajUsluge(HttpSession httpSession, Model mod) {
        if (httpSession.getAttribute("Login") != null) {
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

        return "Dodaj_Usluge";
    }

    @PostMapping("/Uslugii")
    public String Uslugii(HttpSession httpSession,
            @RequestParam(value = "EDYCJA", defaultValue="0") String EDYCJA,
            @RequestParam(value = "ID", defaultValue = "0") Integer ID,
            @RequestParam (value = "name") String name,
            @RequestParam (value = "desc") String desc,
            @RequestParam (value = "price", defaultValue = "0") Integer price,
            Model mod) {
        if (httpSession.getAttribute("Login") != null) {
            String IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);
        }

        if(EDYCJA.equals("1")){
            String SQL = "select * from uslugi";
            List<Uslugi> uslugi = jdbcTemplate.query(SQL, new UslugiMapper());
            mod.addAttribute("uslugi",uslugi);
            mod.addAttribute("ID",ID);
        }

        int i=0;
        if(name.matches("[A-Za-z\s]{3,85}+")) {mod.addAttribute("name",name);i++;} else {mod.addAttribute("Message1","Nazwa Usługi nie powinno zawierać znaków specjalnych i być w długości od 3-85!");}
        if(desc.matches("^(.|\\s)*[a-zA-Z]+(.|\\s)*$")) {mod.addAttribute("desc",desc);i++;} else {mod.addAttribute("Message2","W opisie są błędy!!!");}
        if(price>-1 && price<10001) {mod.addAttribute("price",price);i++;} else {mod.addAttribute("Message3","Cene można podawać w zakresie od 0 do 10 000 zł!!!");}

        if(EDYCJA.equals("1")) {
            if (i == 3) {
                String query = "UPDATE uslugi set Nazwa_Uslugi=?,Cena=?,Opis=? where ID_Uslugi=?";
                jdbcTemplate.update(query, name, price, desc, ID);
                return "redirect:Cennik";
            } else {
                return "Edytuj_Usluge";
            }
        }

        String SQL = "select * from uslugi";
        List<Uslugi> uslugi = jdbcTemplate.query(SQL, new UslugiMapper());
        mod.addAttribute("uslugi",uslugi);

        if(i==3) {
            String query="INSERT INTO uslugi(Nazwa_Uslugi,Cena,Opis) values(?,?,?)";
            jdbcTemplate.update(query,name,price,desc);
            return "redirect:Cennik";
        }

        return "Dodaj_Usluge";
    }
}

