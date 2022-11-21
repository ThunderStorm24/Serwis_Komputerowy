package com.example.projekt_serwis_komputerowy;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ZamowieniaMapper implements RowMapper<Zamowienia> {
    public Zamowienia mapRow(ResultSet rs, int rowNum) throws SQLException {
        Zamowienia zamowienie = new Zamowienia();
        zamowienie.setNr_zamowienia(rs.getInt("NR_Zamowienia" ));
        zamowienie.setStan_realizacji(rs.getString("Stan_Realizacji" ));
        zamowienie.setData_Zamowienia(rs.getString("Data_Zamowienia" ));
        zamowienie.setData_realizacji(rs.getString("Data_Realizacji" ));
        zamowienie.setOpis(rs.getString("Opis" ));
        zamowienie.setId_klienta(rs.getInt("ID_Klienta" ));
        return zamowienie;
    }
}
