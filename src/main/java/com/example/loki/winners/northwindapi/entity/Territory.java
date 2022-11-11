package com.example.loki.winners.northwindapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "territories")
public class Territory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TerritoryID", nullable = false, length = 20)
    private String id;

    @Column(name = "TerritoryDescription", nullable = false, length = 50)
    private String territoryDescription;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "RegionID", nullable = false)
    private Region regionID;

    public Region getRegionID() {
        return regionID;
    }

    public void setRegionID(Region regionID) {
        this.regionID = regionID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
    }

}