// To save as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/myCart")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class AddToCartServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
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
                            +"<span class=\"badge\"></span>"
                    +"</a></li></ul></div>"
                //<!-- End Atribute Navigation -->
            +"</div></nav>"
        //<!-- End Navigation -->
            +"</header>");
    //<!-- End Main Top -->

//<!-- Start All Title Box -->
    out.println("<div class=\"all-title-box\">"
        +"<div class=\"container\">"
            +"<div class=\"row\">"
                +"<div class=\"col-lg-12\">"
                    +"<h2>Cart</h2>"
                    +"<ul class=\"breadcrumb\">"
                        +"<li class=\"breadcrumb-item\"><a href=\"index.html\">Shop</a></li>"
                        +"<li class=\"breadcrumb-item active\">Cart</li>"
                    +"</ul>"
                +"</div>"
            +"</div>"
        +"</div>"
    +"</div>");
    //<!-- End All Title Box -->


      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/limitedshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         // Step 3: Execute a SQL SELECT query
        String Quan[] = request.getParameterValues("qty");
        String Options[] = request.getParameterValues("dropdown");
        String ModelID[] = request.getParameterValues("Model");
        String AmountDetails[] = request.getParameterValues("AmountDetails");
        String ShoeSIDDetails[] = request.getParameterValues("shoeSID");
        //out.println(Quan[0]);
        //out.println(Options[0]);
        //out.println(ModelID[0]);
        //out.println(AmountDetails[0]);
        //out.println(ShoeSIDDetails[0]);

        int shoeQuantityAmount = Integer.parseInt(AmountDetails[0])*Integer.parseInt(Quan[0]);

        String updateTotal = "Update Total set Amount = Amount + "+shoeQuantityAmount+" ";
        //out.println("<p>Your SQL statement is: " + updateTotal + "</p>"); // Echo for debugging
        int countUpdated = stmt.executeUpdate(updateTotal);
        //out.println(countUpdated + " records affected.");

        String insertCart = "Insert into myCart values ('"+ShoeSIDDetails[0]+"' ,"+Quan[0]+", '"+Options[0]+"')";
         //out.println("<p>Your SQL statement is: " + insertCart + "</p>"); // Echo for debugging
         int countInserted = stmt.executeUpdate(insertCart);
         System.out.println(countInserted);
         //out.println(countInserted + " records inserted.");


        String sqlGetShoeID = "Select * from shoe, myCart, Total where shoe.SID = myCart.SID";
         /*for (int i = 0; i < 1; ++i){
               sqlGetShoeID += "'"+ModelID[i]+"'";
         }
         sqlGetShoeID += " ";*/
         //out.println("<p>Your SQL statement is: " + sqlGetShoeID + "</p>"); // Echo for debugging
          ResultSet returnShoeID = stmt.executeQuery(sqlGetShoeID);  // Send the query to the server

        String sqlGetShoeDetails = "null";
        ResultSet returnShoeDetails = null;
        int total = 0;
        String totalAmount = " ";

        //<!-- Start Cart  -->
        out.println("<div class=\"cart-box-main\">"
            +"<div class=\"container\">"
                +"<div class=\"row\">"
                    +"<div class=\"col-lg-12\">"
                        +"<div class=\"table-main table-responsive\">"
                            +"<table class=\"table\">"
                                +"<thead>"
                                    +"<tr>"
                                        +"<th>Images</th>"
                                        +"<th>Product Name</th>"
                                        +"<th>Size</th>"
                                        +"<th>Price</th>"
                                        +"<th>Quantity</th>"
                                        +"<th>Total</th>"
                                        //+"<th>Remove</th>"
                                    +"</tr>"
                                +"</thead>");
        out.println("<tbody>");

        while(returnShoeID.next()){
                //Below block not in use as is the incorrect way of doing.
                //out.println(returnShoeID.getString("shoe.SID"));

                  /*String insertCart = "Insert into myCart values ('"+returnShoeID.getString("shoe.SID")+"' ,"+Quan[0]+", '"+Options[0]+"')";
                   out.println("<p>Your SQL statement is: " + insertCart + "</p>"); // Echo for debugging
                   int countInserted = stmt.executeUpdate(insertCart);
                   out.println(countInserted + " records inserted.");

                    sqlGetShoeDetails = "Select * from myCart, shoe, Total where myCart.SID = shoe.SID";
                    sqlGetShoeDetails += " ";
                    out.println("<p>Your SQL statement is: " + sqlGetShoeDetails + "</p>"); // Echo for debugging
                     returnShoeDetails = stmt.executeQuery(sqlGetShoeDetails);  // Send the query to the server  */


                      //while(returnShoeDetails.next()){
            out.println("<tr>"
                +"<td class=\"thumbnail-img\">"
                  +"<a href=\"#\">"
                  +"<img class=\"img-fluid\" src=\"images/my_images/Shoe/Shoe"+returnShoeID.getString("PhotoID")+".jpg\" alt=\"\" />"
                  +"</a>"
                  +"</td>"
                  +"<td class=\"name-pr\">"
                    +"<a href=\"#\">"+returnShoeID.getString("Model")+"</a>"
                  +"</td>"
                  +"<td class=\"price-pr\">" //This CSS reuse for SIZE
                  +"<p>"+returnShoeID.getString("Size")+"</p>"
                  +"</td>"
                  +"<td class=\"price-pr\">"
                    +"<p>"+returnShoeID.getString("Price")+"</p>"
                  +"</td>"
                  +"<td class=\"quantity-box\">"
                  +"<p>"+returnShoeID.getString("Quantity")+"</p>"
                  +"</td>");
                  total = Integer.parseInt(returnShoeID.getString("Price"))*Integer.parseInt(returnShoeID.getString("myCart.Quantity"));
                  out.println("<td class=\"total-pr\">"
                    +"<p>"+total+"</p>"
                  +"</td>"
                  /*+"<td><form method=\"get\" action=\"removeItemInCart\">"
                  +"<td class=\"remove-pr\">"
                  +"<a href=\"#\"><i class=\"fas fa-times\"></i></a>"
                    //+"</td>"
                  /*+"<input type =\"hidden\" name=\"removeItem\" value=\""+returnShoeID.getString("SID")+"\">"
                  +"<input type=\"Submit\" value=\"delete\">"
                  +"</form>"
                  +"</td>"*/
                  +"</tr>");
                                  
                  totalAmount=returnShoeID.getString("Amount");

                //}
  }
                    out.println("<tbody>");
                  out.println("</table>"
                    +"</div>"
                +"</div>"
            +"</div>");

            out.println("<div class=\"row my-5\">"
                +"<div class=\"col-lg-6 col-sm-6\">"
                    +"<div class=\"coupon-box\">"
                        +"<div class=\"input-group input-group-sm\">"
                        +"</div>"
                    +"</div>"
                +"</div>"
                +"<div class=\"col-lg-6 col-sm-6\">"
                    +"<div class=\"update-box\">"
                        +"<form method=\"get\" action=\"index.html\">"
                          +"<input value=\"Back to Shop\" type=\"submit\">"
                          +"<input value=\"Empty Cart\" type=\"submit\" formaction=\"cartEmpty\">"
                        +"</form>"
                    +"</div>"
                +"</div>"
            +"</div>"

            +"<div class=\"row my-5\">"
                +"<div class=\"col-lg-8 col-sm-12\"></div>"
                +"<div class=\"col-lg-4 col-sm-12\">"
                    +"<div class=\"order-box\">"
                        +"<h3>Order summary</h3>"
                        +"<div class=\"d-flex\">"
                            +"<h4>Sub Total</h4>"
                            +"<div class=\"ml-auto font-weight-bold\"> $"+totalAmount+" </div>"
                        +"</div>"
                        +"<div class=\"d-flex\">"
                            +"<h4>Shipping Cost</h4>"
                            +"<div class=\"ml-auto font-weight-bold\"> At Check Out </div>"
                        +"</div>"
                        +"<hr>"
                        +"<div class=\"d-flex gr-total\">"
                            +"<h5>Grand Total</h5>"
                            +"<div class=\"ml-auto h5\"> $ "+totalAmount+" </div>"
                        +"</div>"
                        +"<hr> </div>"
                +"</div>"
                +"<form method=\"get\" action=\"payment\"class=\"col-12 d-flex shopping-box\">"
                //+"<div class=\"col-12 d-flex shopping-box\">"
                //+"<input type = \"hidden\" name=\"qty\" value=\""+Quan[0]+"\" >"
                +"<input class=\"ml-auto btn hvr-hover\" class=\"coupon-box\" type=\"Submit\" value=\"Checkout\"/></form> "
                //+"<a href=\"checkout.html\" class=\"ml-auto btn hvr-hover\">Checkout</a> </div>"
            +"</div>"
        +"</div>"
    +"</div>");
//<!-- End Cart -->


 
      } catch(Exception ex) {
         out.println("<p>Error: " + ex.getMessage() + "</p>");
         //out.println("<p>Check Tomcat console for details.</p>");
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
                                    +"<p><i class=\"fas fa-map-marker-alt\"></i>This Project is done by: <br>CHUA JI XIANG<br>EVY WIDYA<br> Undergraduate, Information Engineering and Media <br>Nanyang Technological University </p>"
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