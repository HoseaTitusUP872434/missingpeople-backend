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
@Table(name = "private_messages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrivateMessages.findAll", query = "SELECT p FROM PrivateMessages p")
    , @NamedQuery(name = "PrivateMessages.findById", query = "SELECT p FROM PrivateMessages p WHERE p.id = :id")
    , @NamedQuery(name = "PrivateMessages.findByMessage", query = "SELECT p FROM PrivateMessages p WHERE p.message = :message")
    , @NamedQuery(name = "PrivateMessages.findByIsSeen", query = "SELECT p FROM PrivateMessages p WHERE p.isSeen = :isSeen")
    , @NamedQuery(name = "PrivateMessages.findByDateSent", query = "SELECT p FROM PrivateMessages p WHERE p.dateSent = :dateSent")})
public class PrivateMessages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "message")
    private String message;
    @Column(name = "is_seen")
    private Boolean isSeen;
    @Column(name = "date_sent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSent;
    @JoinColumn(name = "from", referencedColumnName = "id")
    @ManyToOne
    private Citizens from1;
    @JoinColumn(name = "to", referencedColumnName = "id")
    @ManyToOne
    private Citizens to;

    public PrivateMessages() {
    }

    public PrivateMessages(Integer id) {
        this.id = id;
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

    public Boolean getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(Boolean isSeen) {
        this.isSeen = isSeen;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public Citizens getFrom1() {
        return from1;
    }

    public void setFrom1(Citizens from1) {
        this.from1 = from1;
    }

    public Citizens getTo() {
        return to;
    }

    public void setTo(Citizens to) {
        this.to = to;
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
        if (!(object instanceof PrivateMessages)) {
            return false;
        }
        PrivateMessages other = (PrivateMessages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.security.nexarssecurity.entities.PrivateMessages[ id=" + id + " ]";
    }
    
}
