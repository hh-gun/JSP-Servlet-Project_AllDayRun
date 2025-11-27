SELECT * FROM tblTestUser;

DELETE from tbltestuser;

DROP TABLE tblTestUser;

CREATE TABLE tblTestUser (
    id        VARCHAR2(30) PRIMARY KEY,   -- 아이디
    pw        VARCHAR2(30) NOT NULL,      -- 비밀번호
    name      VARCHAR2(50) NOT NULL,      -- 이름
    nickname  VARCHAR2(50)                -- 닉네임
);

INSERT INTO tblTestUser (id, pw, name, nickname)
VALUES ('test01', '1234', '홍건', '홍홍홍');

commit;