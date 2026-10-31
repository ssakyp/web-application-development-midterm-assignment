package com.spacebounty.repository;

import com.spacebounty.model.WantedCriminal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface WantedCriminalRepository extends JpaRepository<WantedCriminal, Long> {
    @Query("""
        select c from WantedCriminal c
        where (:minReward is null or c.reward >= :minReward)
          and (:maxReward is null or c.reward <= :maxReward)
          and (:armed is null or c.armed = :armed)
          and (:hasCrew is null or c.hasCrew = :hasCrew)
          and (:escapeMaster is null or c.escapeMaster = :escapeMaster)
        """)
    List<WantedCriminal> findFiltered(@Param("minReward") BigDecimal minReward,
                                      @Param("maxReward") BigDecimal maxReward,
                                      @Param("armed") Boolean armed,
                                      @Param("hasCrew") Boolean hasCrew,
                                      @Param("escapeMaster") Boolean escapeMaster,
                                      Sort sort);
}
