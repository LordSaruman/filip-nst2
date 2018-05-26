/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  filip_000
 * Created: Apr 23, 2018
 */

CREATE TABLE ispitnirok (
    SifraIspitnogRoka INTEGER(10) NOT NULL,
    NazivIspitnogRoka VARCHAR(20) NOT NULL,
    PRIMARY KEY (SifraIspitnogRoka)
);

ALTER TABLE ispitnirok ENGINE = InnoDB;

INSERT INTO ispitnirok (SifraIspitnogRoka, NazivIspitnogRoka) VALUES (1, 'Januar'), (2, 'Februar'), (3, 'Mart'), (4, 'Maj'), (5, 'Jun'), (6, 'Septembar');