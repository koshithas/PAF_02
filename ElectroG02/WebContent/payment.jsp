<%@page import="com.PaymentDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="ISO-8859-1">
        <title>Payment</title>
        <link rel="stylesheet" href="View/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
            integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
            crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="Components/jquery-3.2.1.min.js"></script>
        <div class="container-fluid">
            <div class="jumbotron bg-dark text-light">
                <center>
                    <h1 style="font-family:Fantasy; margin-top:5px" class="p-2 mt-2">ElectroGrid Systems</h1>
                    <h3 style="font-family:courier;" class="p-2 mt-2">Payment Management</h3>
                </center>
                <div class="navigation">
                    <ul class="nav justify-content-center nav-pills nav-fill">
                        <li class="nav-item"><a class="nav-link" href="home.jsp">Home</a></li>
                        <li class="nav-item"><a class="nav-link active" href="payment.jsp">Payment Management</a></li>
                        <li class="nav-item"><a class="nav-link" href="customer.js">Customer</a>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="manage.js">Customer Management</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Help</a></li>
                    </ul>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4">
                    <div class="card bg-light mb-3 border-danger">
                        <div class="card-header bg-dark text-light">
                            <h4>Pay Now</h4>
                        </div>
                        <div class="card-body ">

                            <form id="formPayment" name="formPayment" method="post" action="payment.jsp" class="col">


                                <!-- Email -->
                                Email:
                                <input id="email" name="email" type="text" class="form-control form-control-sm mt-2"
                                    type="number"> <br>

                                <!-- Amount -->
                                Amount:
                                <input id="amount" name="amount" type="number" class="form-control form-control-sm mt-2"
                                    type="number"> <br>


                                <!-- Card Type -->
                                Card Type:
                                <select id="type" name="type" class="form-control form-control-sm mb-3">
                                    <option value="0">--Select Card Type--</option>
                                    <option value="VISA">VISA</option>
                                    <option value="MASTER CARD">MASTER CARD</option>
                                </select>

                                <!-- Card number -->
                                Card Number:
                                <input id="number" name="number" type="text" class="form-control form-control-sm mt-2"> <br>

                                <!-- Expire date -->
                                Expire Date:
                                <input id="exp" name="exp" type="date" class="form-control form-control-sm" >
                                <br>

                                <!-- CVV -->
                                CVV:
                                <input id="cvv" name="cvv" type="number" class="form-control form-control-sm mt-2"
                                    > <br>
										

                                <!-- Button -->
                                <input id="btnSave" name="btnSave" type="button" value="Save " class="btn btn-primary">
                                <input type=" " id="hidPaymentIDSave" name="hidPaymentIDSave" value="">

                            </form>


                        </div>
                        <div class="card-footer text-light">
                            Status:<br>
                            <div id="alertSuccess" class="alert alert-success"></div>
                            <div id="alertError" class="alert alert-danger"></div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8">
                    <div class="text-center">
                        <h4>Existing Card Details</h4>
                        <hr>
                        <br>
                    </div>
                    <div class="card">
                        <div class="table-responsive-md border-danger">
                            <div id="divPaymentGrid">
								<%
							PaymentDao paymentDao = new PaymentDao();
								out.print(paymentDao.viewPayment());
					%>

                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
            <footer class="bg-dark text-center text-white">
                <!-- Grid container -->
                <div class="container p-4 pb-0">
                    <!-- Section: Social media -->
                    <section class="mb-4">
                        <!-- Facebook -->
                        <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"><i
                                class="fab fa-facebook-f"></i></a>

                        <!-- Twitter -->
                        <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"><i
                                class="fab fa-twitter"></i></a>

                        <!-- Google -->
                        <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"><i
                                class="fab fa-google"></i></a>

                        <!-- Instagram -->
                        <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"><i
                                class="fab fa-instagram"></i></a>

                        <!-- Linkedin -->
                        <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"><i
                                class="fab fa-linkedin-in"></i></a>

                        <!-- Github -->
                        <a class="btn btn-outline-light btn-floating m-1" href="#!" role="button"><i
                                class="fab fa-github"></i></a>
                    </section>
                    <!-- Section: Social media -->
                </div>
                <!-- Grid container -->

                <!-- Copyright -->
                <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
                    © 2020 Copyright:
                    <a class="text-white" href="https://mdbootstrap.com/">MDBootstrap.com</a>
                </div>
                <!-- Copyright -->
            </footer>
        </div>
        <script src="Components/bill.js"></script>
    </head>
	<script src="Components/payment.js"></script>
    <body>

    </body>

    </html>