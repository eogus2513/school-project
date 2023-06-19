INSERT INTO tbl_link (id, link, title, user_id, created_at, expired_at)
VALUES (1, 'https://www.google.com', '구글', '31393036-3038-3364-2d30-6535352d3436', time (now()),
        DATE_ADD(NOW(), INTERVAL 5 DAY)) ON DUPLICATE KEY
UPDATE expired_at = DATE_ADD(NOW(), INTERVAL 5 DAY);

INSERT INTO tbl_link (id, link, title, user_id, created_at, expired_at)
VALUES (2, 'https://www.naver.com', '네이버', '31393036-3038-3364-2d30-6535352d3436', time (now()),
        DATE_ADD(NOW(), INTERVAL 5 DAY)) ON DUPLICATE KEY
UPDATE expired_at = DATE_ADD(NOW(), INTERVAL 5 DAY);