package com.example.persistence.jpahibernate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publisher;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
