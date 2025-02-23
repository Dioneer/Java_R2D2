package Pegas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "catalogue", name = "t_product")
@NamedQueries(
        @NamedQuery(name = "Product.findAllByTitleLikeIgnoreCase",
        query = "select p from Product p where p.title ilike :filter")
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "c_title", nullable = true)
    private String title;
    @Column(name = "c_details")
    private String details;
}
