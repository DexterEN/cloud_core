-- Insert into t_location if it does not exist based on postal_code
INSERT INTO t_location (city, address, postal_code)
SELECT 'Stockholm City', 'Stockholm vägen 1', '116 60'
WHERE NOT EXISTS (
    SELECT 1 FROM t_location WHERE postal_code = '116 60'
);

INSERT INTO t_organization (name, location_id)
SELECT 'Stockholm AB', LAST_INSERT_ID()
WHERE NOT EXISTS (
    SELECT 1 FROM t_organization WHERE name = 'Stockholm AB'
);

INSERT INTO t_practitioner (name, date_of_birth, role, email, organization_id)
SELECT 'test', '1980-01-01 00:00:00', 'DOCTOR', 'test@gmail.com', LAST_INSERT_ID()
WHERE NOT EXISTS (
    SELECT 1 FROM t_practitioner WHERE email = 'test@gmail.com'
);

INSERT INTO t_patient (gender, name, date_of_birth, email)
SELECT 'MALE', 'patient', '2000-01-01 00:00:00', 'patient@gmail.com'
WHERE NOT EXISTS (
    SELECT 1 FROM t_patient WHERE email = 'patient@gmail.com'
);
