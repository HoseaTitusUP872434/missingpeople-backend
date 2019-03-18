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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "media")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Media.findAll", query = "SELECT m FROM Media m")
    , @NamedQuery(name = "Media.findById", query = "SELECT m FROM Media m WHERE m.id = :id")
    , @NamedQuery(name = "Media.findByUrl", query = "SELECT m FROM Media m WHERE m.url = :url")
    , @NamedQuery(name = "Media.findByReportId", query = "SELECT m FROM Media m WHERE m.reportId.id = :id")
    , @NamedQuery(name = "Media.findByMediaType", query = "SELECT m FROM Media m WHERE m.mediaType = :mediaType")
    , @NamedQuery(name = "Media.findByIsCensored", query = "SELECT m FROM Media m WHERE m.isCensored = :isCensored")})
public class Media implements Serializable {

    @OneToMany(mappedBy = "mediaId")
    private List<FacialFeature> facialFeatureList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "url")
    private String url;
    @Size(max = 50)
    @Column(name = "media_type")
    private String mediaType;
    @Column(name = "is_censored")
    private Boolean isCensored;
    @OneToMany(mappedBy = "mediaId")
    private List<ReportComments> reportCommentsList;
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    @ManyToOne
    private Reports reportId;
    @OneToMany(mappedBy = "mediaId")
    private List<Citizens> citizensList;

    public Media() {
    }

    public Media(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Boolean getIsCensored() {
        return isCensored;
    }

    public void setIsCensored(Boolean isCensored) {
        this.isCensored = isCensored;
    }

    @XmlTransient
    @JsonIgnore
    public List<ReportComments> getReportCommentsList() {
        return reportCommentsList;
    }

    public void setReportCommentsList(List<ReportComments> reportCommentsList) {
        this.reportCommentsList = reportCommentsList;
    }

    @JsonIgnore
    public Reports getReportId() {
        return reportId;
    }

    public void setReportId(Reports reportId) {
        this.reportId = reportId;
    }

    @XmlTransient
    @JsonIgnore
    public List<Citizens> getCitizensList() {
        return citizensList;
    }

    public void setCitizensList(List<Citizens> citizensList) {
        this.citizensList = citizensList;
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
        if (!(object instanceof Media)) {
            return false;
        }
        Media other = (Media) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.security.nexarssecurity.entities.Media[ id=" + id + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public List<FacialFeature> getFacialFeatureList() {
        return facialFeatureList;
    }

    public void setFacialFeatureList(List<FacialFeature> facialFeatureList) {
        this.facialFeatureList = facialFeatureList;
    }

}
