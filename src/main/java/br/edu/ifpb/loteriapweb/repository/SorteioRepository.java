package br.edu.ifpb.loteriapweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.loteriapweb.model.Sorteio;

@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Integer>{

}
