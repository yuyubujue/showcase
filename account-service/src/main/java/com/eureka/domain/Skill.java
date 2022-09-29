package com.eureka.domain;

import org.apache.commons.lang.builder.EqualsBuilder;

import javax.persistence.*;

@Entity
@Table
public class Skill {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "SKILL")
    private String skill;


    public Skill() {}

    public Skill(String skill) {
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    public String getId() {
        return id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        return new EqualsBuilder()
                .append(skill, skill.skill)
                .isEquals();
    }
}