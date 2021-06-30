// To save as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/login")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class LoginServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
                  String[] cust_name = request.getParameterValues("cust_name");
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
      // Print an HTML page as the output of the query
out.println("<html lang=\"en\"><head>");
      out.println("<meta charset=\"utf-8\">"
      +"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"
   //<!-- Mobile Metas -->
      +"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
      out.println("<title>The Limited Shop</title>");
      out.println("<!-- Site Icons -->");
      out.println("<link rel=\"shortcut icon\" href=\"images/my_images/limitedLogo.jpg\" type=\"image/x-icon\">");
      out.println("<link rel=\"apple-touch-icon\" href=\"images/apple-touch-icon.png\">");

      out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
      out.println("<link rel=\"stylesheet\" href=\"css/style.css\">");
      out.println("<link rel=\"stylesheet\" href=\"css/responsive.css\">");
      out.println("<link rel=\"stylesheet\" href=\"css/custom.css\">");

      out.println("</head><body>");
      //<!-- Start Main Top -->
      out.println("<div class=\"main-top\">"
        +"<div class=\"container-fluid\">"
            +"<div class=\"row\">"
                +"<div class=\"col-lg-6 col-md-6 col-sm-12 col-xs-12\">"
                    +"<div class=\"text-slid-box\">"
                        +"<div id=\"offer-box\" class=\"carouselTicker\">"
                            +"<ul class=\"offer-box\">"
                                +"<li><i class=\"fab fa-opencart\"></i> 10 % Discount above 2 purchases</li>"
                                +"<li><i class=\"fab fa-opencart\"></i> 20% Discount above 3 purchases</li>"
                                +"<li><i class=\"fab fa-opencart\"></i> Chinese New Year Sale!</li>"
                            +"</ul></div></div></div>"
                +"<div class=\"col-lg-6 col-md-6 col-sm-12 col-xs-12\">"
                    +"<div class=\"custom-select-box\">"
                        +"<select id=\"basic\" class=\"selectpicker show-tick form-control\" data-placeholder=\"$ USD\">"
                        +"<option>$ SGD</option>"
                    +"</select></div>"
                    +"<div class=\"right-phone-box\"><p>Call US : <a href=\"#\"> +65 6543 2341</a></p></div>"
                    +"<div class=\"our-link\">"
                        +"<ul><li><a href=\"#\">My Account</a></li>"
                        +"<li><a href=\"#\">Contact Us</a></li></ul>"
                    +"</div></div></div></div></div>");
    //<!-- End Main Top -->

         //<!-- Start Main Top -->
          out.println("<header class=\"main-header\">"
              //<!-- Start Navigation -->
              +"<nav class=\"navbar navbar-expand-lg navbar-light bg-light navbar-default bootsnav\">"
                  +"<div class=\"container\">"
                      //<!-- Start Header Navigation -->
                      +"<div class=\"navbar-header\">"
                          +"<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbar-menu\" aria-controls=\"navbars-rs-food\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">"
                          +"<i class=\"fa fa-bars\"></i></button>"
                          +"<a class=\"navbar-brand\" href=\"index.html\"><img src=\"images/my_images/limitedLogo.jpg\" class=\"logo\" alt=\"\" style=\"width:180px;height:120px;\"></a>"
                      +"</div>"
                      //<!-- End Header Navigation -->

                      //<!-- Collect the nav links, forms, and other content for toggling -->
                      +"<div class=\"collapse navbar-collapse\" id=\"navbar-menu\">"
                          +"<ul class=\"nav navbar-nav ml-auto\" data-in=\"fadeInDown\" data-out=\"fadeOutUp\">"
                              +"<li class=\"nav-item active\"><a class=\"nav-link\" href=\"index.html\">Home</a></li>"
                              +"<li class=\"nav-item\"><a class=\"nav-link\" href=\"about.html\">About Us</a></li>"
                              +"<li class=\"dropdown\">"
                                  +"<a href=\"#\" class=\"nav-link\" data-toggle=\"dropdown\">SHOP</a>"
                                  +"<ul class=\"dropdown-menu\">"
                                      +"<li><a href=\"cart.html\">Cart</a></li>"
                                      +"<li><a href=\"checkout.html\">Checkout</a></li>"
                                      +"<li><a href=\"my-account.html\">My Account</a></li>"
                                      +"<li><a href=\"wishlist.html\">Wishlist</a></li>"
                                      +"<li><a href=\"shop-detail.html\">Shop Detail</a></li>"
                                  +"</ul></li>"
                              +"<li class=\"nav-item\"><a class=\"nav-link\" href=\"service.html\">Our Service</a></li>"
                              +"<li class=\"nav-item\"><a class=\"nav-link\" href=\"contact-us.html\">Contact Us</a></li>"
                          +"</ul></div>"
                      //<!-- /.navbar-collapse -->

                      //<!-- Start Atribute Navigation -->
                      +"<div class=\"attr-nav\">"
                          +"<ul>"
                              +"<li class=\"side-menu\"><a href=\"#\">"
                              +"<i class=\"fa fa-shopping-bag\"></i>"
                                  +"<span class=\"badge\">3</span>"
                          +"</a></li></ul></div>"
                      //<!-- End Atribute Navigation -->
                  +"</div></nav>"
              //<!-- End Navigation -->
                  +"</header>");
          //<!-- End Main Top -->

      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/limitedshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         //Step 3:
         String username[] = request.getParameterValues("username");
         String password[] = request.getParameterValues("password");
         //out.println(username[0]); //for debugging
         //out.println(password[0]); // for debugging

         String cartItems[] = request.getParameterValues("cartItems");
        String cartShoeSize[] = request.getParameterValues("cartShoeSize");
        String cartShoeQuantity[] = request.getParameterValues("cartShoeQuantity");
        //out.println(cartItems[0]); // for debugging
        //out.println(cartShoeSize[0]); //for debugging

         String sqlGetUserDetails = "Select * from login, billing_address, orderid, payment where login.username = billing_address.username and login.username = '"+username[0]+"' and login.password = '"+password[0]+"'";
         //out.println("<p>Your SQL statement is: " + sqlGetUserDetails + "</p>"); // Echo for debugging
         ResultSet returnUserDetails = stmt.executeQuery(sqlGetUserDetails);  // Send the query to the server                           

         //Step 4: print resultset
         if (returnUserDetails.next()){
            //response.sendRedirect("orderViaLogin");
          //out.println(returnUserDetails.getString("orderid.orderID"));

            if (returnUserDetails.getString("lastName").equals("admin")){
               out.println("<li class=\"text-centre\">"
                  +"<div class=\"container\">"
                         +"<div class=\"row\">"
                         +"<div class=\"col-md-12\">"
                         +"<center><h1 class=\"m-b-20\"><strong>Technical Admin login</strong></h1></center>"
                        +"<form method=\"get\" action=\"payment\">"
                           +"<input class=\"btn hvr-hover\" data-fancybox-close=\"\" style=\"margin:15px;\" type=\"Submit\" value=\"Return Previous Page\"/>"
                        +"</form>"
                     +"</div></div></div></li>");
            }
            else{
               out.println("<table "
             +"height=\"30px\">"
             +"</table>"
             +"</div>"
             +"</div>"

             +"<li class=\"text-centre\">"
             +"<div class=\"container\">"
                         +"<div class=\"row\">"
                         +"<div class=\"col-md-12\">"
                               +"<center><h1 class=\"m-b-20\"><strong><br>Welcome back,  "+returnUserDetails.getString("firstName")+" "+returnUserDetails.getString("lastName")+"</strong><br><br></h1><center>"
                                 +"<center><h1 class=\"m-b-20\"><strong><br>Your particulars have been retrieved. Click the 'Place Order' button below to confirm your order.</strong><br><br></h1><center>"
                           //+"<div class=\"col-12 d-flex shopping-box\">"
                           +"<form method=\"get\" action=\"orderConfirmation\" id=\"ToOrder\">"
                           +"<input type=\"hidden\" name='cartItems' value= '"+cartItems[0]+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='cartShoeSize' value= '"+cartShoeSize[0]+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='cartShoeQuantity' value= '"+cartShoeQuantity[0]+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='firstName' value= '"+returnUserDetails.getString("firstName")+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='lastName' value= '"+returnUserDetails.getString("lastName")+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='email' value= '"+returnUserDetails.getString("email")+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='address' value= '"+returnUserDetails.getString("address")+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='country' value= '"+returnUserDetails.getString("country")+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='postal' value= '"+returnUserDetails.getString("postal")+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='card' value= '"+returnUserDetails.getString("payment.card")+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='name' value= '"+returnUserDetails.getString("payment.name")+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='creditCardNo' value= '"+returnUserDetails.getString("payment.creditCardNo")+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='expiration' value= '"+returnUserDetails.getString("payment.expiration")+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='CVV' value= '"+returnUserDetails.getString("payment.CVV")+"' form=\"ToOrder\"/>"
                                       +"<input type=\"hidden\" name='orderid' value= '"+returnUserDetails.getString("orderid.orderID")+"' form=\"ToOrder\"/>"
                           +"<input class=\"btn hvr-hover\" data-fancybox-close=\"\" style=\"margin:15px;\" type=\"Submit\" value=\"Back to Shop\" formaction=\"index.html\"/>"
                           +"<input class=\"btn hvr-hover\" data-fancybox-close=\"\" style=\"margin:15px;\" type=\"Submit\" value=\"Back to Payment\" formaction=\"payment\"/>"
                           +"<input class=\"btn hvr-hover\" data-fancybox-close=\"\" style=\"margin:15px;\" type=\"Submit\" value=\"Place Order\"/>"
                           +"</form>"
                           //+"<div class=\"col-12 d-flex shopping-box\"> <a href=\"Order_Confirmation.html\" class=\"ml-auto btn hvr-hover\">Place Order</a> </div>"
                       +"</div>"
                        +"</div>"
                         +"</div>"
                     +"</div>"
                 +"</li>"
             +"</ul>"
       +"</div>"
     +"</div>"

         +"<table "
             +"height=\"30px\">"
         +"</table>");
            }

         }
         else{
            response.sendRedirect("inValidLogin.html");
            //out.println("wrong user name and password...");
            //directresponse to a html page and put a retry button.
         }


      } catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         out.println("<p>Check Tomcat console for details.</p>");
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
 
      //<!-- Start Instagram Feed  -->
    out.println("<div class=\"instagram-box\">"
        +"<div class=\"main-instagram owl-carousel owl-theme\"><div class=\"item\"><div class=\"ins-inner-box\">"
                    +"<img src=\"images/my_images/Insta1.jpg\" alt=\"\" style=\"width:240px;height:200px;\" />"
                    +"<div class=\"hov-in\"><a href=\"#\"><i class=\"fab fa-instagram\"></i></a></div>"
                +"</div>"
            +"</div>"
            +"<div class=\"item\">"
                +"<div class=\"ins-inner-box\">"
                    +"<img src=\"images/my_images/Insta2.jpg\" alt=\"\" style=\"width:240px;height:200px;\" />"
                    +"<div class=\"hov-in\">"
                        +"<a href=\"#\"><i class=\"fab fa-instagram\"></i></a>"
                    +"</div>"
                +"</div>"
            +"</div>"
            +"<div class=\"item\">"
                +"<div class=\"ins-inner-box\">"
                    +"<img src=\"images/my_images/Insta3.jpg\" alt=\"\" style=\"width:240px;height:200px;\" />"
                    +"<div class=\"hov-in\">"
                        +"<a href=\"#\"><i class=\"fab fa-instagram\"></i></a>"
                    +"</div>"
                +"</div>"
            +"</div>"
            +"<div class=\"item\">"
                +"<div class=\"ins-inner-box\">"
                    +"<img src=\"images/my_images/Insta4.jpg\" alt=\"\" style=\"width:240px;height:200px;\" />"
                    +"<div class=\"hov-in\">"
                        +"<a href=\"#\"><i class=\"fab fa-instagram\"></i></a>"
                    +"</div>"
                +"</div>"
            +"</div>"
            +"<div class=\"item\">"
                +"<div class=\"ins-inner-box\">"
                    +"<img src=\"images/my_images/Insta5.jpg\" alt=\"\" style=\"width:240px;height:200px;\" />"
                    +"<div class=\"hov-in\">"
                        +"<a href=\"#\"><i class=\"fab fa-instagram\"></i></a>"
                    +"</div>"
                +"</div>"
            +"</div>"
            +"<div class=\"item\">"
                +"<div class=\"ins-inner-box\">"
                    +"<img src=\"images/my_images/Insta6.jpg\" alt=\"\" style=\"width:240px;height:200px;\" />"
                    +"<div class=\"hov-in\">"
                        +"<a href=\"#\"><i class=\"fab fa-instagram\"></i></a>"
                    +"</div>"
                +"</div>"
            +"</div>"
            +"<div class=\"item\">"
                +"<div class=\"ins-inner-box\">"
                    +"<img src=\"images/my_images/Insta7.jpg\" alt=\"\" style=\"width:240px;height:200px;\" />"
                    +"<div class=\"hov-in\">"
                        +"<a href=\"#\"><i class=\"fab fa-instagram\"></i></a>"
                    +"</div>"
                +"</div>"
            +"</div>"
            +"<div class=\"item\">"
                +"<div class=\"ins-inner-box\">"
                    +"<img src=\"images/my_images/Insta8.jpg\" alt=\"\" style=\"width:240px;height:200px;\" />"
                    +"<div class=\"hov-in\">"
                        +"<a href=\"#\"><i class=\"fab fa-instagram\"></i></a>"
                    +"</div>"
                +"</div>"
            +"</div>"
            +"<div class=\"item\">"
                +"<div class=\"ins-inner-box\">"
                    +"<img src=\"images/my_images/Insta9.jpg\" alt=\"\" style=\"width:240px;height:200px;\" />"
                    +"<div class=\"hov-in\">"
                        +"<a href=\"#\"><i class=\"fab fa-instagram\"></i></a>"
                    +"</div>"
                +"</div>"
            +"</div>"
            +"<div class=\"item\">"
                +"<div class=\"ins-inner-box\">"
                    +"<img src=\"images/my_images/Insta10.jpg\" alt=\"\" style=\"width:240px;height:200px;\" />"
                    +"<div class=\"hov-in\">"
                        +"<a href=\"#\"><i class=\"fab fa-instagram\"></i></a>"
                    +"</div>"
                +"</div>"
            +"</div>"
        +"</div>"
    +"</div>");
    //<!-- End Instagram Feed  -->

    //<!-- Start Footer  -->
    out.println("<footer>"+
        "<div class=\"footer-main\">"
            +"<div class=\"container\">"
                +"<div class=\"row\"><div class=\"col-lg-4 col-md-12 col-sm-12\"><div class=\"footer-widget\">"
                            +"<h4>About The Limited Shop</h4>"
                            +"<p>This online shop is for project purposes only. <br>Products listed are not for sale but for demostration purposes.</p>"
                            +"<ul>"
                                +"<li><a href=\"#\"><i class=\"fab fa-facebook\" aria-hidden=\"true\"></i></a></li>"
                                +"<li><a href=\"#\"><i class=\"fab fa-twitter\" aria-hidden=\"true\"></i></a></li>"
                                +"<li><a href=\"#\"><i class=\"fab fa-linkedin\" aria-hidden=\"true\"></i></a></li>"
                                +"<li><a href=\"#\"><i class=\"fab fa-google-plus\" aria-hidden=\"true\"></i></a></li>"
                                +"<li><a href=\"#\"><i class=\"fa fa-rss\" aria-hidden=\"true\"></i></a></li>"
                                +"<li><a href=\"#\"><i class=\"fab fa-pinterest-p\" aria-hidden=\"true\"></i></a></li>"
                                +"<li><a href=\"#\"><i class=\"fab fa-whatsapp\" aria-hidden=\"true\"></i></a></li>"
                            +"</ul>"
                        +"</div>"
                    +"</div>"
                    +"<div class=\"col-lg-4 col-md-12 col-sm-12\">"
                        +"<div class=\"footer-link\">"
                            +"<h4>Information</h4>"
                            +"<ul>"
                                +"<li><a href=\"http://localhost:9999/limitedshop/homeTest.html\">About Us</a></li>"
                                +"<li><a href=\"#\">Our Service</a></li>"
                            +"</ul>"
                        +"</div>"
                    +"</div>"
                    +"<div class=\"col-lg-4 col-md-12 col-sm-12\">"
                        +"<div class=\"footer-link-contact\">"
                            +"<h4>Contact Us</h4>"
                            +"<ul>"
                                +"<li>"
                                    +"<p><i class=\"fas fa-map-marker-alt\"></i>This Project is done by: <br>CHUA JI XIANG<br>EVY WIYDA<br> Undergraduate, Information Engineering and Media <br>Nanyang Technological University </p>"
                                +"</li>"
                                +"<li>"
                                    +"<p><i class=\"fas fa-phone-square\"></i>Phone: <a href=\"tel:+1-888705770\">+65 6541 2341</a></p>"
                                +"</li>"
                                +"<li>"
                                    +"<p><i class=\"fas fa-envelope\"></i>Email: <a href=\"mailto:contactinfo@gmail.com\">jchua093@e.ntu.edu.sg</a></p>"
                                    +"<p><i class=\"fas fa-envelope\"></i>Email: <a href=\"mailto:contactinfo@gmail.com\">evy001@e.ntu.edu.sg</a></p>"
                                +"</li></ul></div></div></div></div></div></footer>");
    //<!-- End Footer  -->
    out.println("<a href=\"#\" id=\"back-to-top\" title=\"Back to top\" style=\"display: none;\">&uarr;</a>");

//ALL JS FILES 
      out.print("<script src=\"js/jquery-3.2.1.min.js\"></script>");
      out.print("<script src=\"js/popper.min.js\"></script>");
      out.print("<script src=\"js/bootstrap.min.js\"></script>");
      // ALL PLUGINS
      out.print("<script src=\"js/jquery.superslides.min.js\"></script>");
      out.print("<script src=\"js/bootstrap-select.js\"></script>");
      out.print("<script src=\"js/inewsticker.js\"></script>");
      out.print("<script src=\"js/bootsnav.js.\"></script>");
      out.print("<script src=\"js/images-loded.min.js\"></script>");
      out.print("<script src=\"js/isotope.min.js\"></script>");
      out.print("<script src=\"js/owl.carousel.min.js\"></script>");
      out.print("<script src=\"js/baguetteBox.min.js\"></script>");
      out.print("<script src=\"js/form-validator.min.js\"></script>");
      out.print("<script src=\"js/contact-form-script.js\"></script>");
      out.print("<script src=\"js/custom.js\"></script>");
 
      out.println("</body></html>");
      out.close();
   }
}