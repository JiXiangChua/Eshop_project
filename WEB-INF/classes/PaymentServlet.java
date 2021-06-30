// To save as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/payment")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class PaymentServlet extends HttpServlet {

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
      out.println("<title>The Limited Shop - Checkout</title>");
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
    
 //<!-- Start All Title Box -->
    out.println("<div class=\"all-title-box\">"
        +"<div class=\"container\">"
            +"<div class=\"row\">"
                +"<div class=\"col-lg-12\">"
                    +"<h2>Checkout</h2>"
                    +"<ul class=\"breadcrumb\">"
                        +"<li class=\"breadcrumb-item\"><a href=\"index.html\">Shop</a></li>"
                        +"<li class=\"breadcrumb-item active\">Checkout</li>"
                    +"</ul>"
                +"</div>"
            +"</div>"
        +"</div>"
    +"</div>");
//    <!-- End All Title Box -->
//<!-- End Main Top -->
//Declaring Variables
    int shippingPrice = 10;
    int totalAmountForShoe=0;
    int total = 0;


      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/limitedshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
// Step 3 & 4: Execute a SQL SELECT query and Process the query result
         // Retrieve the books' id. Can order more than one books.
    //<!-- Start Cart  -->
    out.println("<div class=\"cart-box-main\">"
        +"<div class=\"container\">"
        +"<div class=\"row new-account-login\">"
                +"<div class=\"col-sm-6 col-lg-6 mb-3\">"
                    +"<div class=\"title-left\">"
                        +"<h3>Account Login</h3>"
                    +"</div>"
                    +"<h5><a data-toggle=\"collapse\" href=\"#formLogin\" role=\"button\" aria-expanded=\"false\">Click here to Login</a></h5>"
                    +"<form class=\"mt-3 collapse review-form-box\" method=\"get\" action=\"login\" id=\"formLogin\">"
                        +"<div class=\"form-row\">"
                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"InputUsername\" class=\"mb-0\">Username</label>"
                                +"<input type=\"text\" class=\"form-control\" name=\"username\" id=\"InputUsername\" placeholder=\"Enter Username\" required> </div>"
                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"InputPassword\" class=\"mb-0\">Password</label>"
                                +"<input type=\"password\" class=\"form-control\" name=\"password\"id=\"InputPassword\" placeholder=\"Password\" required> </div>"
                        +"</div>"
                        +"<button type=\"submit\" class=\"btn hvr-hover\">Login</button>"
                    +"</form>"
                +"</div>"
                +"<div class=\"col-sm-6 col-lg-6 mb-3\">"
                    +"<div class=\"title-left\">"
                        +"<h3>Create New Account</h3>"
                    +"</div>"
                    +"<h5><a data-toggle=\"collapse\" href=\"#formRegister\" role=\"button\" aria-expanded=\"false\">Click here to Register</a></h5>"
                    +"<form class=\"mt-3 collapse review-form-box\" method=\"get\" action=\"registration\"id=\"formRegister\">"
                        +"<div class=\"form-row\">"
                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"InputName\" class=\"mb-0\">First Name</label>"
                                +"<input type=\"text\" class=\"form-control\" name=\"firstName\"id=\"InputName\" placeholder=\"First Name\" required> </div>"
                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"InputLastname\" class=\"mb-0\">Last Name</label>"
                                +"<input type=\"text\" class=\"form-control\" name=\"lastName\"id=\"InputLastname\" placeholder=\"Last Name\" required> </div>"
                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"InputEmail1\" class=\"mb-0\">Email</label>"
                                +"<input type=\"email\" class=\"form-control\" name=\"email\"id=\"InputEmail1\" placeholder=\"Enter Email\" required> </div>"
                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"InputPassword1\" class=\"mb-0\">Shipping Address</label>"
                                +"<input type=\"text\" class=\"form-control\" name=\"address\"id=\"InputShippingAddress\" placeholder=\"Address\" required> </div>"
                        +"</div>"
                        +"<div class=\"form-row\">"
                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"country\">Select your Country </label>"
                                +"<select class=\"wide w-100\" name=\"country\" form=\"formRegister\" required>"
                                    +"<option value=\"Choose...\" data-display=\"Select\">Choose...</option>"
                                    +"<option value=\"Singapore\" form=\"ToOrder\">Singapore</option>"
                                    +"<option value=\"China\" form=\"ToOrder\">China</option>"
                                    +"<option value=\"United States\" form=\"ToOrder\">United States</option>"
                                    +"</select>"
                                +"</div>"
                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"InputLastname\" class=\"mb-0\">Postal Code</label>"
                                +"<input type=\"text\" class=\"form-control\" maxlength=\"6\" name=\"postal\" id=\"InputPostal\" placeholder=\"6 digits\" required> </div>"

                                +"<input type=\"hidden\" name=\"card\" type=\"radio\" value=\"Credit Card\" class=\"custom-control-input\" form=\"formRegister\">"

                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"InputEmail1\" class=\"mb-0\">Name on Card</label>"
                                +"<input type=\"text\" class=\"form-control\" name=\"name\" id=\"InputNameOnCard\" placeholder=\"Full Name\" required> </div>"
                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"InputPassword1\" class=\"mb-0\">Credit card number</label>"
                                +"<input type=\"text\" class=\"form-control\" name=\"creditCardNo\" maxlength=\"16\" id=\"InputCredit\" placeholder=\"xxxx xxxx xxxx xxxx\" required> </div>"
                        +"</div>"
                        +"<div class=\"form-row\">"
                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"InputName\" class=\"mb-0\">Expiration</label>"
                                +"<input type=\"month\" class=\"form-control\" name=\"expiration\" id=\"InputExpire\" placeholder=\"MM/YY\" required> </div>"
                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"InputLastname\" class=\"mb-0\">CVV</label>"
                                +"<input type=\"text\" class=\"form-control\" name=\"CVV\" maxlength=\"3\" id=\"InputCVV\" placeholder=\"3 digits\" required> </div>"
                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"InputLastname\" class=\"mb-0\">Enter your Username</label>"
                                +"<input type=\"text\" class=\"form-control\" name=\"username\" id=\"InputPassword\" placeholder=\"Username\" required> </div>"
                            +"<div class=\"form-group col-md-6\">"
                                +"<label for=\"InputLastname\" class=\"mb-0\">Password</label>"
                                +"<input type=\"password\" class=\"form-control\" name=\"password\" id=\"InputPassword\" placeholder=\"Password\" required> </div>"
                        +"</div>"
                        +"<button type=\"submit\" class=\"btn hvr-hover\">Register</button>"
                    +"</form>"
                +"</div>"
            +"</div>"
             +"<div class=\"row\">"
                +"<div class=\"col-sm-6 col-lg-6 mb-3\">" //LEFT COLUMN
                    +"<div class=\"checkout-address\">"
                        +"<div class=\"title-left\">"
                            +"<h3>Billing address</h3>"
                        +"</div>"
                        +"<form class=\"needs-validation\" novalidate>"
                            +"<div class=\"row\">"
                                +"<div class=\"col-md-6 mb-3\">"
                                    +"<label for=\"firstName\">First name *</label>"
                                    +"<input type=\"text\" class=\"form-control\" name=\"firstName\" placeholder=\"\" value=\"\" form=\"ToOrder\" required>"
                                    +"<div class=\"invalid-feedback\"> Valid first name is required. </div>"
                                +"</div>"
                                +"<div class=\"col-md-6 mb-3\">"
                                    +"<label for=\"lastName\">Last name *</label>"
                                    +"<input type=\"text\" class=\"form-control\" name=\"lastName\" placeholder=\"\" value=\"\" form=\"ToOrder\" required>"
                                    +"<div class=\"invalid-feedback\"> Valid last name is required. </div>"
                                +"</div>"
                            +"</div>" + " <div class=\"mb-3\">" +"<div class=\"input-group\">"
                                    
                                    +"<div class=\"invalid-feedback\"> </div>"
                                +"</div>"
                                +"</div>"
                                +"<div class=\"mb-3\">"
                                +"<label for=\"email\">Email Address *</label>"
                                +"<input type=\"email\" class=\"form-control\" name=\"email\" placeholder=\"\" value=\"\" form=\"ToOrder\" required>"
                                +"<div class=\"invalid-feedback\"> Please enter a valid email address for shipping updates. </div>"
                            +"</div>"
                            +"<div class=\"mb-3\">"
                                +"<label for=\"address\">Address *</label>"
                                +"<input type=\"text\" class=\"form-control\" name=\"address\" placeholder=\"\" name=\'address\' form=\"ToOrder\" required>"
                                +"<div class=\"invalid-feedback\"> Please enter your shipping address. </div>"
                            +"</div>"
                            +"<div class=\"row\">"
                                +"<div class=\"col-md-5 mb-3\">"
                                    +"<label for=\"country\">Country *</label>"
                                    +"<select class=\"wide w-100\" name=\"country\" form=\"ToOrder\" required>"
                                    +"<option value=\"Choose...\" data-display=\"Select\" form=\"ToOrder\">Choose...</option>"
                                    +"<option value=\"Singapore\" form=\"ToOrder\">Singapore</option>"
                                    +"<option value=\"China\" form=\"ToOrder\">China</option>"
                                    +"<option value=\"United States\" form=\"ToOrder\">United States</option>"
                                    +"</select>"
                                    +"<div class=\"invalid-feedback\"> Please select a valid country. </div>"
                                +"</div>"
                                +"<div class=\"col-md-4 mb-3\">"
                                    +"<label for=\"Postal Code\">Postal Code *</label>"
                                    +"<input type=\"text\" class=\"form-control\" maxlength=\"6\" name=\"postal\" max=\"999999\" form=\"ToOrder\""
                                    +"placeholder=\"\" name= \'postal\' form=\"ToOrder\" required>"
                                    +"<div class=\"invalid-feedback\"> Postal code required. </div>"
                                +"</div>"
                                +"<div class=\"col-md-4 mb-3\">"
                                 +"</div>"
                            +"</div>"
                            
                            +"<hr class=\"mb-4\">"
                            +"<div class=\"title\"> <span>Payment</span> </div>"
                            +"<div class=\"d-block my-3\">"
                                +"<div class=\"custom-control custom-radio\">"
                                    +"<input id=\"credit\" name=\"card\" type=\"radio\" value=\"Credit Card\" class=\"custom-control-input\" form=\"ToOrder\" checked required>"
                                    +"<label class=\"custom-control-label\" for=\"credit\">Credit card</label>"
                                +"</div>"
                                +"<div class=\"custom-control custom-radio\">"
                                    +"<input id=\"debit\" name=\"card\" type=\"radio\" value=\"Debit Card\" class=\"custom-control-input\" form=\"ToOrder\" required>"
                                    +"<label class=\"custom-control-label\" for=\"debit\">Debit card</label>"
                                +"</div>"
                                +"<div class=\"custom-control custom-radio\">"
                                    +"<input id=\"paypal\" name=\"card\" type=\"radio\" value=\"Paypal\" class=\"custom-control-input\" form=\"ToOrder\" required>"
                                    +"<label class=\"custom-control-label\" for=\"paypal\">Paypal</label>"
                                +"</div>"
                            +"</div>"
                            +"<div class=\"row\">"
                                +"<div class=\"col-md-6 mb-3\">"
                                    +"<label for=\"cc-name\">Name on card</label>"
                                    +"<input type=\"text\" class=\"form-control\" name=\"name\" id=\"cc-name\" placeholder=\"\" form=\"ToOrder\" required> <small class=\"text-muted\">Full name as displayed on card</small>"
                                    +"<div class=\"invalid-feedback\"> Name on card is required </div>"
                                +"</div>"
                                +"<div class=\"col-md-6 mb-3\">"
                                    +"<label for=\"cc-number\">Credit card number</label>"
                                    +"<input type=\"text\" class=\"form-control\" name=\"creditCardNo\" maxlength=\"16\" id=\"cc-number\" placeholder=\"\" form=\"ToOrder\" required>"
                                    +"<div class=\"invalid-feedback\"> Credit card number is required </div>"
                                +"</div>"
                            +"</div>"
                            +"<div class=\"row\">"
                                +"<div class=\"col-md-6 mb-3\">"
                                    +"<label for=\"cc-expiration\">Expiration</label>"
                                    +"<input type=\"month\" class=\"form-control\" name=\"expiration\" id=\"cc-expiration\" placeholder=\"\" form=\"ToOrder\" required>"
                                    +"<div class=\"invalid-feedback\"> Expiration date required </div>"
                                +"</div>"
                                +"<div class=\"col-md-3 mb-3\">"
                                    +"<label for=\"cc-expiration\">CVV</label>"
                                    +"<input type=\"text\" class=\"form-control\" maxlength=\"3\" name=\"CVV\" id=\"cc-cvv\" placeholder=\"\" form=\"ToOrder\" required>"
                                    +"<div class=\"invalid-feedback\"> Security code required </div>"
                                +"</div>"
                                +"<div class=\"col-md-6 mb-3\">"
                                    +"<div class=\"payment-icon\">"
                                        +"<ul>"
                                            +"<li><img class=\"img-fluid\" src=\"images/payment-icon/1.png\" alt=\"\"></li>"
                                            +"<li><img class=\"img-fluid\" src=\"images/payment-icon/2.png\" alt=\"\"></li>"
                                            +"<li><img class=\"img-fluid\" src=\"images/payment-icon/3.png\" alt=\"\"></li>"
                                            +"<li><img class=\"img-fluid\" src=\"images/payment-icon/5.png\" alt=\"\"></li>"
                                            +"<li><img class=\"img-fluid\" src=\"images/payment-icon/7.png\" alt=\"\"></li>"
                                        +"</ul>"
                                    +"</div>"
                                +"</div>"
                            +"</div>"
                            +"<hr class=\"mb-1\"> </form>"
                        +"</div>"
                    +"</div>"

            +"<div class=\"col-sm-6 col-lg-6 mb-3\">" //RIGHT COLUMN
                    +"<div class=\"row\">"
                        +"<div class=\"col-md-12 col-lg-12\">"
                            +"<div class=\"shipping-method-box\">"
                                +"<div class=\"title-left\">" //SHIPPMENT METHOD
                                    +"<h3>Shipping Method</h3>"
                                    +"</div>"
                                    +"<div class=\"mb-4\">"
                                    +"<div class=\"custom-control custom-radio\">"
                                        +"<input id=\"shippingOption1\" name=\"shipping-option\" class=\"custom-control-input\" checked=\"checked\" type=\"radio\">"
                                        +"<label class=\"custom-control-label\" for=\"shippingOption1\">Standard Delivery</label> <span class=\"float-right font-weight-bold\">FREE</span> </div>"
                                    +"<div class=\"ml-4 mb-2 small\">(3-7 business days)</div>"
                                    +"<div class=\"custom-control custom-radio\">"
                                    +"</div>"
                                    +"<div class=\"ml-4 mb-2 small\"></div>"
                                    +"</div>"
                                +"</div>"
                        +"<div class=\"col-md-12 col-lg-12\">"
                            +"<div class=\"odr-box\">"
                                +"<div class=\"title-left\">"
                                    +"<h3>Shopping cart</h3>"
                                +"</div>");

        //Executing SQL SELECCT Query
        String sqlGetShoeDetails = "Select * from myCart, shoe, orderid where myCart.SID = shoe.SID";
        sqlGetShoeDetails += " ";
         //out.println("<p>Your SQL statement is: " + sqlGetShoeDetails + "</p>"); // Echo for debugging
         ResultSet returnShoeDetails = stmt.executeQuery(sqlGetShoeDetails);  // Send the query to the server                           

        while(returnShoeDetails.next()){
                out.println("<div class=\"rounded p-2 bg-light\">"
                                    +"<div class=\"media mb-2 border-bottom\">"
                                    +"<input type=\"hidden\" name='cartItems' value= '"+returnShoeDetails.getString("myCart.SID")+"' form=\"ToOrder\"/>"
                                    +"<input type=\"hidden\" name='cartShoeSize' value= '"+returnShoeDetails.getString("myCart.Size")+"' form=\"ToOrder\"/>"
                                    +"<input type=\"hidden\" name='cartShoeQuantity' value= '"+returnShoeDetails.getString("myCart.Quantity")+"' form=\"ToOrder\"/>"
                                    +"<input type=\"hidden\" name='orderid' value= '"+returnShoeDetails.getString("orderID")+"' form=\"ToOrder\"/>"
                                    +"<input type=\"hidden\" name='cartItems' value= '"+returnShoeDetails.getString("myCart.SID")+"' form=\"formLogin\"/>"
                                    +"<input type=\"hidden\" name='cartShoeSize' value= '"+returnShoeDetails.getString("myCart.Size")+"' form=\"formLogin\"/>"
                                    +"<input type=\"hidden\" name='cartShoeQuantity' value= '"+returnShoeDetails.getString("myCart.Quantity")+"' form=\"formLogin\"/>"
                                    +"<input type=\"hidden\" name='orderid' value= '"+returnShoeDetails.getString("orderID")+"' form=\"formLogin\"/>"
                                    +"<input type=\"hidden\" name='orderid' value= '"+returnShoeDetails.getString("orderID")+"' form=\"formRegister\"/>"
                                        +"<div class=\"media-body\"> <a href=\"\">"+returnShoeDetails.getString("Model")+"</a>"
                                            +"<div class=\"small text-muted\">Price: $"+returnShoeDetails.getString("Price")+" <span class=\"mx-2\">|</span>Size: "+returnShoeDetails.getString("Size")+"<span class=\"mx-2\">|</span> Qty: "+returnShoeDetails.getString("Quantity")); 
                                            totalAmountForShoe = Integer.parseInt(returnShoeDetails.getString("Price"))*Integer.parseInt(returnShoeDetails.getString("Quantity"));
                                            out.println("<span class=\"mx-2\">|</span> Subtotal: $"+totalAmountForShoe+"</div>"
                                        +"</div>"
                                    +"</div>"
                                +"</div>");
                                total+=totalAmountForShoe;
            


        }
        out.println("</div>"
                +"</div>"
                +"<div class=\"col-md-12 col-lg-12\">");
                        
                            out.println("<div class=\"order-box\">"
                                +"<div class=\"title-left\">"
                                    +"<h3>Your order</h3>"
                                +"</div>"
                                +"<div class=\"d-flex\">"
                                    +"<div class=\"font-weight-bold\">Product</div>"
                                    +"<div class=\"ml-auto font-weight-bold\">Total</div>"
                                +"</div>"
                                +"<hr class=\"my-1\">"
                                +"<div class=\"d-flex\">"
                                    +"<h4>Sub Total</h4>"
                                    +"<div class=\"ml-auto font-weight-bold\"> $"+total+" </div>"
                                +"</div>"
                                +"<div class=\"d-flex\">"
                                    +"<h4>Shipping Cost</h4>"
                                    +"<div class=\"ml-auto font-weight-bold\"> Free </div>"
                                +"</div>"
                                +"<hr>"
                                +"<div class=\"d-flex gr-total\">"
                                    +"<h5>Grand Total</h5>"
                                    +"<div class=\"ml-auto h5\"> $"+total+" </div>"
                                +"</div>"
                                +"<hr> </div>"
                        +"</div>");


                        

//Shopping Submit Button
                        out.println("<div class=\"col-12 d-flex shopping-box\">"
                        +"<form method=\"get\" action=\"orderConfirmation\" id=\"ToOrder\">"
                        +"<input class=\"btn hvr-hover\" data-fancybox-close=\"\" style=\"margin:15px;\" type=\"Submit\" value=\"Back to Shop\" formaction=\"index.html\"/>"
                        +"<input class=\"btn hvr-hover\" data-fancybox-close=\"\" style=\"margin:15px;\" type=\"Submit\" value=\"Place Order\"/>"
                        +"</form>"
                        //+"<div class=\"col-12 d-flex shopping-box\"> <a href=\"Order_Confirmation.html\" class=\"ml-auto btn hvr-hover\">Place Order</a> </div>"
                    +"</div>"
                +"</div>"
            +"</div>"
            +"</div>"
        +"</div>"
    +"</div>");


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