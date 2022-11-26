package com.example.projekt_serwis_komputerowy.Controllers;

import com.example.projekt_serwis_komputerowy.GetSet.Uzytkownicy;
import com.example.projekt_serwis_komputerowy.Mappers.UzytkownicyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@org.springframework.stereotype.Controller
public class LoginController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/Signin")
    public String Signin(HttpSession httpSession, @RequestParam(value = "Login", defaultValue="Visitor") String Login, @RequestParam(value = "Password", defaultValue="Visitor") String Password, Model mod) {

        String SQL = "select * from uzytkownicy";
        List<Uzytkownicy> uzytkownik = jdbcTemplate.query(SQL, new UzytkownicyMapper());
        String LogIn="";
        String PassIn="";
        String Role="";
        Integer ID_U=0;
        for(Uzytkownicy record : uzytkownik){
            if(Login.equals(record.getLogin()) && Password.equals(record.getPassword())){
                ID_U = record.getId();
                LogIn = record.getLogin();
                Role = record.getRole();
                PassIn = record.getPassword();
            }
        }
        if (Login.equals(LogIn) && Password.equals(PassIn)) {
            httpSession.setAttribute("ID", ID_U);
            httpSession.setAttribute("Login", Login);
            httpSession.setAttribute("Rola", Role);
            return "redirect:index";
        } else {
            mod.addAttribute("LoginV",Login);
            mod.addAttribute("Message","Błędny login lub hasło!!! Spróbuj ponownie!!!");
            return "Login";
        }
    }

    @GetMapping("/Logout")
    public String Logout(HttpSession httpSession) {
        httpSession.removeAttribute("ID");
        httpSession.removeAttribute("Login");
        httpSession.removeAttribute("Rola");
        return "index";
    }

    @GetMapping("/Login")
    public String Login(HttpSession httpSession, Model mod) {
        return "Login";
    }

    @PostMapping("/Registerin")
    public String Registerin(HttpSession httpSession,
                             @RequestParam(value = "EDYCJA", defaultValue="0") String EDYCJA,
                             @RequestParam(value = "ID_U", defaultValue = "0") Integer ID_U,
                             @RequestParam(value = "Rola", defaultValue = "brak") String Rola,
                             @RequestParam(value = "Login") String Login,
                             @RequestParam(value = "Password") String Password,
                             @RequestParam(value = "rePassword") String rePassword,
                             @RequestParam(value = "Imie") String Imie,
                             @RequestParam(value = "Nazwisko") String Nazwisko,
                             @RequestParam(value = "Miasto") String Miasto,
                             @RequestParam(value = "Adres") String Adres,
                             @RequestParam(value = "Telefon") String Telefon,
                             Model mod){
        if(EDYCJA.equals("1")){
            String SQL = "select * from uzytkownicy";
            List<Uzytkownicy> uzytkownik = jdbcTemplate.query(SQL, new UzytkownicyMapper());
            mod.addAttribute("uzytkownicy",uzytkownik);
            mod.addAttribute("ID_U",ID_U);
        }
        int i=0;
        int j=0;
        if(Rola.equals("admin") || Rola.equals("user")){mod.addAttribute("Rola",Rola);j++;}
        if(Login.matches("^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{3,40}$")) {mod.addAttribute("Login",Login);i++;} else {mod.addAttribute("Message1","Login nie powinien zawierać specjalnych znaków oraz mieć długość 3-40!");}
        if(Password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,40}$")) {mod.addAttribute("Password",Password);i++;} else {mod.addAttribute("Message2","Hasło musi się składać od 8 do 40 znaków, conajmniej jedna litera i conajmniej jedna liczba!");}
        if(rePassword.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,40}$")){mod.addAttribute("rePassword",rePassword);i++;} else {mod.addAttribute("Message3","Hasło musi się składać od 8 do 40 znaków, conajmniej jedna litera i conajmniej jedna liczba!");}
        if(Imie.matches("[A-Z][a-z]{3,20}")) {mod.addAttribute("Imie",Imie);i++;} else {mod.addAttribute("Message4","Imie nie powinno zawierać znaków specjalnych i być w długości od 3-20!");}
        if(Nazwisko.matches("[A-Z][a-z]{3,30}")) {mod.addAttribute("Nazwisko",Nazwisko);i++;} else {mod.addAttribute("Message5","Nazwisko nie powinno zawierać znaków specjalnych i być w długości od 3-30!");}
        if(Miasto.matches("[A-Za-z\s]{3,40}+")) {mod.addAttribute("Miasto",Miasto);i++;} else {mod.addAttribute("Message6","Miasto nie powinno zawierać znaków specjalnych i być w długości od 3-30!");}
        if(Adres.matches("[A-Za-z \\d \\/]{3,40}+")) {mod.addAttribute("Adres",Adres);i++;} else {mod.addAttribute("Message7","Adres nie powinien zawierać znaków specjalnych i być w długości 3-40!");}
        if(Telefon.matches("^\\+48\\d{9}$")) {mod.addAttribute("Telefon",Telefon);i++;} else {mod.addAttribute("Message8","Telefon powinien być zapisany w postaci +48123456789!");}
        if(Password.equals(rePassword)) {i++;} else {mod.addAttribute("Message9","Hasła nie zgadzają się!");}

        if(EDYCJA.equals("1")){
            if(j==1 && i==9){
                String query = "UPDATE uzytkownicy set Rola=?,Imie=?,Nazwisko=?,Miasto=?,Adres=?,Telefon=?,Login=?,Password=? where ID_Uzytkownika=?";
                jdbcTemplate.update(query, Rola, Imie, Nazwisko, Miasto, Adres, Telefon, Login, Password, ID_U);
                return "redirect:Profil";
            } else {
                return "Edycja";
            }
        }

        String SQL = "select * from uzytkownicy";
        List<Uzytkownicy> uzytkownik = jdbcTemplate.query(SQL, new UzytkownicyMapper());
        for(Uzytkownicy record : uzytkownik){
            if(Login.equals(record.getLogin())){
                mod.addAttribute("Message10","Użytkownik: "+Login+" znajduje się w bazie danych!");
                mod.addAttribute("Login","");
                if(EDYCJA.equals("1")){return "Edycja";}
                if(j==1){return "Dodaj";}
                return "Rejestracja";
            }
            if(Telefon.equals(record.getPhone())){
                mod.addAttribute("Message11","Telefon: "+Telefon+" już znajduje się w naszej bazie danych!");
                mod.addAttribute("Telefon","");
                if(EDYCJA.equals("1")){return "Edycja";}
                if(j==1){return "Dodaj";}
                return "Rejestracja";
            }
        }

        if(j==1){
        if(i==9) {
            String query = "INSERT INTO uzytkownicy(Rola,Imie,Nazwisko,Miasto,Adres,Telefon,Login,Password) values(?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(query, Rola, Imie, Nazwisko, Miasto, Adres, Telefon, Login, Password);
            return "redirect:Profil";

        } else {
            return "Dodaj";
        }
        }

        if(i==9) {
            String query="INSERT INTO uzytkownicy(Rola,Imie,Nazwisko,Miasto,Adres,Telefon,Login,Password) values(?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(query,"user",Imie,Nazwisko,Miasto,Adres,Telefon,Login,Password);
            return "Login";
        }else{
            return "Rejestracja";
        }
    }

    @GetMapping("/Rejestracja")
    public String Rejestracja(HttpSession httpSession, Model mod) {
        return "Rejestracja";
    }
}
