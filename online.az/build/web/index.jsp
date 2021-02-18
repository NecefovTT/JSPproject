
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Pojo.Users"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Pojo.Product"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Map"%>
<%@page import="Pojo.Brand"%>
<%@page import="Pojo.Category"%>
<%@page import="Pojo.Menu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Tools Shop</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="style.css" />
        <!--[if IE 6]>
        <link rel="stylesheet" type="text/css" href="iecss.css" />
        <![endif]-->
        <script type="text/javascript" src="js/boxOver.js"></script>
    </head>


    <jsp:useBean id="dbc" class="DBConnected.DBConnection"/>
    <%
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lang = "az";
        try {
            if (request.getParameter("language") != null) {
                session.setAttribute("lg", request.getParameter("language"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("lg", "az");

        }

        try {
            if (session.getAttribute("lg") != null) {
                lang = session.getAttribute("lg").toString();
            } else {
                session.setAttribute("lg", "az");

            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("lg", "az");
            lang = "az";
        }

        Map<String, String> lgMap = dbc.listPageLanguageIndex(lang);

        int b_id = 0;
        if (request.getParameter("b_id") != null) {
            session.setAttribute("b_id", request.getParameter("b_id"));
            session.setAttribute("c_id", 0);
        } else {
            b_id = 0;
        }

        int cat_id = 0;
        if (request.getParameter("cat_id") != null) {
            session.setAttribute("cat_id", request.getParameter("cat_id"));
            session.setAttribute("b_id", 0);
        } else {
            cat_id = 0;
        }

        try {
            if (session.getAttribute("b_id") != null) {
                b_id = Integer.parseInt(session.getAttribute("b_id").toString());
            }
        } catch (Exception e) {
            b_id = 0;
        }

        try {
            if (session.getAttribute("cat_id") != null) {
                cat_id = Integer.parseInt(session.getAttribute("cat_id").toString());
            }
        } catch (Exception e) {
            cat_id = 0;
        }

        int say = 0;

        try {
            try {
                if (request.getParameter("setbasket") != null) {
                    if (session.getAttribute("sessionsetbasket") != null) {
                        String ssb = session.getAttribute("sessionsetbasket").toString();
                        session.setAttribute("sessionsetbasket", ssb + "-" + request.getParameter("setbasket"));
                    } else {
                        session.setAttribute("sessionsetbasket", request.getParameter("setbasket"));
                    }
                }
            } catch (Exception e) {
            }

            try {
                if (request.getParameter("deleteBasket") != null) {
                    String dsb = "";
                    for (String ss : session.getAttribute("sessionsetbasket").toString().split("-")) {
                        if (!ss.equals(request.getParameter("deleteBasket"))) {
                            if (dsb.equals("")) {
                                dsb = ss;
                            } else {
                                dsb = dsb + "-" + ss;
                            }
                        }
                    }
                    session.setAttribute("sessionsetbasket", dsb);
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (request.getParameter("btnSignUp") != null) {
            Users u = new Users();
            u.setName(request.getParameter("txtNameReg"));
            u.setSurname(request.getParameter("txtSurnameReg"));
            u.setBirthday(sdf.format(new Date()));
            if (request.getParameter("rbGender").equals("M")) {
                u.setGender('M');
            } else if (request.getParameter("rbGender").equals("F")) {
                u.setGender('F');
            }
            u.setMobile(request.getParameter("txtMobile"));
            u.setPhone(request.getParameter("txtPhone"));
            u.setEmail(request.getParameter("txtEmail"));
            u.setAddress(request.getParameter("txtAddress"));
            u.setCode(request.getParameter("txtCode"));
            u.setUsername(request.getParameter("txtUsername"));
            if (request.getParameter("txtPassword").equals(request.getParameter("txtPassword2"))) {
                u.setPassword(request.getParameter("txtPassword"));
            }

            u.setRegister(sdf.format(new Date()));
            u.setRole_id(1);
            u.setStatu(1);
            dbc.insertUsers(u);
        }
    %>
    <body>
        <div id="main_container">
            <div id="header">
                <div class="top_right">
                    <div class="languages">
                        <div class="lang_text"></div>
                        <a href="/online.az/index.jsp?language=az" class="lang">
                            <img src="language/aze.png" alt="" border="0" />
                        </a> 
                        <a href="/online.az/index.jsp?language=en" class="lang">
                            <img src="language/en.png" alt="" border="0" />
                        </a> 
                        <a href="/online.az/index.jsp?language=ru" class="lang">
                            <img src="language/ru.png" alt="" border="0" />
                        </a> 
                    </div>
                    <div class="big_banner"> <a href="http://all-free-download.com/free-website-templates/"><img src="images/banner728.jpg" alt="" border="0" /></a> </div>
                </div>
                <div id="logo"> <a href="http://all-free-download.com/free-website-templates/"><img src="images/logo.png" alt="" border="0" width="182" height="85" /></a> </div>
            </div>
            <div id="main_content">
                <div id="menu_tab">
                    <ul class="menu">
                        <% for (Menu m
                                    : dbc.listMenu(lang)) {%>
                        <li>
                            <a href="<%=m.getPage()%>?language=<%=lang%>" class="nav"> <%=m.getName()%> </a>
                        </li>
                        <li class="divider"></li>
                            <%}%> 

                    </ul>
                </div>
                <!-- end of menu tab -->
                <div class="crumb_navigation"> <%=lgMap.get("navigation")%>: <span class="current">Home</span> </div>
                <div class="left_content">
                    <div class="title_box"><%=lgMap.get("brand")%></div>
                    <ul class="left_menu">
                        <%
                            boolean cls = true;

                            for (Brand b : dbc.listBrand(lang)) {
                                if (cls) {%>
                        <li class="odd">
                            <a href="index.jsp?b_id=<%=b.getId()%>"><%=b.getName()%></a>
                        </li>
                        <%
                            cls = false;
                        } else {%>
                        <li class="even">
                            <a href="index.jsp?b_id=<%=b.getId()%>"><%=b.getName()%></a>
                        </li>
                        <%
                                cls = true;
                            }
                        %>



                        <% }%>

                    </ul>
                    <div class="title_box"><%=lgMap.get("category")%></div>
                    <ul class="left_menu">
                        <%
                            boolean cl = true;

                            for (Category c : dbc.listCategory(lang)) {
                                if (cl) {%>
                        <li class="odd">
                            <a href="index.jsp?cat_id=<%=c.getId()%>"><%=c.getName()%></a>
                        </li>
                        <%
                            cl = false;
                        } else {%>
                        <li class="even">
                            <a href="index.jsp?cat_id=<%=c.getId()%>"><%=c.getName()%></a>
                        </li>
                        <%
                                cl = true;
                            }
                        %>



                        <% }%>

                    </ul>
                </div>
                <!-- end of left content -->
                <div class="center_content">
                    <div class="oferta"> <img src="images/p1.png" width="165" height="113" border="0" class="oferta_img" alt="" />
                        <div class="oferta_details">
                            <div class="oferta_title">Power Tools BST18XN Cordless</div>
                            <div class="oferta_text"> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco </div>
                            <a href="http://all-free-download.com/free-website-templates/" class="prod_buy">details</a> </div>
                    </div>



                    <%
                        if (b_id != 0 && cat_id == 0) {
                            cat_id = 0;
                            for (Product p : dbc.listProductByBrand(lang, b_id)) {
                                if (cat_id != p.getCategory_id()) {%>
                    <div class="center_title_bar"><%=dbc.findByCatId(lang, p.getCategory_id()).getName()%></div>
                    <% cat_id = p.getCategory_id();
                        }%>


                    <div class="prod_box">
                        <div class="center_prod_box">
                            <div class="product_title"><a href="#"><%=p.getName()%></a></div>
                            <div class="product_img"><a href="#"><img width="100" height="100" src="images/<%=p.getImage()%>" alt="" border="0" /></a></div>
                            <div class="prod_price"><span class="reduce">350$</span> <span class="price"><%=p.getPrice()%></span></div>
                        </div>
                        <div class="prod_details_tab"> <a href="/online.az/index.jsp?setbasket=<%=p.getId()%>" class="prod_buy"><%=lgMap.get("addbasket")%></a> <a href="http://all-free-download.com/free-website-templates/" class="prod_details">Details</a> </div>
                    </div>

                    <%} %>
                    <% } else if (cat_id != 0 && b_id == 0) {
                        b_id = 0;
                        for (Product p : dbc.listProductByCategory(lang, cat_id)) {
                            if (b_id != p.getBrand_id()) {%>
                    <div class="center_title_bar"><%=dbc.findByBrandId(lang, p.getBrand_id()).getName()%></div>
                    <% b_id = p.getBrand_id();
                        }%>



                    <div class="prod_box">
                        <div class="center_prod_box">
                            <div class="product_title"><a href="#"><%=p.getName()%></a></div>
                            <div class="product_img"><a href="#"><img width="100" height="100" src="images/<%=p.getImage()%>" alt="" border="0" /></a></div>
                            <div class="prod_price"><span class="reduce">350$</span> <span class="price"><%=p.getPrice()%></span></div>
                        </div>
                        <div class="prod_details_tab"> <a href="/online.az/index.jsp?setbasket=<%=p.getId()%>" class="prod_buy"><%=lgMap.get("addbasket")%></a> <a href="#" class="prod_details">Details</a> </div>
                    </div>

                    <%}
                        }%>






                </div>
                <!-- end of center content -->
                <div class="right_content">
                    <div class="title_box"><%=lgMap.get("search")%></div>
                    <div class="border_box">
                        <input type="text" name="newsletter" class="newsletter_input" placeholder="<%=lgMap.get("keyword")%>"/>
                        <a href="http://all-free-download.com/free-website-templates/" class="join"><%=lgMap.get("search")%></a> </div>

                    <div class="border_box">
                        <input type="text" name="txtUsername" class="newsletter_input" placeholder="<%=lgMap.get("username")%>"/>
                        <input type="password" name="txtPassword" class="newsletter_input" placeholder="<%=lgMap.get("password")%>"/>
                        <a href="http://all-free-download.com/free-website-templates/" class="join"><%=lgMap.get("login")%></a> </div>
                    <div class="title_box"><a href="register.jsp" style="text-decoration: none; color: #159dcc;"><%=lgMap.get("register")%></a></div>
                    <div class="shopping_cart">
                        <div class="title_box"><%=lgMap.get("basket")%></div>
                        <% try {
                                if (session.getAttribute("sessionsetbasket") != null) {
                                    for (String ss : session.getAttribute("sessionsetbasket").toString().split("-")) {
                                        if (ss != null && !ss.equals("")) {
                                            say++;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                            }


                        %>
                        <div class="cart_details"> <%=say%> <%=lgMap.get("item")%> <br />



                            <%
                                int total_price = 0;
                                try {

                            %>
                            <table>
                                <% try {
                                        if (session.getAttribute("sessionsetbasket") != null) {
                                            for (String ss : session.getAttribute("sessionsetbasket").toString().split("-")) {
                                                if (ss != null && !ss.equals("")) {
                                                    Product basketproduct = dbc.listProductById(lang, Integer.parseInt(ss));
                                                    total_price += basketproduct.getPrice();

                                %>

                                <tr>
                                    <td><%=basketproduct.getName()%></td>
                                    <td><a href="index.jsp?deleteBasket=<%=basketproduct.getId()%>"><img style="width: 20px; height: 20px" src="images/delete.png"/></a></td>
                                </tr>

                                <%
                                                }

                                            }
                                        }

                                    } catch (Exception ex) {

                                    }
                                %>
                            </table>
                            <%
                                } catch (Exception ex) {

                                }
                            %>



                            <span class="border_cart"></span> <%=lgMap.get("total")%>: <span class="price"><%=total_price%> AZN</span> </div>
                        <div class="cart_icon"><a href="http://all-free-download.com/free-website-templates/"><img src="images/shoppingcart.png" alt="" width="35" height="35" border="0" /></a></div>
                    </div>
                </div>
                <!-- end of right content -->
            </div>
            <!-- end of main content -->
            <div class="footer">
                <div class="left_footer"> <img src="images/footer_logo.png" alt="" width="89" height="42"/> </div>
                <div class="center_footer"> Template name. All Rights Reserved 2008<br />
                    <a href="http://csscreme.com"><img src="images/csscreme.jpg" alt="csscreme" title="csscreme" border="0" /></a><br />
                    <img src="images/payment.gif" alt="" /> </div>
                <div class="right_footer"> <a href="http://all-free-download.com/free-website-templates/">home</a> <a href="http://all-free-download.com/free-website-templates/">about</a> <a href="http://all-free-download.com/free-website-templates/">sitemap</a> <a href="http://all-free-download.com/free-website-templates/">rss</a> <a href="http://all-free-download.com/free-website-templates/">contact us</a> </div>
            </div>
        </div>
        <!-- end of main_container -->
        <div align=center>This template  downloaded form <a href='http://all-free-download.com/free-website-templates/'>free website templates</a></div></body>
</html>


