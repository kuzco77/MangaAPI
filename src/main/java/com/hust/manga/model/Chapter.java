package com.hust.manga.model;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
@Entity
@Table(name = "chapter")
public class Chapter {
    @SerializedName("manga_id")
    private int id;
    @SerializedName("id")
    private String originId;
    @SerializedName("mangaImage")
    private String mangaImage;
    @SerializedName("mangaTitle")
    private String mangaTitle;
    @SerializedName("mangaId")
    private String mangaId;
    @SerializedName("keyGet")
    private String keyGet;
    @SerializedName("number")
    private Integer number;
    @SerializedName("chapterCreatedAt")
    private Long createdAt;
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
    @Column(name = "origin_id")
    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    @Basic
    @Column(name = "manga_image")
    public String getMangaImage() {
        return mangaImage;
    }

    public void setMangaImage(String mangaImage) {
        this.mangaImage = mangaImage;
    }

    @Basic
    @Column(name = "manga_title")
    public String getMangaTitle() {
        return mangaTitle;
    }

    public void setMangaTitle(String mangaTitle) {
        this.mangaTitle = mangaTitle;
    }

    @Basic
    @Column(name = "manga_id")
    public String getMangaId() {
        return mangaId;
    }

    public void setMangaId(String mangaId) {
        this.mangaId = mangaId;
    }

    @Basic
    @Column(name = "key_get")
    public String getKeyGet() {
        return keyGet;
    }

    public void setKeyGet(String keyGet) {
        this.keyGet = keyGet;
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
    @Column(name = "created_at")
    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createAt) {
        this.createdAt = createAt;
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
        Chapter chapter = (Chapter) o;
        return id == chapter.id &&
                Objects.equals(originId, chapter.originId) &&
                Objects.equals(mangaImage, chapter.mangaImage) &&
                Objects.equals(mangaTitle, chapter.mangaTitle) &&
                Objects.equals(mangaId, chapter.mangaId) &&
                Objects.equals(keyGet, chapter.keyGet) &&
                Objects.equals(number, chapter.number) &&
                Objects.equals(createdAt, chapter.createdAt) &&
                Objects.equals(addDate, chapter.addDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originId, mangaImage, mangaTitle, mangaId, keyGet, number, createdAt, addDate);
    }
}
