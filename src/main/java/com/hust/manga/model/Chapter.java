package com.hust.manga.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
@Entity
@Table(name = "chapter")
public class Chapter {
    private int id;
    private String originId;
    private String mangaImage;
    private String mangaTitle;
    private Integer mangaId;
    private Integer keyGet;
    private Integer number;
    private String createAt;
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
    public Integer getMangaId() {
        return mangaId;
    }

    public void setMangaId(Integer mangaId) {
        this.mangaId = mangaId;
    }

    @Basic
    @Column(name = "key_get")
    public Integer getKeyGet() {
        return keyGet;
    }

    public void setKeyGet(Integer keyGet) {
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
    @Column(name = "create_at")
    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
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
                Objects.equals(createAt, chapter.createAt) &&
                Objects.equals(addDate, chapter.addDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originId, mangaImage, mangaTitle, mangaId, keyGet, number, createAt, addDate);
    }
}
