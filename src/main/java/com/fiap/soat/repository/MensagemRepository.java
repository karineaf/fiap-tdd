package com.fiap.soat.repository;

import com.fiap.soat.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, UUID> {
    List<Mensagem> findAllByUsuario(String usuario);
}
