package br.com.rankbet.model;


import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_properties")
public class PropertiesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "propertie_key")
    private String propertieKey;

    @Column(name = "propertie_value")
    private String propertieValue;

    public long getId() {
        return id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getPropertieKey() {
        return propertieKey;
    }

    public String getPropertieValue() {
        return propertieValue;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setPropertieKey(String propertieKey) {
        this.propertieKey = propertieKey;
    }

    public void setPropertieValue(String propertieValue) {
        this.propertieValue = propertieValue;
    }
}
