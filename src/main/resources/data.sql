INSERT INTO T_Location (city, address, postalCode)
VALUES ('Stockholm City', 'Stockholm vägen 1', '116 60');

INSERT INTO T_Organization (name, location_id)
VALUES ('Stockholm AB', LAST_INSERT_ID());


INSERT INTO T_Practitioner (name, dateOfBirth, role, email,organization_id)
VALUES ('test', '1980-01-01 00:00:00', 'DOCTOR', 'test@gmail.com',LAST_INSERT_ID());

INSERT INTO T_Patient (gender, name, dateOfBirth, email)
VALUES ('MALE', 'patient', '2000-01-01 00:00:00', 'patient@gmail.com');

