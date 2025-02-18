package edu.school21.shopapi.model;

import edu.school21.shopapi.model.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "images", schema = "shopapp")
public class Image extends BaseEntity {
    @Column(name = "image", nullable = false)
    private byte[] image;
}