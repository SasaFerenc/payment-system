INSERT INTO firm(firm_name) VALUES ('FirmaC');
INSERT INTO firm(firm_name) VALUES ('FirmaD');

INSERT INTO account(count_number, reserved, total, firm_id) VALUES ('908000', 0, 500000, (SELECT firm_id FROM firm WHERE firm_name = 'FirmaC'));
INSERT INTO account(count_number, reserved, total, firm_id) VALUES ('908111', 0, 40000, (SELECT firm_id FROM firm WHERE firm_name = 'FirmaD'));

INSERT INTO bank(name, account_number, swift_code, local) VALUES ('BankA', '200000000012345600', 'HAABRSBG', true);
INSERT INTO bank(name, account_number, swift_code, local) VALUES ('BankB', '908000000001550135', 'AIKBRS22', false);
