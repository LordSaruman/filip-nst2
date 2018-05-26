/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  filip_000
 * Created: Apr 23, 2018
 */

CREATE TABLE mesto (
    Ptt INTEGER(10) NOT NULL,
    Naziv VARCHAR(40) NOT NULL,
    PRIMARY KEY (Ptt)
);

ALTER TABLE mesto ENGINE = InnoDB;

INSERT INTO mesto (Ptt, Naziv) VALUES (11000, 'Beograd'), (11070, 'Novi Beograd'), (12223, 'Golubac'), (18000, 'Nis'), (21000, 'Novi Sad'), (34000, 'Kragujevac');