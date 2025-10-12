CREATE TABLE triage_queue (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    patient_id INT NOT NULL,
    tenant_id INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (tenant_id) REFERENCES tenants(id)
);