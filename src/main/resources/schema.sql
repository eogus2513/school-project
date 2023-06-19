CREATE TABLE IF NOT EXISTS tbl_link
(
    id         bigint auto_increment
        PRIMARY KEY,
    link       VARCHAR(1000) NOT NULL,
    title      VARCHAR(100)  NOT NULL,
    user_id    CHAR(36)      NOT NULL,
    created_at TIMESTAMP     NOT NULL,
    expired_at TIMESTAMP     NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;