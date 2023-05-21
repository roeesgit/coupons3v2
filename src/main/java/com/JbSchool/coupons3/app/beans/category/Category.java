package com.JbSchool.coupons3.app.beans.category;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Scope("prototype")
@Table(name = "categories")
public class Category {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name")
//    @Enumerated(EnumType.STRING)
    private String name;
}
