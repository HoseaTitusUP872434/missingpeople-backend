/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "facial_face_ids")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacialFaceIds.findAll", query = "SELECT f FROM FacialFaceIds f")
    , @NamedQuery(name = "FacialFaceIds.findById", query = "SELECT f FROM FacialFaceIds f WHERE f.id = :id")
    , @NamedQuery(name = "FacialFaceIds.findByPersonId", query = "SELECT f FROM FacialFaceIds f WHERE f.personId = :personId")
    , @NamedQuery(name = "FacialFaceIds.findByPersonIdOrPersist", query = "SELECT f FROM FacialFaceIds f WHERE f.personId = :personId OR f.persistedFaceId = :persist")
    , @NamedQuery(name = "FacialFaceIds.findByPersistedFaceId", query = "SELECT f FROM FacialFaceIds f WHERE f.persistedFaceId = :persistedFaceId")
    , @NamedQuery(name = "FacialFaceIds.findByDateCreated", query = "SELECT f FROM FacialFaceIds f WHERE f.dateCreated = :dateCreated")})
public class FacialFaceIds implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 225)
    @Column(name = "person_id")
    private String personId;
    @Size(max = 225)
    @Column(name = "persisted_face_id")
    private String persistedFaceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Media mediaId;

    public FacialFaceIds() {
    }

    public FacialFaceIds(Integer id) {
        this.id = id;
    }

    public FacialFaceIds(Integer id, Date dateCreated) {
        this.id = id;
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersistedFaceId() {
        return persistedFaceId;
    }

    public void setPersistedFaceId(String persistedFaceId) {
        this.persistedFaceId = persistedFaceId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Media getMediaId() {
        return mediaId;
    }

    public void setMediaId(Media mediaId) {
        this.mediaId = mediaId;
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
        if (!(object instanceof FacialFaceIds)) {
            return false;
        }
        FacialFaceIds other = (FacialFaceIds) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.facialapp.facialentities.FacialFaceIds[ id=" + id + " ]";
    }

}
