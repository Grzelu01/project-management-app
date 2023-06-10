package pl.pracadyplomowa.Vera.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String name;
    @Column
    private String task;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;
}
