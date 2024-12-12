-- Insert into t_location if it does not exist based on postal_code
INSERT IGNORE INTO t_location (city, address, postal_code)
VALUES ('Stockholm City', 'Stockholm vägen 1', '116 60');

-- Insert into t_organization if it does not exist based on name
INSERT IGNORE INTO t_organization (name, location_id)
VALUES ('Stockholm AB', LAST_INSERT_ID());

-- Insert into t_practitioner if it does not exist based on email
INSERT IGNORE INTO t_practitioner (name, date_of_birth, role, email, organization_id)
VALUES ('test', '1980-01-01 00:00:00', 'DOCTOR', 'test@gmail.com', LAST_INSERT_ID());

-- Insert into t_patient if it does not exist based on email
INSERT IGNORE INTO t_patient (gender, name, date_of_birth, email)
VALUES ('MALE', 'patient', '2000-01-01 00:00:00', 'patient@gmail.com');
