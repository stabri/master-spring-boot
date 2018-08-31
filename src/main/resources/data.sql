INSERT INTO user VALUES (1001, sysdate(), 'Paul', 'default');
INSERT INTO user VALUES (1002, sysdate(), 'Adam', 'default');
INSERT INTO user VALUES (1003, sysdate(), 'Jil', 'default');

INSERT INTO post VALUES (1101, 'First post id db user 1002', 1002);
INSERT INTO post VALUES (1102, 'Second post id db user 1002', 1002);
INSERT INTO post VALUES (1103, 'First post id db of user 1003', 1003);