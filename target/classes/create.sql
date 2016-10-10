CREATE TABLE budget
(
    bud_id INTEGER PRIMARY KEY NOT NULL,
    bud_description VARCHAR(255),
    bud_category VARCHAR(255),
    bud_budgeted_amount NUMERIC,
    bud_actual_amount NUMERIC
);
CREATE UNIQUE INDEX budget_bud_id_uindex ON budget (bud_id);

CREATE SEQUENCE BUDGET_SEQ;