/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "nigerian_states")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NigerianStates.findAll", query = "SELECT n FROM NigerianStates n")
    , @NamedQuery(name = "NigerianStates.findByStateId", query = "SELECT n FROM NigerianStates n WHERE n.stateId = :stateId")
    , @NamedQuery(name = "NigerianStates.findByName", query = "SELECT n FROM NigerianStates n WHERE n.name = :name")})
public class NigerianStates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "state_id")
    private Integer stateId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stateId")
    private List<NigeriaStateLga> nigeriaStateLgaList;
    
     @JoinColumn(name = "state_id", referencedColumnName = "id")
    @OneToOne
    private Citizens citizens;
   

    public NigerianStates() {
    }

    public NigerianStates(Integer stateId) {
        this.stateId = stateId;
    }

    public NigerianStates(Integer stateId, String name) {
        this.stateId = stateId;
        this.name = name;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    
    public List<NigeriaStateLga> getNigeriaStateLgaList() {
        return nigeriaStateLgaList;
    }

    @JsonIgnore
    public Citizens getCitizens() {
        return citizens;
    }

    public void setCitizens(Citizens citizens) {
        this.citizens = citizens;
    }
    
    

    public void setNigeriaStateLgaList(List<NigeriaStateLga> nigeriaStateLgaList) {
        this.nigeriaStateLgaList = nigeriaStateLgaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateId != null ? stateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NigerianStates)) {
            return false;
        }
        NigerianStates other = (NigerianStates) object;
        if ((this.stateId == null && other.stateId != null) || (this.stateId != null && !this.stateId.equals(other.stateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.security.nexarssecurity.entities.NigerianStates[ stateId=" + stateId + " ]";
    }
    
}
