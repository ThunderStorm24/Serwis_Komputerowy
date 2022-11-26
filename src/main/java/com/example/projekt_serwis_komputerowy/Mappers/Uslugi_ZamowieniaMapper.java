package com.example.projekt_serwis_komputerowy.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.projekt_serwis_komputerowy.GetSet.Uslugi_Zamowienia;
import org.springframework.jdbc.core.RowMapper;

public class Uslugi_ZamowieniaMapper implements RowMapper<Uslugi_Zamowienia> {
    public Uslugi_Zamowienia mapRow(ResultSet rs, int rowNum) throws SQLException {
        Uslugi_Zamowienia uslugi_zamowienia = new Uslugi_Zamowienia();
        uslugi_zamowienia.setNr_zamowienia(rs.getInt("NR_Zamowienia" ));
        uslugi_zamowienia.setId_uslugi(rs.getInt("ID_Uslugi" ));
        uslugi_zamowienia.setKoszt(rs.getInt("Koszt" ));
        uslugi_zamowienia.setOpis(rs.getString("Opis" ));
        return uslugi_zamowienia;
    }
}
