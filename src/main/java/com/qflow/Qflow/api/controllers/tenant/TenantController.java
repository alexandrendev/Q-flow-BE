package com.qflow.Qflow.api.controllers.tenant;

import com.qflow.Qflow.api.requests.tenant.CreateNewTenantRequest;
import com.qflow.Qflow.core.entity.tenant.Tenant;
import com.qflow.Qflow.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenants")
public class TenantController {

    private TenantService service;


    @PostMapping("/create")
    public ResponseEntity<Tenant> createNewTenant(@RequestBody CreateNewTenantRequest request) {
        var tenant = service.save(request);

        return tenant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Autowired
    public TenantController(TenantService service) {
        this.service = service;
    }
}
