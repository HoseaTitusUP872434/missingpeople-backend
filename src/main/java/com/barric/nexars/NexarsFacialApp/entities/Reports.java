/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "reports")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reports.findAllReports", query = "SELECT r FROM Reports r ORDER BY r.id DESC")
    , @NamedQuery(name = "Reports.findById", query = "SELECT r FROM Reports r WHERE r.id = :id")
    , @NamedQuery(name = "Reports.findByMessage", query = "SELECT r FROM Reports r WHERE r.message = :message")
    , @NamedQuery(name = "Reports.findByHasMedia", query = "SELECT r FROM Reports r WHERE r.hasMedia = :hasMedia")
    , @NamedQuery(name = "Reports.findByCaption", query = "SELECT r FROM Reports r WHERE r.caption = :caption")
    , @NamedQuery(name = "Reports.findByDateCreated", query = "SELECT r FROM Reports r WHERE r.dateCreated = :dateCreated")
    , @NamedQuery(name = "Reports.findByReportTypeId", query = "SELECT r FROM Reports r WHERE r.reportTypeId = :reportTypeId")
    , @NamedQuery(name = "Reports.findByAnonimity", query = "SELECT r FROM Reports r WHERE r.anonimity = :anonimity")})
public class Reports implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "message")
    private String message;
    @Column(name = "has_media")
    private Boolean hasMedia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "caption")
    private String caption;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "report_type_id")
    private int reportTypeId;

    @Column(name = "anonimity")
    private boolean anonimity;
    @JoinColumn(name = "citizen_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Citizens citizenId;
    @OneToMany(mappedBy = "reportId")
    private List<ReportComments> reportCommentsList;
    @OneToMany(mappedBy = "reportId")
    private List<Media> mediaList;

    public Reports() {
    }

    public Reports(Integer id) {
        this.id = id;
    }

    public Reports(Integer id, String message, String caption, Date dateCreated, int reportTypeId, boolean anonimity) {
        this.id = id;
        this.message = message;
        this.caption = caption;
        this.dateCreated = dateCreated;
        this.reportTypeId = reportTypeId;
        this.anonimity = anonimity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getHasMedia() {
        return hasMedia;
    }

    public void setHasMedia(Boolean hasMedia) {
        this.hasMedia = hasMedia;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(int reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public boolean getAnonimity() {
        return anonimity;
    }

    public void setAnonimity(boolean anonimity) {
        this.anonimity = anonimity;
    }

    public Citizens getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Citizens citizenId) {
        this.citizenId = citizenId;
    }

    @XmlTransient
    @JsonIgnore
    public List<ReportComments> getReportCommentsList() {
        return reportCommentsList;
    }

    public void setReportCommentsList(List<ReportComments> reportCommentsList) {
        this.reportCommentsList = reportCommentsList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
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
        if (!(object instanceof Reports)) {
            return false;
        }
        Reports other = (Reports) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.security.nexarssecurity.entities.Reports[ id=" + id + " ]";
    }

}
