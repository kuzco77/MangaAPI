package com.hust.manga.model;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
@Entity
@Table(name = "manga")
public class Manga {
    @SerializedName("manga_id")
    private Integer id;
    @SerializedName("id")
    private String originId;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("artist")
    private String artist;
    @SerializedName("author")
    private String author;
    @SerializedName("description")
    private String description;
    @SerializedName("categories")
    private String categories;
    @SerializedName("status")
    private Integer status;
    @SerializedName("hits")
    private Integer hits;
    @SerializedName("created")
    private Long created;
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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "artist")
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "categories")
    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "hits")
    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    @Basic
    @Column(name = "created")
    public Long getCreated() {
        return created;
    }

    public void setCreated(Long createAt) {
        this.created = createAt;
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
        Manga manga = (Manga) o;
        return id.equals(manga.id) &&
                Objects.equals(originId, manga.originId) &&
                Objects.equals(title, manga.title) &&
                Objects.equals(image, manga.image) &&
                Objects.equals(artist, manga.artist) &&
                Objects.equals(author, manga.author) &&
                Objects.equals(description, manga.description) &&
                Objects.equals(categories, manga.categories) &&
                Objects.equals(status, manga.status) &&
                Objects.equals(hits, manga.hits) &&
                Objects.equals(created, manga.created) &&
                Objects.equals(addDate, manga.addDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originId, title, image, artist, author, description, categories, status, hits, created, addDate);
    }
}
