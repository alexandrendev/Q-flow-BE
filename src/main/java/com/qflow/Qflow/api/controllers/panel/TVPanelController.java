package com.qflow.Qflow.api.controllers.panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tv-panel")
public class TVPanelController {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @GetMapping("/dashboard/tv")
    public String tvDashboard(@RequestParam(name = "tenantId", required = false) Long tenantId, Model model) {
        if (tenantId == null) tenantId = 1L;

        String sqlWaiting = "SELECT COUNT(*) FROM triage_queue WHERE status = 'WAITING' AND tenant_id = :tenantId";
        String sqlPrioritized = "SELECT COUNT(*) FROM triage_queue tq " +
                "JOIN patients p ON p.id = tq.patient_id " +
                "WHERE tq.status = 'WAITING' AND p.priority IS NOT NULL AND tq.tenant_id = :tenantId";

        MapSqlParameterSource params = new MapSqlParameterSource("tenantId", tenantId);

        Long waitingCount = jdbcTemplate.queryForObject(sqlWaiting, params, Long.class);
        Long prioritizedCount = jdbcTemplate.queryForObject(sqlPrioritized, params, Long.class);

        model.addAttribute("waitingCount", waitingCount != null ? waitingCount : 0L);
        model.addAttribute("prioritizedCount", prioritizedCount != null ? prioritizedCount : 0L);
        model.addAttribute("tenantId", tenantId);

        return "dashboard/tv-panel";
    }

    @Autowired
    public TVPanelController(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
