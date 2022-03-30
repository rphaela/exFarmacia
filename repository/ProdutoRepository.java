package org.generation.exFarmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.generation.exFarmacia.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	public List<Produto> findAllByNomeContainingIgnoreCase (String nome);
	
	@Query(value = "select * from tb_produto where preco between :inicio and :fim", nativeQuery = true)
	public List<Produto> findByPrecoBetween(BigDecimal inicio, BigDecimal fim);

}
