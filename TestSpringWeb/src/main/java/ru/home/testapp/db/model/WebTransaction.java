package ru.home.testapp.db.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author STechiev
 */
@Entity
@Table(name = "web_transaction", catalog = "test", schema = "")
@NamedQueries({
    @NamedQuery(name = "WebTransaction.findAll", query = "SELECT w FROM WebTransaction w"),
    @NamedQuery(name = "WebTransaction.findById", query = "SELECT w FROM WebTransaction w WHERE w.id = :id"),
    @NamedQuery(name = "WebTransaction.findByServiceId", query = "SELECT w FROM WebTransaction w WHERE w.serviceId = :serviceId"),
    @NamedQuery(name = "WebTransaction.findByMsisdn", query = "SELECT w FROM WebTransaction w WHERE w.msisdn = :msisdn"),
    @NamedQuery(name = "WebTransaction.findBySubscriberId", query = "SELECT w FROM WebTransaction w WHERE w.subscriberId = :subscriberId"),
    @NamedQuery(name = "WebTransaction.findByStatus", query = "SELECT w FROM WebTransaction w WHERE w.status = :status"),
    @NamedQuery(name = "WebTransaction.findByClickId", query = "SELECT w FROM WebTransaction w WHERE w.clickId = :clickId"),
    @NamedQuery(name = "WebTransaction.findByReturnUrl", query = "SELECT w FROM WebTransaction w WHERE w.returnUrl = :returnUrl"),
    @NamedQuery(name = "WebTransaction.findByIp", query = "SELECT w FROM WebTransaction w WHERE w.ip = :ip"),
    @NamedQuery(name = "WebTransaction.findByCreated", query = "SELECT w FROM WebTransaction w WHERE w.created = :created"),
    @NamedQuery(name = "WebTransaction.findByUpdated", query = "SELECT w FROM WebTransaction w WHERE w.updated = :updated"),
    @NamedQuery(name = "WebTransaction.findByStreamId", query = "SELECT w FROM WebTransaction w WHERE w.streamId = :streamId"),
    @NamedQuery(name = "WebTransaction.findByPartnerTid", query = "SELECT w FROM WebTransaction w WHERE w.partnerTid = :partnerTid"),
    @NamedQuery(name = "WebTransaction.findByCrc", query = "SELECT w FROM WebTransaction w WHERE w.crc = :crc"),
    @NamedQuery(name = "WebTransaction.findByUagent", query = "SELECT w FROM WebTransaction w WHERE w.uagent = :uagent"),
    @NamedQuery(name = "WebTransaction.findByOperatorGroupId", query = "SELECT w FROM WebTransaction w WHERE w.operatorGroupId = :operatorGroupId"),
    @NamedQuery(name = "WebTransaction.findBySubscrType", query = "SELECT w FROM WebTransaction w WHERE w.subscrType = :subscrType"),
    @NamedQuery(name = "WebTransaction.findByTmpUrlWap", query = "SELECT w FROM WebTransaction w WHERE w.tmpUrlWap = :tmpUrlWap"),
    @NamedQuery(name = "WebTransaction.findByTmpUrlWeb", query = "SELECT w FROM WebTransaction w WHERE w.tmpUrlWeb = :tmpUrlWeb"),
    @NamedQuery(name = "WebTransaction.findByOperatorUid", query = "SELECT w FROM WebTransaction w WHERE w.operatorUid = :operatorUid"),
    @NamedQuery(name = "WebTransaction.findByGwSubscrId", query = "SELECT w FROM WebTransaction w WHERE w.gwSubscrId = :gwSubscrId"),
    @NamedQuery(name = "WebTransaction.findByResult", query = "SELECT w FROM WebTransaction w WHERE w.result = :result"),
    @NamedQuery(name = "WebTransaction.findByGwStatusCode", query = "SELECT w FROM WebTransaction w WHERE w.gwStatusCode = :gwStatusCode")})
public class WebTransaction implements Serializable {

    public static final String TYPE_WEB = "web";
    public static final String TYPE_WAP = "wap";
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "id")
    private String id;
    @Size(max = 45)
    @Column(name = "service_id")
    private String serviceId;
    @Size(max = 45)
    @Column(name = "msisdn")
    private String msisdn;
    @Size(max = 45)
    @Column(name = "subscriber_id")
    private String subscriberId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Size(max = 255)
    @Column(name = "click_id")
    private String clickId;
    @Size(max = 45)
    @Column(name = "return_url")
    private String returnUrl;
    @Size(max = 45)
    @Column(name = "ip")
    private String ip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "stream_id")
    private String streamId;
    @Size(max = 45)
    @Column(name = "partner_tid")
    private String partnerTid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "crc")
    private String crc;
    @Size(max = 512)
    @Column(name = "uagent")
    private String uagent;
    @Column(name = "operator_group_id")
    private Integer operatorGroupId;
    @Size(max = 45)
    @Column(name = "subscr_type")
    private String subscrType;
    @Size(max = 512)
    @Column(name = "tmp_url_wap")
    private String tmpUrlWap;
    @Size(max = 512)
    @Column(name = "tmp_url_web")
    private String tmpUrlWeb;
    @Size(max = 255)
    @Column(name = "operator_uid")
    private String operatorUid;
    @Size(max = 255)
    @Column(name = "gw_subscr_id")
    private String gwSubscrId;
    @Column(name = "result")
    private Boolean result;
    @Column(name = "gw_status_code")
    private Integer gwStatusCode;
    @Size(max = 45)
    @Column(name = "remote_ip")
    private String remoteIp;

    public WebTransaction() {
        this.id = UUID.randomUUID().toString();
    }

    public WebTransaction(String id) {
        this.id = id;
    }

    public WebTransaction(String id, int status, Date created, String streamId, String crc) {
        this.id = id;
        this.status = status;
        this.created = created;
        this.streamId = streamId;
        this.crc = crc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClickId() {
        return clickId;
    }

    public void setClickId(String clickId) {
        this.clickId = clickId;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public String getPartnerTid() {
        return partnerTid;
    }

    public void setPartnerTid(String partnerTid) {
        this.partnerTid = partnerTid;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getUagent() {
        return uagent;
    }

    public void setUagent(String uagent) {
        this.uagent = uagent;
    }

    public Integer getOperatorGroupId() {
        return operatorGroupId;
    }

    public void setOperatorGroupId(Integer operatorGroupId) {
        this.operatorGroupId = operatorGroupId;
    }

    public String getSubscrType() {
        return subscrType;
    }

    public void setSubscrType(String subscrType) {
        this.subscrType = subscrType;
    }

    public String getTmpUrlWap() {
        return tmpUrlWap;
    }

    public void setTmpUrlWap(String tmpUrlWap) {
        this.tmpUrlWap = tmpUrlWap;
    }

    public String getTmpUrlWeb() {
        return tmpUrlWeb;
    }

    public void setTmpUrlWeb(String tmpUrlWeb) {
        this.tmpUrlWeb = tmpUrlWeb;
    }

    public String getOperatorUid() {
        return operatorUid;
    }

    public void setOperatorUid(String operatorUid) {
        this.operatorUid = operatorUid;
    }

    public String getGwSubscrId() {
        return gwSubscrId;
    }

    public void setGwSubscrId(String gwSubscrId) {
        this.gwSubscrId = gwSubscrId;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Integer getGwStatusCode() {
        return gwStatusCode;
    }

    public void setGwStatusCode(Integer gwStatusCode) {
        this.gwStatusCode = gwStatusCode;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
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
        if (!(object instanceof WebTransaction)) {
            return false;
        }
        WebTransaction other = (WebTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WebTransaction{" + "id=" + id + ", serviceId=" + serviceId + ", msisdn=" + msisdn + ", subscriberId=" + subscriberId + ", status=" + status + ", clickId=" + clickId + ", returnUrl=" + returnUrl + ", ip=" + ip + ", created=" + created + ", updated=" + updated + ", streamId=" + streamId + ", partnerTid=" + partnerTid + ", crc=" + crc + ", uagent=" + uagent + ", operatorGroupId=" + operatorGroupId + ", subscrType=" + subscrType + ", tmpUrlWap=" + tmpUrlWap + ", tmpUrlWeb=" + tmpUrlWeb + ", operatorUid=" + operatorUid + ", result=" + result + ", gwStatusCode=" + gwStatusCode + '}';
    }

}
