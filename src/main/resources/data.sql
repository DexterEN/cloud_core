-- Insert into t_location if it does not exist based on postal_code
INSERT INTO t_location (city, address, postal_code)
SELECT 'Stockholm City', 'Stockholm vägen 1', '116 60'
FROM DUAL
WHERE NOT EXISTS (
    SELECT 1 FROM t_location WHERE postal_code = '116 60'
);

-- Insert into t_organization if it does not exist based on name
-- Using LAST_INSERT_ID() to refer to the recently inserted location
INSERT INTO t_organization (name, location_id)
SELECT 'Stockholm AB', LAST_INSERT_ID()
FROM DUAL
WHERE NOT EXISTS (
    SELECT 1 FROM t_organization WHERE name = 'Stockholm AB'
);

-- Insert into t_practitioner if it does not exist based on email
INSERT INTO t_practitioner (name, date_of_birth, role, email, organization_id)
SELECT 'test', '1980-01-01 00:00:00', 'DOCTOR', 'test@gmail.com', LAST_INSERT_ID()
FROM DUAL
WHERE NOT EXISTS (
    SELECT 1 FROM t_practitioner WHERE email = 'test@gmail.com'
);

-- Insert into t_patient if it does not exist based on email
INSERT INTO t_patient (gender, name, date_of_birth, email)
SELECT 'MALE', 'patient', '2000-01-01 00:00:00', 'patient@gmail.com'
FROM DUAL
WHERE NOT EXISTS (
    SELECT 1 FROM t_patient WHERE email = 'patient@gmail.com'
);

