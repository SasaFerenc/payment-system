INSERT INTO firm(firm_name) VALUES ('FirmaA');
INSERT INTO firm(firm_name) VALUES ('FirmaB');

INSERT INTO account(count_number, reserved, total, firm_id) VALUES ('200111', 0, 100, (SELECT firm_id FROM firm WHERE firm_name='FirmaA'));
--INSERT INTO account(count_number, reserved, total, firm_id) VALUES ('908111', 0, 100, (SELECT firm_id FROM firm WHERE firm_name='FirmaB'));

INSERT INTO bank(name, account_number, swift_code) VALUES ('BankA', '200000000012345600', 'HAABRSBG');
INSERT INTO bank(name, account_number, swift_code) VALUES ('BankB', '908000000001550135', 'AIKBRS22');
