package com.example.projekt_serwis_komputerowy.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.projekt_serwis_komputerowy.GetSet.Uzytkownicy;
import org.springframework.jdbc.core.RowMapper;

public class UzytkownicyMapper implements RowMapper<Uzytkownicy> {
    public Uzytkownicy mapRow(ResultSet rs, int rowNum) throws SQLException {
        Uzytkownicy uzytkownik = new Uzytkownicy();
        uzytkownik.setId(rs.getInt("ID_Uzytkownika" ));
        uzytkownik.setRole(rs.getString("Rola" ));
        uzytkownik.setName(rs.getString("Imie" ));
        uzytkownik.setSurname(rs.getString("Nazwisko" ));
        uzytkownik.setCity(rs.getString("Miasto" ));
        uzytkownik.setAdres(rs.getString("Adres" ));
        uzytkownik.setPhone(rs.getString("Telefon" ));
        uzytkownik.setLogin(rs.getString("Login" ));
        uzytkownik.setPassword(rs.getString("Password" ));
        return uzytkownik;
    }
}
