/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "nigeria_state_lga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NigeriaStateLga.findAll", query = "SELECT n FROM NigeriaStateLga n")
    , @NamedQuery(name = "NigeriaStateLga.findById", query = "SELECT n FROM NigeriaStateLga n WHERE n.id = :id")
         , @NamedQuery(name = "NigeriaStateLga.findByStateId", query = "SELECT n FROM NigeriaStateLga n WHERE n.stateId.stateId = :id")
    , @NamedQuery(name = "NigeriaStateLga.findByName", query = "SELECT n FROM NigeriaStateLga n WHERE n.name = :name")})
public class NigeriaStateLga implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "state_id", referencedColumnName = "state_id")
    @ManyToOne(optional = false)
    private NigerianStates stateId;

    public NigeriaStateLga() {
    }

    public NigeriaStateLga(Integer id) {
        this.id = id;
    }

    public NigeriaStateLga(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public NigerianStates getStateId() {
        return stateId;
    }

    public void setStateId(NigerianStates stateId) {
        this.stateId = stateId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NigeriaStateLga)) {
            return false;
        }
        NigeriaStateLga other = (NigeriaStateLga) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.security.nexarssecurity.entities.NigeriaStateLga[ id=" + id + " ]";
    }
    
}
