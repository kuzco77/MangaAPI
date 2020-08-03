package com.hust.manga.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category {

    @SerializedName("createdsdsdsdsdAt")
    private Integer id = 0;
    @Expose
    @SerializedName("createdAt")
    private Long createdAt;
    @Expose
    @SerializedName("createdBy")
    private String createdBy;
    @Expose
    @SerializedName("lastUpdatedBy")
    private String lastUpdatedBy;
    @Expose
    @SerializedName("updatedAt")
    private Long updatedAt;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("image")
    private String image;
    @Expose
    @SerializedName("id")
    private String originId;
    private Timestamp addDate;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "created_at")
    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "last_updated_by")
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Basic
    @Column(name = "updated_at")
    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "origin_id")
    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    @Basic
    @Column(name = "add_date")
    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                Objects.equals(createdAt, category.createdAt) &&
                Objects.equals(createdBy, category.createdBy) &&
                Objects.equals(lastUpdatedBy, category.lastUpdatedBy) &&
                Objects.equals(updatedAt, category.updatedAt) &&
                Objects.equals(name, category.name) &&
                Objects.equals(image, category.image) &&
                Objects.equals(originId, category.originId) &&
                Objects.equals(addDate, category.addDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, createdBy, lastUpdatedBy, updatedAt, name, image, originId, addDate);
    }
}
