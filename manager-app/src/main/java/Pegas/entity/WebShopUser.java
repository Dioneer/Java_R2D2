package Pegas.entity;

//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(schema = "user_management", name = "t_user")
//public class WebShopUser {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Integer id;
//    @Column(name = "c_username", unique = true)
//    String username;
//    @Column(name = "c_password")
//    String password;
//    @ManyToMany
//    @JoinTable(schema = "user_management", name = "t_user_authority", joinColumns = @JoinColumn(name = "id_user"),
//    inverseJoinColumns = @JoinColumn(name = "id_authority"))
//    private List<Authority> authorityList;
//}
