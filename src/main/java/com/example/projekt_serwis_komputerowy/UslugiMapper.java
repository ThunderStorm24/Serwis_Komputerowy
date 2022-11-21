package com.example.projekt_serwis_komputerowy;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UslugiMapper implements RowMapper<Uslugi> {
    public Uslugi mapRow(ResultSet rs, int rowNum) throws SQLException {
        Uslugi uslugi = new Uslugi();
        uslugi.setId(rs.getInt("ID_Uslugi" ));
        uslugi.setName(rs.getString("Nazwa_Uslugi" ));
        uslugi.setDesc(rs.getString("Opis" ));
        uslugi.setPrice(rs.getInt("Cena" ));
        return uslugi;
    }
}
