/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  filip_000
 * Created: Apr 23, 2018
 */

CREATE TABLE student (
    BrInd VARCHAR(10) NOT NULL,
    Ime VARCHAR(30) NOT NULL,
    Prezime VARCHAR(30) NOT NULL,
    Ptt INTEGER(10) NOT NULL,
    PRIMARY KEY (BrInd),
    FOREIGN KEY (Ptt) REFERENCES mesto(Ptt)
);

ALTER TABLE student ENGINE = InnoDB;

INSERT INTO student (BrInd, Ime, Prezime, Ptt) VALUES ('2017/3713', 'Filip', 'Ivanovic', 12223), ('2017/3701', 'Marko', 'Markovic', 11000), ('2017/3704', 'Pera', 'Peric', 18000), ('2017/3705', 'Ivana', 'Ivanovic', 11000), ('2017/3706', 'Zika', 'Zivkovic', 34000);