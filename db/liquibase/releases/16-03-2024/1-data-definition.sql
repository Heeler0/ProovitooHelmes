CREATE TABLE sectors_classifiers (
    id  SERIAL PRIMARY KEY,
    sector_name  VARCHAR NOT NULL
)

CREATE TABLE user_info (
    id  SERIAL PRIMARY KEY,
    user_name VARCHAR NOT NULL,
    agree_to_terms BOOLEAN NOT NULL DEFAULT TRUE
    sector_id INT,
    CONSTRAINT fk_user_info_sector_id
        FOREIGN KEY (sector_id)
            REFERENCES sectors_classifiers(id)
)