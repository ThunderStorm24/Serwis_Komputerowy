package com.example.projekt_serwis_komputerowy.Controllers;

import com.example.projekt_serwis_komputerowy.GetSet.Uslugi;
import com.example.projekt_serwis_komputerowy.Mappers.UslugiMapper;
import com.example.projekt_serwis_komputerowy.GetSet.Zamowienia;
import com.example.projekt_serwis_komputerowy.Mappers.ZamowieniaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ZamawianieController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping ("/ZamowUsluge")
    public String ZamowUsluge(HttpSession httpSession,
                              @RequestParam(value = "ID_Uslugi", defaultValue = "0") Integer ID_Uslugi,
                              Model mod) {
        if (httpSession.getAttribute("Login") != null) {
            String IdSession = httpSession.getAttribute("ID").toString();
            String txIdSession = httpSession.getAttribute("Login").toString();
            String RolaSession = httpSession.getAttribute("Rola").toString();
            mod.addAttribute("IDD", IdSession);
            mod.addAttribute("Loginn", txIdSession);
            mod.addAttribute("Rolaa", RolaSession);
        }

        if(ID_Uslugi==0){
            mod.addAttribute("Message","Cos nie tak z ID");
            return "redirect:profil";
        }

        String ID_S = httpSession.getAttribute("ID").toString();
        int ID_SesjaKlient = Integer.parseInt(ID_S);

        System.out.println(ID_Uslugi);
        String SQL = "select * from uslugi";
        List<Uslugi> uslugi = jdbcTemplate.query(SQL, new UslugiMapper());
        mod.addAttribute("uslugi",uslugi);

        String name="";
        int price=0;
        for(Uslugi record : uslugi){
            if(ID_Uslugi==record.getId()){
                int id=record.getId();
                name = record.getName();
                String desc = record.getDesc();
                price = record.getPrice();
            }
        }
        LocalDate date = LocalDate.now();
        String Date = date.toString();

        String SQL1 = "select * from zamowienia";
        List<Zamowienia> zamowienia = jdbcTemplate.query(SQL1, new ZamowieniaMapper());
        for(Zamowienia record : zamowienia){
            if(Date.equals(record.getData_zamowienia()) && ID_SesjaKlient==record.getId_klienta()){
                mod.addAttribute("Message","NIE MOZESZ ZLOZYC DWOCH ZAMOWIEN JEDNEGO DNIA!!!");
                return "Zamow";
            }
        }

        String query="INSERT INTO zamowienia(Stan_Realizacji,Data_Zamowienia,Data_Realizacji,Opis,ID_Klienta) values(?,?,?,?,?)";
        jdbcTemplate.update(query,"W trakcie",date,date.plusDays(7),"Zamowienie zostało dodane do bazy i zaraz się nim zajmiemy!",ID_SesjaKlient);

        String SQL3 = "select * from zamowienia";
        List<Zamowienia> zamowienia1 = jdbcTemplate.query(SQL3, new ZamowieniaMapper());
        Integer NR_Zamowienia=0;
        for(Zamowienia record : zamowienia1){
            if(Date.equals(record.getData_zamowienia()) && ID_SesjaKlient==record.getId_klienta()){
                NR_Zamowienia = record.getNr_zamowienia();
            }
        }

        if(NR_Zamowienia==0){
            mod.addAttribute("Message1","COS POSZLO NIE TAK!");
            return "Zamow";
        }

        String query1="INSERT INTO uslugi_zamowienia(ID_Uslugi,NR_Zamowienia,Koszt,Opis) values(?,?,?,?)";
        jdbcTemplate.update(query1,ID_Uslugi,NR_Zamowienia,price,name);


        return "redirect:Profil";
    }

}
