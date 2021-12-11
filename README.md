# Coffee-shop
<h1>Spring Advanced - project</h1>

<h2>Project idea:</h2>
This is a small website for computer game ads. Anyone can make their own registration and start buying or selling a computer games using ads.
There are two types of roles - users and admins. The users can register, log in, create game ads, edit and delete their own ads, and use the search and filter options to find the wanted game. 
Once the game ad is found they can click on the button "make an offer" to write an email to the current ad owner and make an offer.
The admin, can make everything that the user can do, and also edit not only his own ads but of all other users too. He can see all the registered users and change some of their data (like the role, name, and email).

<h3>Short Info and Functionalities:</h3>
<ul>
<li> public part (accessible without authentication) - the non-authenticated users can see:
  <ol type="1">
  <li> Start page - it contains brief information about the web application and buttons for login and register. </li>
  <li> Login page - contains a login form.</li>
  <li> Register page - contains a registration form.</li>
  <li> Error page - "page not found 404" pops up when someone tries to reach a non-existing page.</li>
  </ol>
</li>
<li> private part (available for logined/registered users) - it contains the following pages:
   <ol type="1">
  <li> Home page - has an animated greeting message along with a carousel section that shows the website's suggestions for wanted games.</li>
  <li> All games page - it contains a search field that allows searching of a game by its name. Also, has a section for sorting by price (ascending and descending) along with a second section for filtering by category. 
                     And all that working mutual to obtain the common result (by search, sort, and filter).</li>
  <li> Create game page - holds an empty form to fill out. </li>
  <li> Edit game page - holds filled form, that contains the data of the selected game ad. The information can be amended by the user if he is a creator or has an admin role.</li>
  <li> My games page - a completely animated page that contains an interactive section that shows an image of the hovered game and a section for all games created by the currently logged user.</li>
  <li> Details page - by click on the details button of some specific game ad, the logged user will be redirected to a page that contains a section with game ad information as game name, category, price, description,
                   year of the game release, image, and creator's email address for contact.</li>
     </ol>
</li>
<li> administrator part (available for admins only):
  <ol type="1">
   <li> All users page - it is visible only for users with admin roles. This page shows all registered users and their names, emails, id's and roles in table form, which has pagination. 
Some of the columns have an option to be order reversed in order to be easy for working. Of course, that is not all, on this page is also a search section, 
that allows searching by email in order to be easier for the admin to find the person he wants. </li>
    </ol>
  </li>
  </ul>
  
<hr>

<h3>Project structure:</h3>
<ul>
<li>Components folder:
  <ol type="1">
<li>Authentication module - contains Login and Register components, both use template-driven form.</li>
<li>Landing module - Includes the following components: 
  <ul>
      <li> AllUsersComponent - it shows all the registered users on the admin via pagination table that has a search field.</li>
      <li> CarouselNavigationComponent - it is part of a home page. It is a standard carousel component that shows dinamicly few game ads as suggestions to the user.</li>
      <li> GameCreateComponent- it use a reactive form to creat a game ad.</li>
      <li> GameEditComponent - it use a reactive form to edit a game ad.</li>
      <li> GameDetailsComponent - it shows the current info of the game ad plus it has a special section that contains the previously watched game ads.</li>
      <li> GameAllComponent - it shows all current game ads. The games can be filtered, sorted and searched by input field.</li>
      <li> GameByUserComponent - it shows all current games that the specific user has created using child data transfer (from shared template).</li>
      <li> GameUserComponent - it contains the created games of the current logged user using child data transfer (from shared template).</li>
      <li> HomeComponent - it contains the carousel component and some animated greetings message.</li>
      <li> StartComponent - a simple static web page that has some short description for the website and holds redirect buttons to login and register.</li>
      <li> Page not found - an error page, in case the user tries to reach an unexisting page.</li>
  </ol>
  </li>
<li>Shared module - contains Header area, simple text Footer, and the template for the created game ads of a specific user using Angular animations.</li>
    </ol>
 </li>
<li>Core folder:
  <ol type="1">
<li>Services subfolder - auth service, game service and user service that handling calls to database using Observables.</li>
<li>Guards subfolder - authGuard and admin-authGuard that limit the access of the non-authorized users to specific pages.</li>
<li>Interceptors subfolder - jwt-interceptor that add headers to the specific http request and response-handler interceptor which handle the specific response and shows the notification animated message using ngx-toastr.</li>
<li>Models subfolder - contains the model interfaces for a Game ad and a User</li>
  </ol>
  </li>
  </ul>


For this project are used Angular and Type Script for front-end (with Bootstrap, custom HMTL and CSS). For the back end are used Node.js Server and MongoDB. The routing in this app is made with Аngular routing (Lazy loading).

To start the application open the angular project and the server and run npm install.
To enter as an admin please use:
email: admin@admin.com
password: Admin
