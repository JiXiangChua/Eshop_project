// To save as "ebookshop\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/productDetails")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class ProductPageServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/html");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
      //String[] Model = request.getParameterValues("Model");
      
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

      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/limitedshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
        String Model[] = request.getParameterValues("Model");
        //out.print(Model[0]);
        String sqllStr = "Select * from shoe, productDesp, shoeSize, orderid where shoe.SID = productDesp.SID and shoe.SID = shoeSize.SID and Model = ";
         for (int i = 0; i < 1; ++i){
               sqllStr += "'"+Model[i]+"'";
         }
         sqllStr += " ";
         //out.println("<p>Your SQL statement is: " + sqllStr + "</p>"); // Echo for debugging
          ResultSet rset1 = stmt.executeQuery(sqllStr);  // Send the query to the server
        
          int shoeQuantity = 100;
          String modelName = "Null";
          String amountShoe = " ";
          String shoeSID = " ";
          String orderID = " ";
//<!-- Start Shop Detail  -->
    out.println("<div class=\"shop-detail-box-main\">"
        +"<div class=\"container\">"
            +"<div class=\"row\">"
                +"<div class=\"col-xl-5 col-lg-5 col-md-6\">"
                    +"<div id=\"carousel-example-1\" class=\"single-product-slider carousel slide\" data-ride=\"carousel\">"
                        +"<div class=\"carousel-inner\" role=\"listbox\">");
                            //printing the result set.
                            
                            while (rset1.next()){
                                //out.println(rset1.getString("PhotoID"));
        
                            out.println("<div class=\"carousel-item active\"> <img class=\"d-block w-100\" src=\"images/my_images/Shoe/Shoe"+rset1.getString("PhotoID")+".jpg\" alt=\"First slide\"> </div>"
                            +"<div class=\"carousel-item\"> <img class=\"d-block w-100\" src=\"images/my_images/Shoe/Shoe"+rset1.getString("PhotoID")+"a.jpg\" alt=\"Second slide\"> </div>"
                            +"<div class=\"carousel-item\"> <img class=\"d-block w-100\" src=\"images/my_images/Shoe/Shoe"+rset1.getString("PhotoID")+"b.jpg\" alt=\"Third slide\"> </div>"
                            +"</div>");
                          
                        
                              out.println("<a class=\"carousel-control-prev\" href=\"#carousel-example-1\" role=\"button\" data-slide=\"prev\"> "
                              +"<i class=\"fa fa-angle-left\" aria-hidden=\"true\"></i>"
                              +"<span class=\"sr-only\">Previous</span> "
                            +"</a>"
                                          +"<a class=\"carousel-control-next\" href=\"#carousel-example-1\" role=\"button\" data-slide=\"next\"> "
                              +"<i class=\"fa fa-angle-right\" aria-hidden=\"true\"></i> "
                              +"<span class=\"sr-only\">Next</span> "
                            +"</a>"
                                          +"<ol class=\"carousel-indicators\">"
                                              +"<li data-target=\"#carousel-example-1\" data-slide-to=\"0\" class=\"active\">"
                                                  +"<img class=\"d-block w-100 img-fluid\" src=\"images/my_images/Shoe/Shoe"+rset1.getString("PhotoID")+".jpg\" alt=\"\" />"
                                              +"</li>"
                                              +"<li data-target=\"#carousel-example-1\" data-slide-to=\"1\">"
                                                  +"<img class=\"d-block w-100 img-fluid\" src=\"images/my_images/Shoe/Shoe"+rset1.getString("PhotoID")+"a.jpg\" alt=\"\" />"
                                              +"</li>"
                                              +"<li data-target=\"#carousel-example-1\" data-slide-to=\"2\">"
                                                  +"<img class=\"d-block w-100 img-fluid\" src=\"images/my_images/Shoe/Shoe"+rset1.getString("PhotoID")+"a.jpg\" alt=\"\" />"
                                              +"</li>"
                                          +"</ol>"
                                      +"</div>"
                                  +"</div>"
                                  +"<div class=\"col-xl-7 col-lg-7 col-md-6\">"
                                      +"<div class=\"single-product-details\">"
                                          +"<h2>"+rset1.getString("Model")+"</h2>" //Shoe name
                                          +"<h5>$ "+rset1.getString("Price")+"</h5>"
                                          //need to update the sold value as to retrieve from Evy database.
                                          +"<p class=\"available-stock\" style=\"color:#E2300A\"><span> Stock for Size 40: "+ rset1.getString("UK40")+" available </span>"
                                              +"<p>"
                                          +"<p class=\"available-stock\" style=\"color:#E2300A\"><span> Stock for Size 42: "+ rset1.getString("UK42")+" available </span>"
                                              +"<p>"
                                          +"<p class=\"available-stock\" style=\"color:#E2300A\"><span> Stock for Size 44: "+ rset1.getString("UK44")+" available </span>"
                                              +"<p>"
                                                  +"<h4>Short Description:</h4>"
                                                  +"<p style=\"color:#5B5A59 \">"+rset1.getString("Description")+"</p>"
                                                  +"<ul>");

                              shoeQuantity = Integer.parseInt(rset1.getString("UK40"))+Integer.parseInt(rset1.getString("UK42"))+Integer.parseInt(rset1.getString("UK44"));
                              modelName = rset1.getString("Model");
                              amountShoe = rset1.getString("Price");
                              shoeSID = rset1.getString("SID");
                              orderID = rset1.getString("orderid.orderID");


                            }
                                out.println("<form method=\"get\" action=\"buyNowPayment\">");
                                out.println("<p>Select Size: <select name = \"dropdown\" style=\"width: 200px;height:50px;\"></p>"
                                            +"<option name= \"UK40\" value = \"UK40\" selected>Size UK 40</option>"
                                            +"<option name= \"UK42\" value = \"UK42\" selected>Size UK 42</option>"
                                            +"<option name= \"UK44\" value = \"UK44\" selected>Size UK 44</option>"
                                            +"</select>"
                                            +"<p>Quantity: <input type='number' name=qty value ='1' min = '1' max ='"+shoeQuantity+"'/></p>"
                                            +"<input type=\"hidden\" name=\"Model\" value=\""+modelName+"\" />"
                                            +"<input type=\"hidden\" name=\"AmountDetails\" value=\""+amountShoe+"\" />"
                                            +"<input type=\"hidden\" name=\"shoeSID\" value=\""+shoeSID+"\" />"
                                            +"<input type=\"hidden\" name=\"orderid\" value=\""+orderID+"\" />"
                                            +"<div class=\"price-box-bar\">"
                                                +"<div class=\"cart-and-bay-btn\">"
                                                    +"<input class=\"btn hvr-hover\" data-fancybox-close=\"\" style=\"margin:5px;\" type=\"Submit\" value=\"Buy Now\""
                                                    +"<a class=\"btn hvr-hover\" data-fancybox-close=\"\" href=\"#\"></a>"// do not delete this line if not will have error

                                                    +"<input class=\"btn hvr-hover\" data-fancybox-close=\"\" style=\"margin:15px;\" type=\"Submit\" value=\"Add to Cart\" formaction=\"myCart\""
                                                    //formaction brings me to another webserlvet
                                                    +"<a class=\"btn hvr-hover\" data-fancybox-close=\"\" href=\"#\"></a>" // do not delete this line if not will have error
                                            +"</form>"
                                                +"</div>"
                                            +"</div>");
//Below this chunk of codes are Original codes that help me to build up the above buttons.
                    /*out.println("<div class=\"price-box-bar\">"
                                    +"<div class=\"cart-and-bay-btn\">"
                                      +"<form method=\"get\" action=\"firstTest\">"
                                        //+"<a class=\"btn hvr-hover\" data-fancybox-close=\"\" href=\"#\">Buy New</a>"
                                      +"<input type =\"hidden\" name"
                                        +"<input class=\"btn hvr-hover\" data-fancybox-close=\"\" type=\"Submit\" value=\"Buy New\""
                                        +"<a class=\"btn hvr-hover\" data-fancybox-close=\"\" href=\"#\"></a>"// do not delete this line if not will have error
                                      +"</form>"
                                      +"<form method=\"get\" action=\"firstTest22\">"
                                        //+"<a class=\"btn hvr-hover\" data-fancybox-close=\"\" href=\"#\">Buy New</a>"
                                        +"<input class=\"btn hvr-hover\" data-fancybox-close=\"\" type=\"Submit\" value=\"Add to Cart\""
                                        +"<a class=\"btn hvr-hover\" data-fancybox-close=\"\" href=\"#\"></a>" // do not delete this line if not will have error
                                      +"</form>"
                                    +"</div>"
                                  +"</div>"*/
                    out.println("</div>"
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