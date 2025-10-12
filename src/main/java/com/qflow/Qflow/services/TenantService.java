package com.qflow.Qflow.services;

import com.qflow.Qflow.api.requests.tenant.CreateNewTenantRequest;
import com.qflow.Qflow.core.entity.tenant.Tenant;
import com.qflow.Qflow.infra.repository.tenant.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantService {
    private TenantRepository tenantRepository;


    public Optional<Tenant> save(CreateNewTenantRequest request){
        return tenantRepository.save(
                request.toTenant()
        );
    }

    @Autowired
    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }
}
