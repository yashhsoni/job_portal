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

-- Create roles table
CREATE TABLE IF NOT EXISTS roles (
                                     id          BIGSERIAL PRIMARY KEY,
                                     name        VARCHAR(50) NOT NULL UNIQUE,
                                     created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                     created_by  VARCHAR(20) NOT NULL,
                                     updated_at  TIMESTAMP NULL,
                                     updated_by  VARCHAR(20)
);

--Create user table
CREATE TABLE IF NOT EXISTS users (
                                     id              BIGSERIAL PRIMARY KEY,
                                     name            VARCHAR(255) NOT NULL,
                                     email           VARCHAR(255) NOT NULL UNIQUE,
                                     password_hash   VARCHAR(500) NOT NULL,
                                     mobile_number   VARCHAR(20) UNIQUE,
                                     role_id         BIGINT NOT NULL,
                                         company_id      BIGINT,
                                     created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                     created_by      VARCHAR(20) NOT NULL,
                                     updated_at      TIMESTAMP,
                                     updated_by      VARCHAR(20),
                                     CONSTRAINT fk_users_role
                                         FOREIGN KEY (role_id) REFERENCES roles(id),

                                     CONSTRAINT fk_users_company
                                         FOREIGN KEY (company_id) REFERENCES companies(id)
                                             ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS profiles (
                                        id BIGSERIAL PRIMARY KEY,
                                        user_id BIGINT NOT NULL,
                                        job_title VARCHAR(255) NOT NULL,
                                        location VARCHAR(255) NOT NULL,
                                        experience_level VARCHAR(50) NOT NULL,
                                        professional_bio TEXT NOT NULL,
                                        portfolio_website VARCHAR(500),
                                        profile_picture BYTEA,
                                        profile_picture_name VARCHAR(255),
                                        profile_picture_type VARCHAR(100),
                                        resume BYTEA,
                                        resume_name VARCHAR(255),
                                        resume_type VARCHAR(100),
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                        created_by VARCHAR(20) NOT NULL,
                                        updated_at TIMESTAMP DEFAULT NULL,
                                        updated_by VARCHAR(20) DEFAULT NULL,
                                        CONSTRAINT fk_profile_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS saved_jobs (
                                          user_id BIGINT NOT NULL,
                                          job_id  BIGINT NOT NULL,
                                          PRIMARY KEY (user_id, job_id),
                                          CONSTRAINT fk_saved_jobs_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                          CONSTRAINT fk_saved_jobs_job FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS job_applications (
                                                id BIGSERIAL PRIMARY KEY,
                                                user_id BIGINT NOT NULL,
                                                job_id BIGINT NOT NULL,
                                                applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                                status VARCHAR(50) DEFAULT 'PENDING' NOT NULL,
                                                cover_letter TEXT,
                                                notes TEXT,
                                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                                created_by VARCHAR(20) NOT NULL,
                                                updated_at TIMESTAMP DEFAULT NULL,
                                                updated_by VARCHAR(20) DEFAULT NULL,
                                                FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                                FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE CASCADE,
                                                CONSTRAINT unique_user_job_application UNIQUE (user_id, job_id)
);