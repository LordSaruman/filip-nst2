/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  filip_000
 * Created: Apr 23, 2018
 */

CREATE TABLE ispit (
    SifraIspita INTEGER(10) NOT NULL,
    NazivIspita VARCHAR(30) NOT NULL,
    PRIMARY KEY (SifraIspita)
);

ALTER TABLE ispit ENGINE = InnoDB;

INSERT INTO ispit (SifraIspita, NazivIspita) VALUES (1, 'Softverski zahtevi'), (2, 'Softverski procesi'), (3, 'Napredne soft. tehnologije'), (4, 'Napredne soft. tehnologije 2'), (5, 'Konstrukcija softvera'), (6, 'Alati i metode soft. inz.');
