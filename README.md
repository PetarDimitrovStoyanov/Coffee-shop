# Coffee-shop

<h2>Project idea:</h2>
This is a small web application that aims to introduce potential customers to the company and offer makes direct sales if needed.
The application supports two types of roles - admin and user. The administrator can control the entire process of adding, deleting and modifying products, including determining which other user can become an administrator, as well as monitor statistics related to the operation of the application.

<h3>Short Info and Functionalities:</h3>
<ul>
<li> public part (accessible without authentication) - the non-authenticated users can see:
  <ol type="1">
  <li> Home page - it contains brief information about the company. </li>
  <li> Login page - contains a login form.</li>
  <li> Register page - contains a registration form.</li>
  <li> About page - it contains full information about the company and products. From here the admin can see the list of users and change their roles</li>
  <li> Error page - a funny "page not found 404" page pops up when someone tries to reach a non-existing page.</li>
  </ol>
</li>
<li> private part (available for logined/registered users) - it contains the following pages:
   <ol type="1">
  <li> Product page - it contains the full list of products that the company offers. From here each user can add the specific product/s to the shopping basked.</li>
  <li> Shopping cart - holds the list of chosen products for buying.</li>
      <li> Delivery page - this is a special page - form in which the user write the wanted delivery information.</li>
      <li> Payment page - it is a form in which the user choose the payment options and confirm the deal.</li>
     <li> Order done page - this is a page that inform the client that he has made the order successfully. This page trigger an email notification to the company in which is described the full info of the order (delivery, payment and etc.). On each 4th hour the application sent a SMS message to the major admin and inform him for the current turnover. Also each day at 00:00 the application delete all orders and visitations</li>
     </ol>
</li>
<li> administrator part (available for admins only):
  <ol type="1">
   <li> Admin page - it is visible only for users with admin roles. This page contains the statistic result for the orders mafe + all visitations made on the product page.</li>
     <li> Add product page - it can be accessed from the admin page and will allow to add or delete a product</li>
    <li> Edit product page - it can be accessed from the product page by clicking on a special button. It holds an option for editing.</li>
    </ol>
  </li>
</ul>
  
<hr>

<h3>Project structure:</h3>
<p>The project structure is a standard MVC which contains some specific utillity class's such as for the SMS and email notifications</p>
For this application are user CSS, HTML, JQuery, Thymeleaf and JavaScript for the Front-End and Java (Spring framework, Hibernate) for the Back-End part along with MySQL.
About the testing of the application are used JUnit5 - implemented in Unit and Integration tests.
There has been used a special library for the SMS notifications - javax.mail. The SMS notification is implemented using Twilio API.

To start the application clone the "final" repository from github, put your credentials for the MySQL DB and make an enviroment variable named "TWILIO" - the value of this variable will be given on the project defence, owing some security reasons.
To enter as an admin please use:
email: admin@abv.bg
password: asd
