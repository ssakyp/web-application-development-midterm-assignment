package com.spacebounty.web;

import com.spacebounty.model.HuntLog;
import com.spacebounty.model.WantedCriminal;
import com.spacebounty.repository.HuntLogRepository;
import com.spacebounty.repository.WantedCriminalRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hunts")
public class HuntLogController {
    private final HuntLogRepository huntLogs;
    private final WantedCriminalRepository criminals;

    public HuntLogController(HuntLogRepository huntLogs, WantedCriminalRepository criminals) {
        this.huntLogs = huntLogs;
        this.criminals = criminals;
    }

    @GetMapping({"", "/"})
    public List<HuntLog> list(@RequestParam(name = "criminal_id", required = false) Long criminalId) {
        return criminalId == null ? huntLogs.findAll() : huntLogs.findByCriminal_Id(criminalId);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public HuntLog get(@PathVariable Long id) {
        return findHunt(id);
    }

    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public HuntLog create(@Valid @RequestBody HuntLogRequest request) {
        WantedCriminal criminal = criminals.findById(request.criminalId())
                .orElseThrow(() -> new ResourceNotFoundException("Criminal " + request.criminalId() + " was not found"));
        return huntLogs.save(new HuntLog(criminal, request.success(), request.notes()));
    }

    @PatchMapping({"/{id}", "/{id}/"})
    public HuntLog update(@PathVariable Long id, @Valid @RequestBody HuntLogPatchRequest request) {
        if (request.success() == null && request.notes() == null) {
            throw new IllegalArgumentException("At least one of success or notes is required");
        }
        HuntLog hunt = findHunt(id);
        if (request.success() != null) hunt.setSuccess(request.success());
        if (request.notes() != null) hunt.setNotes(request.notes());
        return huntLogs.save(hunt);
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        huntLogs.delete(findHunt(id));
    }

    private HuntLog findHunt(Long id) {
        return huntLogs.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hunt log " + id + " was not found"));
    }
}
