-- Create companies table
CREATE TABLE IF NOT EXISTS companies (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    logo VARCHAR(500),
    industry VARCHAR(100) NOT NULL,
    size VARCHAR(50) NOT NULL,
    rating DECIMAL(3,2) NOT NULL,
    locations VARCHAR(1000),
    founded INT NOT NULL,
    description TEXT,
    employees INT,
    website VARCHAR(500),
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(20)  NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NULL,
    updated_by  VARCHAR(20) DEFAULT NULL
    );

-- Contact Table
CREATE TABLE IF NOT EXISTS contacts (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    user_type VARCHAR(50) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'NEW',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(20) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
    );

-- Job table
CREATE TABLE IF NOT EXISTS jobs
(
    id                   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title                VARCHAR(255)   NOT NULL,
    company_id           BIGINT         NOT NULL,
    location             VARCHAR(255)   NOT NULL,
    work_type            VARCHAR(50)    NOT NULL,                  -- On-site, Remote, Hybrid
    job_type             VARCHAR(50)    NOT NULL,                  -- Full-time, Part-time, Contract, Freelance
    category             VARCHAR(100)   NOT NULL,                  -- Technology, Design, Marketing, Sales, Finance, Healthcare, Education, Operations
    experience_level     VARCHAR(50)    NOT NULL,                  -- Entry Level, Mid Level, Senior Level, Executive Level
    salary_min           NUMERIC(12, 2) NOT NULL,
    salary_max           NUMERIC(12, 2) NOT NULL,
    salary_currency      VARCHAR(10)    NOT NULL DEFAULT 'USD',
    salary_period        VARCHAR(20)    NOT NULL DEFAULT 'year',
    description          TEXT           NOT NULL,
    requirements         TEXT,                                     -- JSON array stored as text
    benefits             TEXT,                                     -- JSON array stored as text
    posted_date          TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    application_deadline TIMESTAMP,
    applications_count   INT            NOT NULL DEFAULT 0,
    featured             BOOLEAN        NOT NULL DEFAULT FALSE,
    urgent               BOOLEAN        NOT NULL DEFAULT FALSE,
    remote               BOOLEAN        NOT NULL DEFAULT FALSE,
    status               VARCHAR(20)    NOT NULL DEFAULT 'ACTIVE', -- ACTIVE, CLOSED, DRAFT
    created_at           TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by           VARCHAR(20)    NOT NULL,
    updated_at           TIMESTAMP               DEFAULT NULL,
    updated_by           VARCHAR(20)             DEFAULT NULL,
    FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE
);
-- Create companies table
-- CREATE TABLE IF NOT EXISTS companies (
--                                          id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
--                                          name VARCHAR(255) NOT NULL UNIQUE,
--                                          logo VARCHAR(500),
--                                          industry VARCHAR(100) NOT NULL,
--                                          size VARCHAR(50) NOT NULL,
--                                          rating DECIMAL(3,2) NOT NULL,
--                                          locations VARCHAR(1000),
--                                          founded INT NOT NULL,
--                                          description TEXT,
--                                          employees INT,
--                                          website VARCHAR(500),
--                                          created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP NOT NULL,
--                                          created_by  VARCHAR(20)  NOT NULL,
--                                          updated_at  TIMESTAMP   DEFAULT NULL,
--                                          updated_by  VARCHAR(20) DEFAULT NULL
-- );
--
-- -- Contact Table
-- CREATE TABLE IF NOT EXISTS contacts (
--                                         id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
--                                         name VARCHAR(255) NOT NULL,
--                                         email VARCHAR(255) NOT NULL,
--                                         user_type VARCHAR(50) NOT NULL,
--                                         subject VARCHAR(255) NOT NULL,
--                                         message TEXT NOT NULL,
--                                         status VARCHAR(20) NOT NULL DEFAULT 'NEW',
--                                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
--                                         created_by VARCHAR(20) NOT NULL,
--                                         updated_at TIMESTAMP DEFAULT NULL,
--                                         updated_by VARCHAR(20) DEFAULT NULL
-- );
--
-- -- Job Table
-- CREATE TABLE IF NOT EXISTS jobs
-- (
--     id                   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
--     title                VARCHAR(255)   NOT NULL,
--     company_id           BIGINT         NOT NULL,
--     location             VARCHAR(255)   NOT NULL,
--     work_type            VARCHAR(50)    NOT NULL,
--     job_type             VARCHAR(50)    NOT NULL,
--     category             VARCHAR(100)   NOT NULL,
--     experience_level     VARCHAR(50)    NOT NULL,
--     salary_min           NUMERIC(12, 2) NOT NULL,
--     salary_max           NUMERIC(12, 2) NOT NULL,
--     salary_currency      VARCHAR(10)    NOT NULL DEFAULT 'USD',
--     salary_period        VARCHAR(20)    NOT NULL DEFAULT 'year',
--     description          TEXT           NOT NULL,
--     requirements         TEXT,
--     benefits             TEXT,
--     posted_date          TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
--     application_deadline TIMESTAMP,
--     applications_count   INT            NOT NULL DEFAULT 0,
--     featured             BOOLEAN        NOT NULL DEFAULT FALSE,
--     urgent               BOOLEAN        NOT NULL DEFAULT FALSE,
--     remote               BOOLEAN        NOT NULL DEFAULT FALSE,
--     status               VARCHAR(20)    NOT NULL DEFAULT 'ACTIVE',
--     created_at           TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
--     created_by           VARCHAR(20)    NOT NULL,
--     updated_at           TIMESTAMP               DEFAULT NULL,
--     updated_by           VARCHAR(20)             DEFAULT NULL,
--     FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE
-- );
--
-- -----------------------------------------------------
-- -- BOOLEAN AUTO‑CONVERSION (1 → TRUE, 0 → FALSE)
-- -----------------------------------------------------
--
-- -- Step 1: Create Function
-- CREATE OR REPLACE FUNCTION convert_bool()
--     RETURNS trigger AS $$
-- BEGIN
--     -- Convert numeric values to boolean
--     NEW.featured := NEW.featured = 1;
--     NEW.urgent := NEW.urgent = 1;
--     NEW.remote := NEW.remote = 1;
--     RETURN NEW;
-- END;
-- $$ LANGUAGE plpgsql;
--
-- -- Step 2: Create Trigger for jobs table
-- CREATE TRIGGER jobs_bool_trigger
--     BEFORE INSERT OR UPDATE ON jobs
--     FOR EACH ROW
-- EXECUTE FUNCTION convert_bool();