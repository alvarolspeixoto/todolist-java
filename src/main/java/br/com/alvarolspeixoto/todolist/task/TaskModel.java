package br.com.alvarolspeixoto.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


/**
 * ID
 * Usuário (ID_USUARIO)
 * Descrição
 * Título
 * Data de Início
 * Data de Término
 * Prioridade
 */

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    private String priority;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
    private UUID userId;
}
