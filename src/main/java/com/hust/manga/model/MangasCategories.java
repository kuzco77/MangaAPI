package com.hust.manga.model;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "mangas_categories")
public class MangasCategories {
    private int id;
    private int mangaId;
    private int caategoryId;

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
    @Column(name = "manga_id")
    public int getMangaId() {
        return mangaId;
    }

    public void setMangaId(int mangaId) {
        this.mangaId = mangaId;
    }

    @Basic
    @Column(name = "caategory_id")
    public int getCaategoryId() {
        return caategoryId;
    }

    public void setCaategoryId(int caategoryId) {
        this.caategoryId = caategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MangasCategories that = (MangasCategories) o;
        return id == that.id &&
                mangaId == that.mangaId &&
                caategoryId == that.caategoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mangaId, caategoryId);
    }
}
