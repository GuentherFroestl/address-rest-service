/**
 * Fulltext search addons to create ddl. 
 * Author:  gfr
 * Created: 31.03.2016
 */

ALTER TABLE BUILDING ADD COLUMN TSV tsvector;
CREATE TRIGGER tsv_update_trigger BEFORE INSERT OR UPDATE
ON BUILDING FOR EACH ROW EXECUTE PROCEDURE
tsvector_update_trigger(tsv, 'pg_catalog.english', FULL_TEXT_SEARCH);