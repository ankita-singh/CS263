<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Let's Play</title>

    <!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/freelancer.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">


    <style type="text/css">

    .state-icon {
        left: -5px;
    }
    .list-group-item-primary {
        color: rgb(255, 255, 255);
        background-color: rgb(66, 139, 202);
    }

    </style>


</head>

<body id="page-top" class="index">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#page-top">Let's Play!</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <%
                            UserService userService = UserServiceFactory.getUserService();
                            User user = userService.getCurrentUser();
                            if (user != null) {
                                pageContext.setAttribute("user", user);
                    %>
                    <li class="page-scroll">
                        <a href="#contact">Pick your Interests</a>
                    </li>
                    <li class="page-scroll">
                        <a href="#about">Events</a>
                    </li>
                    <li class="page-scroll">
                        <a href="#portfolio">Lets Start</a>
                    </li>
                    <li >
                            
                         
                         <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>" class="btn btn-lg btn-outline" style="width:150px;height:50px;top:-20px; right:-50px; position:relative; padding-bottom:1cm; text-align:center;"  >
                            </i>Logout
                        </a>
                    </li>
                        <%
                            }
                        %>
                    
                    
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <!-- Header -->
    <header>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <img class="img-responsive" src="img/profile.png" alt="">
                    <div class="intro-text">
                        <span class="name">Get Set Go!</span>
                        <hr class="star-light">
                        
                        <%
                        if(user ==null){
                        
                    %>
                        <a href="<%= userService.createLoginURL(request.getRequestURI()) %>" class="btn btn-lg btn-outline">
                         </i>Login
                        </a>
                    <%
                        }
                    %>
                    </div>
                    <div style= "padding-top: 0.5cm;">
                        <span class="skills" style= "font-size: 250%">Let's Be Fit!!</span>
                    </div>
                </div>
            </div>
        </div>
    </header>


     <%
            if(user!=null){
     %>
    <!-- Pick your interests -->
    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>Pick your Interests</h2>
                    <hr class="star-primary">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2" style="text-align:center;">
                    <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
                    <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
                    <ul class="list-group checked-list-box" style="max-height: 300px;overflow: auto;text-align:left;">
                      <li class="list-group-item" data-style="button">Up for a walk?</li>
                      <li class="list-group-item" data-style="button" data-color="success">Basketball!!</li>
                      <li class="list-group-item" data-style="button" data-color="info">Cricket</li>
                      <li class="list-group-item" data-style="button" data-color="warning">Lets Swim</li>
                      <li class="list-group-item" data-style="button" data-color="danger">What about some Soccer</li>
                      <li class="list-group-item" data-style="button" data-color="warning">Ping Pong</li>
                      <li class="list-group-item" data-style="button" data-color="warning">Gyming</li>
                    </ul>
                    <button type="button" class="btn btn-success" >Save</button>
                </div>

            </div>
        </div>
    </section>
    

    <!-- View Activities -->
    <section class="success" id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>View Nearby Events</h2>
                    <hr class="star-light">
                </div>
            </div>
            <div class="row">
            <div id = "portfolio">
                <div class="col-sm-4 portfolio-item" "col-lg-offset-4">
                    <a href="#portfolioModal4" class="portfolio-link" data-toggle="modal">
                        <div class="caption" style="position:relative;left:400px;">
                            <div class="caption-content">
                                <i class="fa fa-search-plus fa-3x"></i>
                            </div>
                        </div>
                        <img src="img/portfolio/circus.png" class="img-responsive img-centered" alt="" style="position:relative;left:400px;">
                    </a>
                </div>
            </div>
                
            </div>
        </div>
    </section>

    <!-- Your Events -->
    <section id="portfolio">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>Let's Start!</h2>
                    <hr class="star-primary">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 portfolio-item">
                    <a href="#portfolioModal1" class="portfolio-link" data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <i class="fa fa-search-plus fa-3x"></i>
                            </div>
                        </div>
                        <h3 style="text-align:center;">Your Events!</h3>
                        <img src="img/portfolio/safe.png" class="img-responsive" alt="">
                    </a>
                </div>
                <div class="col-sm-6 portfolio-item">
                    <a href="#portfolioModal2" class="portfolio-link" data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <i class="fa fa-search-plus fa-3x"></i>
                            </div>
                        </div>
                        <h3 style="text-align:center;">Create New Event!</h3>
                        <img src="img/portfolio/cake.png" class="img-responsive" alt="">
                    </a>
                </div>       
            </div>

            <div class="row">
               
                <div class="col-sm-6 portfolio-item">
                    <a href="#portfolioModal3" class="portfolio-link" data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <i class="fa fa-search-plus fa-3x"></i>
                            </div>
                        </div>
                        <h3 style="text-align:center;">Upcoming Events!</h3>
                        <img src="img/portfolio/submarine.png" class="img-responsive" alt="">
                    </a>
                </div> 

                <div class="col-sm-6 portfolio-item">
                    <a href="#newActivity" class="portfolio-link" data-toggle="modal">
                        <div class="caption">
                            <div class="caption-content">
                                <i class="fa fa-search-plus fa-3x"></i>
                            </div>
                        </div>
                        <h3 style="text-align:center;">New Activity!</h3>
                        <img src="img/portfolio/game.png" class="img-responsive" alt="">
                    </a>
                </div>             
            </div>

        </div>
    </section>

    <!-- Footer -->
    <footer class="text-center">
        <div class="footer-below">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">

                        Copyright &copy; Ucsb CS263
                        <hr class="star-primary">
                    </div>
                </div>
            </div>
        </div>
    </footer> 

    <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
    <div class="scroll-top page-scroll visible-xs visible-sm">
        <a class="btn btn-primary" href="#page-top">
            <i class="fa fa-chevron-up"></i>
        </a>
    </div>

    <!-- Events By User -->
    <div class="portfolio-modal modal fade" id="portfolioModal1" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="modal-body">
                            <h2>Your Events</h2>
                            <hr class="star-primary">
                            <img src="img/portfolio/safe.png" class="img-responsive img-centered" alt="" height="100" width="200">
                            <table id= "eventByUser_table" class="table table-striped table-hover" style="text-align: left;">
                              <tr>
                                 <th >Activity</th>
                                 <th >Day</th>
                                 <th >StartTime</th>
                                 <th >EndTime</th>

                            </tr>
                            

                            </table>

                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Create New Event -->
    <div class="portfolio-modal modal fade" id="portfolioModal2" tabindex="-1" role="dialog" aria-hidden="true" data-focus-on="input:first" style="display: none;" >
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="modal-body">
                            <h2>Create New Event </h2>
                            <hr class="star-primary">
                            <img src="img/portfolio/cake.png" class="img-responsive img-centered" alt="" height="100" width="200">

                            <form id="form1">
                                  <div class="form-group">
                                    <label for="activity">Pick activity!</label>
                                    <select class="form-control" id="activity" placeholder="Activity"> 
                                        <option value="football">Football</option>
                                        <option value="basketball">Basketball</option>
                                    </select>
                                </div>
                                  <div class="form-group">
                                    <label for="day">Pick the day!</label>
                                     <select class="form-control" id="day" placeholder="Day"> 
                                        <option value="1">Sunday</option>
                                        <option value="2">Monday</option>
                                        <option value="3">Tuesday</option>
                                        <option value="4">Wednesday</option>
                                        <option value="5">Thursday</option>
                                        <option value="6">Friday</option>
                                        <option value="7">Saturday</option>
                                    </select>
                                  </div>
                                  <div class="form-group">
                                    <label for="startTime">Pick the start time!</label>
                                    <input type="text" class="form-control" id="s_hour" placeholder="Starting hour...">
                                    <input type="text" class="form-control" id="s_min" placeholder="Starting min...">
                                   
                                  </div>
                                  <div class="form-group">
                                    <label for="startTime">Pick the end time!</label>
                                    <input type="text" class="form-control" id="e_hour" placeholder="Ending hour...">
                                    <input type="text" class="form-control" id="e_min" placeholder="Ending hour...">
                                  </div>
                                  <button onclick="submitData()" class="btn btn-success">Create</button>


                                   <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-times"></i> Cancel</button>
                                </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- upcoming events -->
    <div class="portfolio-modal modal fade" id="portfolioModal3" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="modal-body">
                            <h2>Upcoming Events</h2>
                            <hr class="star-primary">
                            <img src="img/portfolio/submarine.png" class="img-responsive img-centered" alt="" height="100" width="200">
                            <table class="table table-striped table-hover" style="text-align: center;">
                            <tr>
                                 <th style="text-align: center;">Activity</th>
                                 <th style="text-align: center;">Owner</th>
                                 <th style="text-align: center;">Time</th>
                                 <th style="text-align: center;">Participants</th>
                                 <th style="text-align: center;">  </th>
                            </tr>
                            <tr>
                                 <td>BasketBall</td>
                                 <td>Joey</td>
                                 <td>Saturday 11am-1pm</td>
                                 <td>Chandler, Ross</td>
                                 <td><button type="button" class="btn btn-warning">Not Interested</button>
                            </tr>
                              <tr>
                                 <td>BasketBall</td>
                                 <td>Joey</td>
                                 <td>Saturday 11am-1pm</td>
                                 <td>Chandler, Ross</td>
                                 <td><button type="button" class="btn btn-warning">Not Interested</button>
                            </tr>
                           <tr>
                                 <td>BasketBall</td>
                                 <td>Joey</td>
                                 <td>Saturday 11am-1pm</td>
                                 <td>Chandler, Ross</td>
                                 <td><button type="button" class="btn btn-warning">Not Interested</button>
                            </tr>

                            </table>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- New Activity -->
    <div class="portfolio-modal modal fade" id="newActivity" tabindex="-1" role="dialog" aria-hidden="true" data-focus-on="input:first" >
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="modal-body">
                        <h2>Create a new Activity</h2>
                            <hr class="star-primary">
                           <form>
                                  <div class="form-group">
                                    <label for="activityName">Activity Name</label>
                                    <input type="text" class="form-control" id="activity" placeholder="Activity">
                                  </div>
                                  <div class="form-group">
                                    <label for="day">Description</label>
                                    <input type="textarea" class="form-control" id="day" placeholder="Day">
                                  </div>
                                  <button type="button" class="btn btn-success" data-dismiss="modal"> Create Activity</button>
                                </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- All events -->
    <div class="portfolio-modal modal fade" id="portfolioModal4" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="modal-body" style="overflow-y: auto;">
                            <h2>Activities around you!</h2>
                            <hr class="star-primary">
                            <img src="img/portfolio/circus.png" class="img-responsive img-centered" alt="" height="100" width="200">
                            <table id = "event_table" class="table table-striped table-hover" style="text-align: left;">
                            <tr>
                                 <th >Activity</th>
                                 <th >Day</th>
                                 <th >Owner</th>
                                 <th >StartTime</th>
                                 <th >EndTime</th>

                            </tr>
                            

                            </table>



                            <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>

    <%
            }
     %>
    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="js/classie.js"></script>
    <script src="js/cbpAnimatedHeader.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
    <script src="js/contact_me.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/freelancer.js"></script>
    <script src="js/bootbox.min.js"></script>

    <!--  Custom js for the list -->
    <script src="js/myscripts.js"></script>

    <!---<script type="text/javascript" src="js/bootstrap-timepicker.min.js"></script>-->


    <script type="text/javascript">

        

function submitData(){
        
        var eventData = {};
        eventData["activity"] = $('#activity')[0].value;
        eventData["s_hour"] = $('#s_hour')[0].value;
        eventData["s_min"] = $('#s_min')[0].value;
        eventData["e_hour"] = $('#e_hour')[0].value;
        eventData["e_min"] = $('#e_min')[0].value;
        eventData["day"] = $('#day')[0].value;
        console.log(eventData);
      
        
        
        $.ajax({
              url: "/rest/event",
              type: "POST",
              data: JSON.stringify(eventData),
              contentType: "application/json; charset=utf-8",
              dataType: "json",
              success: function(){
                  alert("Your lecture has been created");
              }
            })
    
    }

$(document).ready(function() {
  

   
        $.ajax({

            url: "https://"+window.location.host+"/rest/event/",
            type:'GET',
            dataType: "json",
            success: function (data) {

            var i;
            for(i=0;i<data.length;i++)
              {

                var myEvent = data[i];
                var activity = myEvent["activity"];
                var day = myEvent["day"];
                var owner = myEvent["owner"];
                var s_hour = myEvent["s_hour"];
                var s_min = myEvent["s_min"];
                var e_hour = myEvent["e_hour"];
                var e_min = myEvent["e_min"];

                $('#event_table').append('<tr><td>' + activity + '</td>' + '<td>' + day + '</td>' + '<td>' + owner + '</td>'+ '<td>' + s_hour + '</td>' + '<td>' + e_hour + '</td></tr>');

                
              }
    
            }
            
        });

        $.ajax({

            url: "https://"+window.location.host+"/rest/event/byOwner",
            type:'GET',
            dataType: "json",
            success: function (data) {

            var i;
            for(i=0;i<data.length;i++)
              {

                var myEvent = data[i];
                var activity = myEvent["activity"];
                var day = myEvent["day"];
                var owner = myEvent["owner"];
                var s_hour = myEvent["s_hour"];
                var s_min = myEvent["s_min"];
                var e_hour = myEvent["e_hour"];
                var e_min = myEvent["e_min"];

                $('#eventByUser_table').append('<tr><td>' + activity + '</td>' + '<td>' + day + '</td>'+ '<td>' + s_hour + '</td>' + '<td>' + e_hour + '</td></tr>');

                
              }
    
            }
            
        });
    });

    </script>


</body>

</html>
