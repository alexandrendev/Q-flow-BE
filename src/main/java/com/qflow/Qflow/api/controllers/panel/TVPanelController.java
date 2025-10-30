package com.qflow.Qflow.api.controllers.panel;

import com.qflow.Qflow.core.ports.PrioritiesQueueRepository;
import com.qflow.Qflow.core.ports.TriageQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tv-panel")
public class TVPanelController {

    private final TriageQueueRepository triageQueueRepository;
    private final PrioritiesQueueRepository prioritiesQueueRepository;



    @GetMapping("/dashboard/tv")
    public String tvDashboard(@RequestParam(name = "tenantId", required = false) Long tenantId, Model model) {
        // Se tenantId não for informado, assumir 1 (ajuste conforme necessidade)
        if (tenantId == null) tenantId = 1L;

        String sqlWaiting = "SELECT COUNT(*) FROM triage_queue WHERE status = 'WAITING' AND tenant_id = :tenantId";
        String sqlPrioritized = "SELECT COUNT(*) FROM triage_queue tq " +
                "JOIN patients p ON p.id = tq.patient_id " +
                "WHERE tq.status = 'WAITING' AND p.manchester_priority IS NOT NULL AND tq.tenant_id = :tenantId";

        MapSqlParameterSource params = new MapSqlParameterSource("tenantId", tenantId);

        Long waitingCount = jdbcTemplate.queryForObject(sqlWaiting, params, Long.class);
        Long prioritizedCount = jdbcTemplate.queryForObject(sqlPrioritized, params, Long.class);

        model.addAttribute("waitingCount", waitingCount != null ? waitingCount : 0L);
        model.addAttribute("prioritizedCount", prioritizedCount != null ? prioritizedCount : 0L);
        model.addAttribute("tenantId", tenantId);

        return "dashboard/tv";
    }

    @Autowired
    public TVPanelController(
            TriageQueueRepository triageQueueRepository,
            PrioritiesQueueRepository prioritiesQueueRepository
    ) {
        this.triageQueueRepository = triageQueueRepository;
        this.prioritiesQueueRepository = prioritiesQueueRepository;
    }

}
