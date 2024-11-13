
# About

Application demo simulating stock exchange market, it's allowing users to add and remove (fictional) orders.

## Technologies

<ul>
<li>Java 22.0.1</li>
<li>Spring Boot 3.3.5</li>
<li>Thymeleaf 3.1.2</li>
</ul>


## Local enviromnent setup

To start the server:
 1.  Download project
 2. In command line navigate to your project directory
 3. Run command  `./mvnw spring-boot:run`

Alternatively, run program in your IDE.

## Usage

After starting the server, type [localhost:8080](localhost:8080) in browser to open a table. Then it's possible to add new stock exchange order or lower the quantity of previously added order by clicking Remove button. After saving, user is being redirected to the site, where order with best (lowest) buy price and the highest sell price are displayed. 

Application is cleared from added data every restart, so after restarting only predefined rows will be shown.

Application is also hosted on a private server, to access it, navigate to [this URL](185.181.10.145:8080).
