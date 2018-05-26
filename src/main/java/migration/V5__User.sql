/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  filip_000
 * Created: Apr 23, 2018
 */

CREATE TABLE user (
    UserId INTEGER(10) NOT NULL,
    Username VARCHAR(20) NOT NULL,
    Password VARCHAR(20) NOT NULL,
    PRIMARY KEY (UserId)
);

ALTER TABLE user ENGINE = InnoDB;

INSERT INTO user (UserId, Username, Password) VALUES (1, 'filip', 'filip'), (2, 'marko', 'marko');