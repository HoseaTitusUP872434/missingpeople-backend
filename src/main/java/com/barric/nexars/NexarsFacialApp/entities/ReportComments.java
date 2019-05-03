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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "report_comments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportComments.findAll", query = "SELECT r FROM ReportComments r")
    , @NamedQuery(name = "ReportComments.findById", query = "SELECT r FROM ReportComments r WHERE r.id = :id")
    , @NamedQuery(name = "ReportComments.findByComment", query = "SELECT r FROM ReportComments r WHERE r.comment = :comment")
    , @NamedQuery(name = "ReportComments.findAllByReport", query = "SELECT r FROM ReportComments r WHERE r.reportId.id = :reportId ORDER BY r.id DESC")
    , @NamedQuery(name = "ReportComments.findByDateCreated", query = "SELECT r FROM ReportComments r WHERE r.dateCreated = :dateCreated")})
public class ReportComments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "comment")
    private String comment;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "citizen_id", referencedColumnName = "id")
    @ManyToOne
    private Citizens citizenId;
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    @ManyToOne
    private Media mediaId;
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    @ManyToOne
    private Reports reportId;

    public ReportComments() {
    }

    public ReportComments(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Citizens getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Citizens citizenId) {
        this.citizenId = citizenId;
    }

    public Media getMediaId() {
        return mediaId;
    }

    public void setMediaId(Media mediaId) {
        this.mediaId = mediaId;
    }

    public Reports getReportId() {
        return reportId;
    }

    public void setReportId(Reports reportId) {
        this.reportId = reportId;
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
        if (!(object instanceof ReportComments)) {
            return false;
        }
        ReportComments other = (ReportComments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.security.nexarssecurity.entities.ReportComments[ id=" + id + " ]";
    }

}
