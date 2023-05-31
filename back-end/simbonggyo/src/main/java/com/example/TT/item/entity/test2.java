package com.example.TT.item.entity;

import javax.persistence.*;

import org.springframework.data.jpa.domain.Specification;

import lombok.*;

import com.example.TT.item.dto.userDto2;

@Entity
@Table(name = "user_setting", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class test2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_token")
    private Long token;

    @Column(nullable = false)
    private String speaker;

    @Column(nullable = false)
    private Integer volume;

    @Column(nullable = false)
    private Integer speed;


    public test2(userDto2 userDto2) {
        this.speaker = userDto2.getSpeaker();
        this.volume = userDto2.getVolume();
        this.speed = userDto2.getSpeed();
    }

    public Long getId() {
        return token;
    }
    public static Specification<test2> findByToken(String token) {
        return (root, query, cb) -> cb.equal(root.get("token"), token);
}
}