/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barric.nexars.NexarsFacialApp.entities;

import com.barric.nexars.NexarsFacialApp.resources.NigerianStateLgaResource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Barima
 */
@Entity
@Table(name = "citizens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Citizens.findAll", query = "SELECT c FROM Citizens c")
    , @NamedQuery(name = "Citizens.findById", query = "SELECT c FROM Citizens c WHERE c.id = :id")
    , @NamedQuery(name = "Citizens.findByFirstname", query = "SELECT c FROM Citizens c WHERE c.firstname = :firstname")
    , @NamedQuery(name = "Citizens.findByOthernames", query = "SELECT c FROM Citizens c WHERE c.othernames = :othernames")
    , @NamedQuery(name = "Citizens.findByLastname", query = "SELECT c FROM Citizens c WHERE c.lastname = :lastname")
    , @NamedQuery(name = "Citizens.findByEmail", query = "SELECT c FROM Citizens c WHERE c.email = :email")
    , @NamedQuery(name = "Citizens.findByEmailAndPassword", query = "SELECT c FROM Citizens c WHERE c.email = :email AND c.password = :password")
    , @NamedQuery(name = "Citizens.findByStateId", query = "SELECT c FROM Citizens c WHERE c.stateId = :stateId")
    , @NamedQuery(name = "Citizens.findByLgaId", query = "SELECT c FROM Citizens c WHERE c.lgaId = :lgaId")
    , @NamedQuery(name = "Citizens.findByAddress", query = "SELECT c FROM Citizens c WHERE c.address = :address")
    , @NamedQuery(name = "Citizens.findByNin", query = "SELECT c FROM Citizens c WHERE c.nin = :nin")
    , @NamedQuery(name = "Citizens.findByPassword", query = "SELECT c FROM Citizens c WHERE c.password = :password")
    , @NamedQuery(name = "Citizens.findByDateRegistered", query = "SELECT c FROM Citizens c WHERE c.dateRegistered = :dateRegistered")
    , @NamedQuery(name = "Citizens.findByDateOfBirth", query = "SELECT c FROM Citizens c WHERE c.dateOfBirth = :dateOfBirth")})
public class Citizens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 45)
    @Column(name = "othernames")
    private String othernames;
    @Size(max = 45)
    @Column(name = "lastname")
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    
     @JoinColumn(name = "state_id", referencedColumnName = "state_id")
    @OneToOne
    private NigerianStates stateId;
    
   
    
     @JoinColumn(name = "lga_id", referencedColumnName = "id")
    @OneToOne
    private NigeriaStateLga lgaId;
    
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Size(max = 15)
    @Column(name = "nin")
    private String nin;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Column(name = "date_registered")
    @Temporal(TemporalType.DATE)
    private Date dateRegistered;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citizenId")
    private List<Reports> reportsList;
    @OneToMany(mappedBy = "citizenId")
    private List<ReportComments> reportCommentsList;
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    @ManyToOne
    private Media mediaId;
    @OneToMany(mappedBy = "citizenId")
    private List<Notifications> notificationsList;
    @OneToMany(mappedBy = "from1")
    private List<PrivateMessages> privateMessagesList;
    @OneToMany(mappedBy = "to")
    private List<PrivateMessages> privateMessagesList1;

    public Citizens() {
    }

    public Citizens(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getOthernames() {
        return othernames;
    }

    public void setOthernames(String othernames) {
        this.othernames = othernames;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NigerianStates getStateId() {
        return stateId;
    }

    public void setStateId(NigerianStates stateId) {
        this.stateId = stateId;
    }

    public NigeriaStateLga getLgaId() {
        return lgaId;
    }

    public void setLgaId(NigeriaStateLga lgaId) {
        this.lgaId = lgaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @XmlTransient
    @JsonIgnore
    public List<Reports> getReportsList() {
        return reportsList;
    }

    public void setReportsList(List<Reports> reportsList) {
        this.reportsList = reportsList;
    }

    @XmlTransient
    @JsonIgnore
    public List<ReportComments> getReportCommentsList() {
        return reportCommentsList;
    }

    public void setReportCommentsList(List<ReportComments> reportCommentsList) {
        this.reportCommentsList = reportCommentsList;
    }

    
    public Media getMediaId() {
        return mediaId;
    }

    public void setMediaId(Media mediaId) {
        this.mediaId = mediaId;
    }

    @XmlTransient
    @JsonIgnore
    public List<Notifications> getNotificationsList() {
        return notificationsList;
    }

    public void setNotificationsList(List<Notifications> notificationsList) {
        this.notificationsList = notificationsList;
    }

    @XmlTransient
    @JsonIgnore
    public List<PrivateMessages> getPrivateMessagesList() {
        return privateMessagesList;
    }

    public void setPrivateMessagesList(List<PrivateMessages> privateMessagesList) {
        this.privateMessagesList = privateMessagesList;
    }

    @XmlTransient
    @JsonIgnore
    public List<PrivateMessages> getPrivateMessagesList1() {
        return privateMessagesList1;
    }

    public void setPrivateMessagesList1(List<PrivateMessages> privateMessagesList1) {
        this.privateMessagesList1 = privateMessagesList1;
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
        if (!(object instanceof Citizens)) {
            return false;
        }
        Citizens other = (Citizens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.security.nexarssecurity.entities.Citizens[ id=" + id + " ]";
    }
    
}
