if introducing new entities, then:

- comment (remove) the mulittenancy annotation from BaseEntity
    //@Multitenant(MultitenantType.TABLE_PER_TENANT)
    //@TenantTableDiscriminator(type = SCHEMA, contextProperty = BaseEntity.TENANT_ID)


- modify persistence.xml, that drop + create statements will be generated:

      <property name="eclipselink.ddl-generation" value="drop-and-create-tables"/>
      <property name="eclipselink.ddl-generation.output-mode" value="both"/>
      <property name="eclipselink.create-ddl-jdbc-file-name" value="sql-create-script.sql"/>
      <property name="eclipselink.drop-ddl-jdbc-file-name" value="sql-drop-script.sql"/>

for working with multitenancy:

- add (uncomment) the Multitenant annotation

- provide sql create scripts 

- adjust persistence.xml

      <property name="javax.persistence.schema-generation.database.action" value="drop-and-create"/>
      <property name="javax.persistence.schema-generation.create-source" value="script"/>
      <property name="javax.persistence.schema-generation.create-script-source" value="sql-create-script-1.sql"/>
      <property name="javax.persistence.schema-generation.drop-source" value="script"/>
      <property name="javax.persistence.schema-generation.drop-script-source" value="sql-drop-script-1.sql"/>