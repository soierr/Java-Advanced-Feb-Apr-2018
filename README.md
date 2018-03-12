# flowergarden

Application is for flower bouquets managing and viewing

`src/main` - apllication source code  
`src/test` - application JUnit, Mockito tests  
`resources` - any application related files such as: sql scripts, properties, etc


Content of `resources:`

* `sql-scripts.txt` - contains all the sql scripts used in application

* `sql-dml-sample.txt` - sample db data used for app demonstration and tests, contains inserts to db such as flowers, bouquet templates, bouquets

* `sql-ddl-create.txt` - contains the sql ddl instructions 
used to create all db object needed for application

* `sql-ddl-drop.txt` - contains the sql ddl instructions 
used to drop all db object when tests is finished. See [Test Strategy](./README.md#test-strategy) &nbsp;for details

### DB Used

`flowegarden.db` - located in root of this project, used by running application
  
`flowegarden-dev.db` - temporarily used database created only for the time of running tests. See [Test Strategy](./README.md#test-strategy) &nbsp;for details

### Test strategy

In order to supply all db related test with connection `CreateDropDbObjectsRule` has been created
Therefore, include `@Rule` to use it or there is test `Suite` might be created with every test incorporated so
`@ClassRule` may be used to avoid connection creation every time a `@Test` launched. In that case you
need to be aware that you have all the data needed for tests, which might be deleted previously 

`CreateDropDbObjectsRule` creates db from scratch using `sql-ddl-create.txt` located in `resource` then the
sample data inserted with help of `sql-dml-sample.txt`. Eventually, `flowegarden-dev.db` is created.
 Once the tests are finished the rule cleans db invoking `sql-ddl-drop.txt` and removes `flowegarden-dev.db`


### Application Launching

To run the application invoke `java -jar flowergarden-1.0.jar` from project root folder

Example output:

    ********************************************************************************************
    
                                      Flower Garden Application
    
                      Application is for flower bouquets managing and viewing
    
    ********************************************************************************************
    
    
    ------------------------------------------------------------------------------
    
    | Id  |      Name      |   Price of assembling    |       Total Price        |
    
    ------------------------------------------------------------------------------
    |  1  |   First Date   |           105            |           555            |
    |  2  |    Wedding     |            0             |            0             |
    ------------------------------------------------------------------------------

If DB sample data or/and `flowegarden.db` has been lost run `CreateDbAndSampleData` to recreate it
