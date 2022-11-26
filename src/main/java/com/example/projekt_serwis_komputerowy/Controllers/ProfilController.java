package com.example.projekt_serwis_komputerowy.Controllers;

import com.example.projekt_serwis_komputerowy.GetSet.Uslugi_Zamowienia;
import com.example.projekt_serwis_komputerowy.GetSet.Uzytkownicy;
import com.example.projekt_serwis_komputerowy.GetSet.Zamowienia;
import com.example.projekt_serwis_komputerowy.Mappers.Uslugi_ZamowieniaMapper;
import com.example.projekt_serwis_komputerowy.Mappers.UzytkownicyMapper;
import com.example.projekt_serwis_komputerowy.Mappers.ZamowieniaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;

import java.util.List;

@org.springframework.stereotype.Controller
public class ProfilController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/Profil")
    public String Profil(HttpSession httpSession, @RequestParam(value = "ID_U", defaultValue = "0") Integer ID_U, Model mod) {

        if (httpSession.getAttribute("Login") != null) {
            String IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);
        }
        int min = 0;
        int max = 11;
        httpSession.setAttribute("max", max);
        httpSession.setAttribute("min", min);

        String SQL = "select * from uzytkownicy";
        List<Uzytkownicy> uzytkownik = jdbcTemplate.query(SQL, new UzytkownicyMapper());
        mod.addAttribute("uzytkownicy", uzytkownik);
        mod.addAttribute("max", max);
        mod.addAttribute("min", min);

        for (Uzytkownicy record : uzytkownik) {
            if (ID_U == record.getId()) {
                mod.addAttribute("Znajdz", ID_U);
            } else {
                mod.addAttribute("Message", "Nie ma użytkownika o ID: " + ID_U);
            }
        }

        String SQL1 = "select * from zamowienia";
        List<Zamowienia> zamowienia = jdbcTemplate.query(SQL1, new ZamowieniaMapper());
        mod.addAttribute("zamowienia", zamowienia);

        String SQL2 = "select * from uslugi_zamowienia";
        List<Uslugi_Zamowienia> uslugi_zamowienia = jdbcTemplate.query(SQL2, new Uslugi_ZamowieniaMapper());
        mod.addAttribute("uslugi_zamowienia", uslugi_zamowienia);


        return "Profil";
    }
    @GetMapping("/Profild")
    public String Profild(HttpSession httpSession, Model mod) {
        if(httpSession.getAttribute("Login")!=null) {
            String IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);
        }
        String low=httpSession.getAttribute("max").toString();
        String lowm=httpSession.getAttribute("min").toString();
        int max = Integer.parseInt(low);
        int min = Integer.parseInt(lowm);

        String SQL = "select * from uzytkownicy";
        List<Uzytkownicy> uzytkownik = jdbcTemplate.query(SQL, new UzytkownicyMapper());
        int LastID=0;
        for(Uzytkownicy record : uzytkownik){
            LastID=record.getId();
        }
        if(max<LastID){
            min=min+10;
            max=max+10;
        }
        httpSession.setAttribute("max",max);
        httpSession.setAttribute("min",min);

        mod.addAttribute("uzytkownicy",uzytkownik);
        mod.addAttribute("max",max);
        mod.addAttribute("min",min);

        String SQL1 = "select * from zamowienia";
        List<Zamowienia> zamowienia = jdbcTemplate.query(SQL1, new ZamowieniaMapper());
        mod.addAttribute("zamowienia", zamowienia);

        String SQL2 = "select * from uslugi_zamowienia";
        List<Uslugi_Zamowienia> uslugi_zamowienia = jdbcTemplate.query(SQL2, new Uslugi_ZamowieniaMapper());
        mod.addAttribute("uslugi_zamowienia", uslugi_zamowienia);

        return "Profil";
    }
    @GetMapping("/Profilo")
    public String Profilo(HttpSession httpSession, Model mod) {
        if(httpSession.getAttribute("Login")!=null) {
            String IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);
        }
        String low=httpSession.getAttribute("max").toString();
        String lowm=httpSession.getAttribute("min").toString();
        int max = Integer.parseInt(low);
        int min = Integer.parseInt(lowm);
        min=min-10;
        max=max-10;
        if(min<0){
            min=0;
            max=11;
        }
        httpSession.setAttribute("max",max);
        httpSession.setAttribute("min",min);

        String SQL = "select * from uzytkownicy";
        List<Uzytkownicy> uzytkownik = jdbcTemplate.query(SQL, new UzytkownicyMapper());
        mod.addAttribute("uzytkownicy",uzytkownik);
        mod.addAttribute("max",max);
        mod.addAttribute("min",min);

        String SQL1 = "select * from zamowienia";
        List<Zamowienia> zamowienia = jdbcTemplate.query(SQL1, new ZamowieniaMapper());
        mod.addAttribute("zamowienia", zamowienia);

        String SQL2 = "select * from uslugi_zamowienia";
        List<Uslugi_Zamowienia> uslugi_zamowienia = jdbcTemplate.query(SQL2, new Uslugi_ZamowieniaMapper());
        mod.addAttribute("uslugi_zamowienia", uslugi_zamowienia);

        return "Profil";
    }

}
