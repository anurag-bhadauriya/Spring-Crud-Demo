CREATE TABLE credential(
	id SERIAL PRIMARY KEY,
	email_id TEXT,
	password TEXT NOT NULL
);

CREATE TABLE school(
	id SERIAL PRIMARY KEY, 
	school_name TEXT NOT NULL,
	email_id TEXT NOT NULL UNIQUE,
	credential_id INTEGER NOT NULL,
	address TEXT,
	description TEXT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	CONSTRAINT fk_school_creds
	FOREIGN KEY(credential_id)
		REFERENCES credential(id)
		ON DELETE CASCADE
);

CREATE TABLE teacher(
	id SERIAL PRIMARY KEY, 
	teacher_name TEXT NOT NULL,
	email_id TEXT NOT NULL UNIQUE,
	credential_id INTEGER NOT NULL,
	address TEXT,
	description TEXT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	CONSTRAINT fk_teacher_creds
	FOREIGN KEY(credential_id)
		REFERENCES credential(id)
		ON DELETE CASCADE
);

CREATE TABLE student(
	id SERIAL PRIMARY KEY, 
	student_name TEXT NOT NULL,
	email_id TEXT NOT NULL UNIQUE,
	credential_id INTEGER NOT NULL,
	address TEXT,
	description TEXT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	CONSTRAINT fk_student_creds
	FOREIGN KEY(credential_id)
		REFERENCES credential(id)
		ON DELETE CASCADE
);
