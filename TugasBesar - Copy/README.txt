//SET UP SQL SERVER 
1. Create New Database di Object Explorer namakan "sRusunDB"
//Jika Belum setup SQL Server authentiaction
1. Login ke SMSS (SQL Managment Studio Server) menggunakan Windows authentication 
2. Connect pada local Database
3. Pada root folder (Database root) di Object Explorer klik kanan dan buka klik properties
4. pada bagian properties buka page Security 
5. Set Server authentiaction ke SQL Server and Windows Authentiaction mode
6. Restart Database dengan klik kanan root folder di Object Explorer dan klik "Restart" 
7. Buka subdirectory root di Object Explorer yang bernama Security dan klik kanan directory Security dan klik "New Login"
8. Gunakan SQL Server Authentiaction dengan LoginName : Administator, Password : Admin12345. Klik ok
9. Di properties administrator yang baru dibuat pilih Server Roles ke sysadmin di Database yang diinginkan
10. User Mapping db_ddladmin, aktifkan dbnya juga.



//SERVER ROLES CHEATSHEET
//SOURCE ChatGPT
These are special-purpose roles usually managed by SQL Server’s internal features or services:
Role Name	                        Description
##MS_DatabaseConnector##	        Allows connection to the database for special Azure-related authentication scenarios.
##MS_DatabaseManager##	            For managing databases in SQL Azure environments.
##MS_DefinitionReader##	            Can read definitions of all entities in the server.
##MS_LoginManager##	                Can manage logins on Azure SQL.
##MS_PerformanceDefinitionReader##	Can read performance-related definitions.
##MS_SecurityDefinitionReader##	Can read security definitions (permissions, etc.).
##MS_ServerPerformanceStateReader##	Can view performance state across the server.
##MS_ServerSecurityStateReader##	Can view server-level security state.
##MS_ServerStateManager##	        Can manage server state — like starting/stopping services.
##MS_ServerStateReader##	        Can view server state (who’s connected, resource usage, etc.).

⚠️ You likely won’t use these for local dev or apps. Mostly used in managed/cloud deployments.

⚙️ Regular SQL Server Roles
Role Name	    Description
bulkadmin	    Can run the BULK INSERT command to import data into SQL Server from files.
dbcreator	    Can create, alter, drop, and restore databases.
diskadmin	    Manages disk files for the SQL Server instance.
processadmin	Can end SQL Server processes (kill running queries or sessions).
public	        The default role every login belongs to. Has very limited access unless granted explicitly.
securityadmin	Can manage logins and their permissions — create/delete logins, grant roles.
serveradmin	    Can change server-wide configuration options and shut down the server.
setupadmin	    Can add and remove linked servers, and execute some system procedures.
sysadmin	    Full control over the entire SQL Server — super admin. Equivalent to root in Linux. 🔥 

//USER MAPPING CHEATSHEET
| Role Name           | Read Data | Write Data | Create Tables | Manage Users | Admin Access | Notes                                  |
| ------------------- | --------- | ---------- | ------------- | ------------ | ------------ | -------------------------------------- |
| `db_datareader`     | YES       | NO         | NO            | NO           | NO           | Can read all table/view data           |
| `db_datawriter`     | NO        | YES        | NO            | NO           | NO           | Can insert, update, delete data        |
| `db_ddladmin`       | NO        | NO         | YES           | NO           | NO           | Can create/alter/drop schema objects   |
| `db_owner`          | YES       | YES        | YES           | YES          | YES          | Full database control (admin)          |
| `db_accessadmin`    | NO        | NO         | NO            | YES          | NO           | Can grant/revoke DB access             |
| `db_backupoperator` | NO        | NO         | NO            | NO           | NO           | Can run database backups               |
| `db_securityadmin`  | NO        | NO         | NO            | YES          | NO           | Can manage DB-level roles/permissions  |
| `db_denydatareader` | NO        | —          | NO            | NO           | NO           | Denies all read access                 |
| `db_denydatawriter` | —         | NO         | NO            | NO           | NO           | Denies all write access                |
| `public`            | NO\*      | NO\*       | NO            | NO           | NO           | Default minimal role (inherits grants) |
