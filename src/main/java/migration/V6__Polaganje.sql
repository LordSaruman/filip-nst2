/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  filip_000
 * Created: Apr 23, 2018
 */

CREATE TABLE polaganje (
    PolaganjeId INTEGER (10) NOT NULL,
    BrInd VARCHAR(10) NOT NULL,
    SifraIspita INTEGER(10) NOT NULL,
    SifraIspitnogRoka INTEGER(10) NOT NULL,
    Ocena INTEGER(10) NOT NULL,
    Datum DATE NOT NULL,
    PRIMARY KEY (PolaganjeId, BrInd, SifraIspita, SifraIspitnogRoka)
);

ALTER TABLE polaganje ENGINE = InnoDB;

ALTER TABLE polaganje MODIFY COLUMN PolaganjeId INT NOT NULL AUTO_INCREMENT;


ALTER TABLE polaganje ADD CONSTRAINT fk_BrInd FOREIGN KEY (BrInd) REFERENCES student(BrInd);

ALTER TABLE polaganje ADD CONSTRAINT fk_SifraIspita FOREIGN KEY (SifraIspita) REFERENCES ispit(SifraIspita);

ALTER TABLE polaganje ADD CONSTRAINT fk_SifraIspitnogRoka FOREIGN KEY (SifraIspitnogRoka) REFERENCES ispitnirok(SifraIspitnogRoka);

INSERT INTO polaganje (BrInd, SifraIspita, SifraIspitnogRoka, Ocena, Datum) VALUES ('2017/3713', 1, 2, 10, '2017-02-28'), ('2017/3713', 2, 2, 10, '2017-02-28');