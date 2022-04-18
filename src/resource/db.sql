--- CREATE DB
-- CREATE DATABASE DEFAULT CHARACTER SET = 'utf8';
-- CREATE TABLE can_bo (
--   `id` INT NOT NULL,
--   `ten` VARCHAR(45) NULL,
--   `tuoi` INT NULL,
--   `gioi_tinh` VARCHAR(45) NULL,
--   `dia_chi` VARCHAR(45) NULL,
--   PRIMARY KEY (`id`))
-- ENGINE = InnoDB
-- DEFAULT CHARACTER SET = utf8
-- COLLATE = utf8_swedish_ci;
CREATE TABLE cong_nhan (
  can_bo_id INT NOT NULL,
  bac INT NOT NULL,
  PRIMARY KEY (can_bo_id)
);
ALTER TABLE
  cong_nhan
ADD
  CONSTRAINT FK_congnhan_canbo FOREIGN KEY (can_bo_id) REFERENCES can_bo (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE
  ky_su
ADD
  CONSTRAINT FK_kysu_canbo FOREIGN KEY (can_bo_id) REFERENCES can_bo (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE
  nhan_vien
ADD
  CONSTRAINT FK_nhanvien_canbo FOREIGN KEY (can_bo_id) REFERENCES can_bo (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
CREATE TABLE ky_su (
    can_bo_id INT NOT NULL,
    nghanh_dao_tao VARCHAR(45) NOT NULL,
    PRIMARY KEY (can_bo_id)
  );
CREATE TABLE nhan_vien (
    can_bo_id INT NOT NULL,
    cong_viec VARCHAR(45) NOT NULL,
    PRIMARY KEY (can_bo_id)
  );
--- ADD
INSERT INTO
  can_bo
VALUES(1, 'Vu Tien Dat', 20, 'Nam', 'Quan 7');
INSERT INTO
  can_bo
VALUES(2, 'Phan Thi Ha Vy', 20, 'Nu', 'Go Vap');
INSERT INTO
  can_bo
VALUES(3, 'Nguyen Thi Phuong Anh', 20, 'Nu', 'Ha Noi');
INSERT INTO
  can_bo
VALUES(4, 'Nguyen Van A', 22, 'Nam', 'Binh Duong');
SELECT
  *
FROM
  can_bo;
INSERT INTO
  ky_su
VALUES(1, 'Phan mem');
INSERT INTO
  nhan_vien
VALUES(2, 'Sale');
INSERT INTO
  ky_su
VALUES(3, 'Phan mem');
INSERT INTO
  cong_nhan
VALUES(4, 5);
SELECT
  *
FROM
  ky_su;
SELECT
  *
FROM
  nhan_vien;
SELECT
  *
FROM
  cong_nhan;
SELECT
  cb.id id,
  cb.ten ten,
  cb.tuoi tuoi,
  cb.gioi_tinh gioi_tinh,
  cb.dia_chi dia_chi,
  ks.nghanh_dao_tao nghanh_dao_tao
FROM
  can_bo cb
  INNER JOIN ky_su ks ON cb.id = ks.can_bo_id;
SELECT
  cb.id id,
  cb.ten ten,
  cb.tuoi tuoi,
  cb.gioi_tinh gioi_tinh,
  cb.dia_chi dia_chi,
  nv.cong_viec cong_viec
FROM
  can_bo cb
  INNER JOIN nhan_vien nv ON cb.id = nv.can_bo_id;
SELECT
  cb.id id,
  cb.ten ten,
  cb.tuoi tuoi,
  cb.gioi_tinh gioi_tinh,
  cb.dia_chi dia_chi,
  cn.bac bac
FROM
  can_bo cb
  INNER JOIN cong_nhan cn ON cb.id = cn.can_bo_id;
--- READ
SELECT
  *
FROM
  can_bo;
--- UPDATE
UPDATE
  can_bo
SET
  tuoi = 22
WHERE
  id = 1;
--- DELETE
DELETE FROM
  can_bo
WHERE
  id = 1;