package br.edu.ifpb.loteriapweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.loteriapweb.model.Aposta;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {

}
