package com.qflow.Qflow.api.requests.tenant;

import com.qflow.Qflow.core.entity.tenant.Tenant;

public record CreateNewTenantRequest(
        String name,
        String CNPJ,
        String city
) {

    public Tenant toTenant(){
        Tenant tenant = new Tenant();
        tenant.setName(this.name);
        tenant.setCNPJ(this.CNPJ);
        tenant.setCity(this.city);
        return tenant;
    }
}
