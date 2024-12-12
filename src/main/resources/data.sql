-- Insert into t_location (assuming 'postalCode' is mapped to 'postal_code' in the database)
INSERT INTO t_location (city, address, postal_code)
VALUES ('Stockholm City', 'Stockholm vägen 1', '116 60');

-- Insert into t_organization (assuming 'location_id' is the foreign key to t_location)
INSERT INTO t_organization (name, location_id)
VALUES ('Stockholm AB', LAST_INSERT_ID());

-- Insert into t_practitioner (assuming 'user_email' is the email field in t_practitioner and 'role' is an ENUM type)
INSERT INTO t_practitioner (name, date_of_birth, role, email, organization_id)
VALUES ('test', '1980-01-01 00:00:00', 'DOCTOR', 'test@gmail.com', LAST_INSERT_ID());

-- Insert into t_patient (assuming 'user_email' is the email field in t_patient)
INSERT INTO t_patient (gender, name, date_of_birth, email)
VALUES ('MALE', 'patient', '2000-01-01 00:00:00', 'patient@gmail.com');

