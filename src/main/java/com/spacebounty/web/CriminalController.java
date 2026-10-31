package com.spacebounty.web;

import com.spacebounty.model.WantedCriminal;
import com.spacebounty.repository.WantedCriminalRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/criminals")
public class CriminalController {
    private final WantedCriminalRepository repository;

    public CriminalController(WantedCriminalRepository repository) {
        this.repository = repository;
    }

    @GetMapping({"", "/"})
    public List<WantedCriminal> list(@RequestParam(name = "min_reward", required = false) BigDecimal minReward,
                                    @RequestParam(name = "max_reward", required = false) BigDecimal maxReward,
                                    @RequestParam(required = false) Boolean armed,
                                    @RequestParam(name = "has_crew", required = false) Boolean hasCrew,
                                    @RequestParam(name = "escape_master", required = false) Boolean escapeMaster,
                                    @RequestParam(required = false, defaultValue = "reward") String sort) {
        if (!sort.equals("reward") && !sort.equals("-reward")) {
            throw new IllegalArgumentException("sort must be reward or -reward");
        }
        Sort ordering = Sort.by(sort.startsWith("-") ? Sort.Direction.DESC : Sort.Direction.ASC, "reward");
        return repository.findFiltered(minReward, maxReward, armed, hasCrew, escapeMaster, ordering);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public WantedCriminal get(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Criminal " + id + " was not found"));
    }
}
