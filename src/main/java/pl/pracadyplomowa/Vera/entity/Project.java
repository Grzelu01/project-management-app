package pl.pracadyplomowa.Vera.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String projectName;
    @Column
    private String description;
    @Column
    private double budget;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private List<Room> rooms;
}
