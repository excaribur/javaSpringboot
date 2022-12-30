package com.bob.backend.entity;

import com.bob.backend.entity.BaseEntity;
import com.bob.backend.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_social")
public class Social extends BaseEntity {

    @Column(length = 120)
    private String facebook;

    @Column(length = 120)
    private String Line;

    @Column(length = 120)
    private String instagram;


    @Column(length = 120)
    private String tiktok;

    @OneToOne
    @JoinColumn(name = "m_user_id")
    private User user;
}
