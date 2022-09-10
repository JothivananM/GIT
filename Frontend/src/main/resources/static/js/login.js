
  $(function () {
   
    $.validator.setDefaults({
      submitHandler: function () {
        loginUser();                    	 
      }     
    });

    $('#quickForm').validate({
      rules: {
        email: {
          required: true,
          email: true,
        },
        password: {
          required: true,
          minlength: 2
        },
        terms: {
          required: true
        },
      },
      messages: {
        email: {
          required: "Please enter a email address",
          email: "Please enter a valid email address"
        },
        password: {
          required: "Please provide a password",
          minlength: "Your password must be at least 8 characters long"
        },
        terms: "Please accept our terms"
      },
      errorElement: 'span',
      errorPlacement: function (error, element) {
        error.addClass('invalid-feedback');
        element.closest('.form-group').append(error);
      },
      highlight: function (element, errorClass, validClass) {
        $(element).addClass('is-invalid');
      },
      unhighlight: function (element, errorClass, validClass) {
        $(element).removeClass('is-invalid');
      }
    });
  });

  // Generates Token using Credentials
  function loginUser(){

    let username = $("#txt-username").val();
    let password = $("#txt-password").val(); 

    let encodedUsername = btoa(username);
    let encodedPassword = btoa(password);

    console.log("btoa", encodedUsername);
    console.log("btoa", encodedPassword);
    console.log("atob", atob(encodedUsername));
    console.log("atob", atob(encodedPassword));

    const emp = {
              companyEmailId :$("#txt-username").val() ,
                password : $("#txt-password").val()
           }
           
         $.ajax({
         url:"http://localhost:8080/user/login",
                 type: 'post',
                 dataType: 'json',
                 async: false,
                 contentType: "application/json",
                // contentType: 'application/x-www-form-urlencoded; charset=UTF-8', (Access Denied)
                 data: JSON.stringify(emp),

                 success: function(successData) {  
                  $.cookie("auth", "Bearer "+ successData.data.accessToken);
                  window.location.replace('http://localhost:8082/'); 
                 },

                 error: function(error) {                                      
                    checkStatus(error);
                 }

             });    
  }
