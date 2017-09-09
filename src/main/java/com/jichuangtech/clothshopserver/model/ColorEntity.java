package com.jichuangtech.clothshopserver.model;

import javax.persistence.*;

/**
 * Created by Bingo on 2017/9/9.
 */
@Entity
@Table(name = "color", schema = "clothShop", catalog = "")
public class ColorEntity {
    private int id;
    private String colorName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "color_name", nullable = false, length = 55)
    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColorEntity that = (ColorEntity) o;

        if (id != that.id) return false;
        if (colorName != null ? !colorName.equals(that.colorName) : that.colorName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (colorName != null ? colorName.hashCode() : 0);
        return result;
    }
}
