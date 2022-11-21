package com.example.projekt_serwis_komputerowy;

public class Zamowienia {
    private Integer nr_zamowienia;
    private String stan_realizacji;
    private String data_zamowienia;
    private String data_realizacji;
    private String opis;
    private Integer id_klienta;

    public void setNr_zamowienia(Integer nr_zamowienia) {
        this.nr_zamowienia = nr_zamowienia;
    }
    public Integer getNr_zamowienia() {
        return nr_zamowienia;
    }

    public void setStan_realizacji(String stan_realizacji) {
        this.stan_realizacji = stan_realizacji;
    }
    public String getStan_realizacji() {
        return stan_realizacji;
    }

    public void setData_Zamowienia(String data_zamowienia) {
        this.data_zamowienia = data_zamowienia;
    }
    public String getData_zamowienia() {
        return data_zamowienia;
    }

    public void setData_realizacji(String data_realizacji) {
        this.data_realizacji = data_realizacji;
    }
    public String getData_realizacji() {
        return data_realizacji;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    public String getOpis() {
        return opis;
    }

    public void setId_klienta(Integer id_klienta) {
        this.id_klienta = id_klienta;
    }
    public Integer getId_klienta() {
        return id_klienta;
    }
}
