package com.example.backend.dao;

import com.example.backend.entities.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface FunctionRepository extends JpaRepository<Function,Long> {

//    Optional<Function> findFunctionByRepRapportId(Long id);

    @Query(value = "SELECT r.* FROM etl.rep_rapports r inner join etl.function_reporting f ON r.id = f.list_rep_id WHERE f.function_id = ?1 ORDER BY f.rep_order ASC", nativeQuery = true)
    List<BigInteger> findReportsByFunctionId(@PathVariable("functionId") Long functionId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE etl.function_reporting SET rep_order = ?1 WHERE function_id = ?2 AND list_rep_id = ?3", nativeQuery = true)
    void updateReportOrderForFunction(Long newOrder, Long functionId, Long repId);
}
