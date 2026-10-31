package com.spacebounty.repository;

import com.spacebounty.model.HuntLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HuntLogRepository extends JpaRepository<HuntLog, Long> {
    List<HuntLog> findByCriminal_Id(Long criminalId);
}
