package com.example.loki.winners.northwindapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "shippers")
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShipperID", nullable = false)
    private Integer id;

    @Column(name = "CompanyName", nullable = false, length = 40)
    private String companyName;

    @Column(name = "Phone", length = 24)
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}