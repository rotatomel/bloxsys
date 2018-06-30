# BloxSYS
Repo para proyecto de BloxSys

Requerimientos previos:

* Java JDK >= 8
* MySQL >= 5
* Maven >= 3


## Instrucciones para ejecutar: ##

1. Descargar WildFly (http://download.jboss.org/wildfly/12.0.0.Final/wildfly-12.0.0.Final.zip) y descomprimir en algún directorio.
2. Bajar el conetor mysql (https://dev.mysql.com/downloads/connector/j/)
3. Iniciar WildFly y deployar el conector MySQL.
    ```
    sh <path_to_wildfly>/bin/standalone.sh -c standalone-full.xml
    ```
    Crear un usuario de administración con el comando:
    ```
    sh <path_to_wildfly>/bin/add-user.sh
    ```
    
    Entrar en la consola de administración (localhost:9990) y deployar el jar de MySQL.
1. Apagar WildFly. (Ctrl+C en la consola donde se lo inició)
4. Configurar el standalone-full.xml con lo siguiente:
    ```
      <subsystem xmlns="urn:jboss:domain:datasources:5.0">
                <datasources>
                .
                .
                <datasource jta="true" jndi-name="java:/BloxsysDS" pool-name="BloxsysDS" enabled="true" use-ccm="false">
                                    <connection-url>jdbc:mysql://localhost:3306/bloxsys2</connection-url>
                                    <driver-class>com.mysql.jdbc.Driver</driver-class>
                                    <driver>mysql-connector-java-5.1.46-bin.jar_com.mysql.jdbc.Driver_5_1</driver>
                                    <security>
                                        <user-name>bloxsys</user-name>
                                        <password>bloxsys</password>
                                    </security>
                                    <validation>
                                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
                                        <background-validation>true</background-validation>
                                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
                                    </validation>
                </datasource>
                 .
                 .
                 </datasources>
       </subsystem>
    ```
    Luego, dentro de:
    ```
       <security-domains>
            <security-domain name="bloxsysSD" default-realm="bloxsys-realm" permission-mapper="default-permission-mapper">
                            <realm name="bloxsys-realm" role-decoder="groups-to-roles"/>
            </security-domain>
       </security-domains>
    ```
    y dentro de:
    ```
       <security-realms>
        <jdbc-realm name="bloxsys-realm">
                            <principal-query sql="SELECT password,role FROM usuarios u join usuarios_roles ur ON ur.id_usuario = u.id_usuario WHERE u.activo=true AND u.login = ?" data-source="BloxsysDS">
                                <attribute-mapping>
                                    <attribute to="groups" index="2"/>
                                </attribute-mapping>
                                <clear-password-mapper password-index="1"/>
                            </principal-query>
         </jdbc-realm>
       </security-realms>
    ```
    Dentro de:
    ```
       <http>
       ..
       <http-authentication-factory name="bloxsys-http-auth" security-domain="bloxsysSD" http-server-mechanism-factory="global">
                           <mechanism-configuration>
                               <mechanism mechanism-name="FORM">
                                   <mechanism-realm realm-name="bloxsysSD"/>
                               </mechanism>
                           </mechanism-configuration>
       </http-authentication-factory>
        ..                   
       </http>
    ```
3. Compilar el proyecto con mvn clean install.
1. Dentro de bloxsys/bloxsys-ear/target se habrá generado el archivo bloxsys-ear.ear
1. Volver a iniciar WildFly, ingresar en la consola de administración y deployar el archivo.
1. En un navegador http://localhost:8080/bloxsys-web se podrá acceder al sistema.



