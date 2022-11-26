package com.example.projekt_serwis_komputerowy.GetSet;

public class Uslugi_Zamowienia {

        private Integer id_uslugi;
        private Integer nr_zamowienia;
        private Integer koszt;
        private String opis;

        public void setId_uslugi(Integer id_uslugi) {
        this.id_uslugi = id_uslugi;
    }
        public Integer getId_uslugi() {
        return id_uslugi;
    }

        public void setNr_zamowienia(Integer nr_zamowienia) {
            this.nr_zamowienia = nr_zamowienia;
        }
        public Integer getNr_zamowienia() {
            return nr_zamowienia;
        }

        public void setKoszt(Integer koszt) {
        this.koszt = koszt;
    }
        public Integer getKoszt() {
        return koszt;
    }

        public void setOpis(String opis) {
            this.opis = opis;
        }
        public String getOpis() {
            return opis;
        }

    }


