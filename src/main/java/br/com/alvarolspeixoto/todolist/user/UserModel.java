package br.com.alvarolspeixoto.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // define os getters e os setters da classe
@Entity(name = "tb_users")
public class UserModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    //@Column(name = "usuario") - pode ser usado pra escolher o nome desse atributo na coluna do banco
    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
