
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

    <script>
        $(function () {
            $("#Form1").submit(function () {
                var x = $("#Password1").val().trim();
                var y = $("#Password2").val().trim();
                if (x != y) {
                    alert("Wifreni duzgun daxil edin");
                    $("#Password2").val("").focus();
                }
            });

        });
    </script>
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
                    <form method="post" action="index.jsp" id="Form1">
                        <div id="login-box" style="height: 900px">
                            <div class="left">
                                <h1>Sign up</h1>
                                <input type="text" name="txtNameReg" placeholder="Name"/>
                                <input type="text" name="txtSurnameReg" placeholder="Surname"/>
                                <input type="date" name="txtDate" /></br>
                                <p>Male </p><input type="radio" name="rbGender" id="Gender" value="M"/> 
                                <p>Female </p><input type="radio" name="rbGender" id="Gender" value="F"/>
                                <input type="text" name="txtMobile" placeholder="Mobile"/>
                                <input type="text" name="txtPhone" placeholder="Phone"/>
                                <input type="text" name="txtEmail" placeholder="E-mail" />
                                <input type="text" name="txtAddress" placeholder="Address"/>
                                <input type="text" name="txtCode" placeholder="Code"/>

                                <input type="text" name="txtUsername" placeholder="Username" />
                                <input type="password" name="txtPassword" id="Password1" placeholder="Password" />
                                <input type="password" name="txtPassword2" id="Password2" placeholder="Retype password" />


                                <input type="submit" name="btnSignUp" value="Sign me up" />
                            </div>

                            <div class="right">
                                <span class="loginwith">Sign in with<br />social network</span>

                                <button class="social-signin facebook">Log in with facebook</button>
                                <button class="social-signin twitter">Log in with Twitter</button>
                                <button class="social-signin google">Log in with Google+</button>
                            </div>
                            <div class="or">OR</div>
                        </div>
                    </form>


                </div>
                <!-- end of center content -->
                <div class="right_content">

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


