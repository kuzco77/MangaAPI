package com.hust.manga.model;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
@Entity
@Table(name = "chapter_detail")
public class ChapterDetail {
    @SerializedName("manga_id")
    private Integer id;
    @SerializedName("id")
    private String originId;
    @SerializedName("number")
    private Integer number;
    @SerializedName("url")
    private String url;
    @SerializedName("chapterId")
    private String chapterId;
    @SerializedName("width")
    private Integer width;
    @SerializedName("height")
    private Integer height;
    private Timestamp addDate;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "chapter_id")
    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    @Basic
    @Column(name = "width")
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Basic
    @Column(name = "height")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
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
        ChapterDetail that = (ChapterDetail) o;
        return id == that.id &&
                Objects.equals(originId, that.originId) &&
                Objects.equals(number, that.number) &&
                Objects.equals(url, that.url) &&
                Objects.equals(chapterId, that.chapterId) &&
                Objects.equals(width, that.width) &&
                Objects.equals(height, that.height) &&
                Objects.equals(addDate, that.addDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originId, number, url, chapterId, width, height, addDate);
    }
}
