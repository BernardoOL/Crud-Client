package org.berns.clients.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import org.berns.clients.entities.Client;

import java.time.LocalDate;

public class ClientDTO {

    private Long id;
    @NotBlank(message= "Não pode ser vazio")
    private String name;
    private String cpf;
    private Double income;
    @PastOrPresent(message = "Não pode ser data futura")
    private LocalDate birthDate;
    private Integer children;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.income = client.getIncome();
        this.birthDate = client.getBirthDate();
        this.children = client.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
