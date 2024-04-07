# Proovitoo Helmes

    Tehtud kasutades java21, spring boot 3, Thymeleaf ja Postgresql

## Paigaldusjuhend

Rakenduse konfiguratsioon on kirjeldatud .properties failis kaustas /resources/application.properties.

### Application.properties file

Muuta ära keycloacki realmi ühenduse parameetrid ning port, millel rakendus jookseb.

Lisada andmebaasi ühenduse parameetrid. 

## Andmebaas

Andmebaasi scriptid asuvad db folderis. 

### Liquibase

Liquibase.properties faili lisada andmebaasi ühenduse parameetrid ja rakenduse kasutaja. 

### Keycloack

Kasutajate autentimiseks kasutab rakendus keycloacki, selleks tuleb luua keycloacki realm ning client.
Kasutaja info jaoks kasutab rakendus keycloacki poolt antud "preferred username". 

## Rakendus

Rakenduse pealeht asub url "/home"

