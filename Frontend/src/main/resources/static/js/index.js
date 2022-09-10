
   $(function () {

    alert

    const currentYear = document.querySelector("#current-year");
    currentYear.innerHTML = new Date().getFullYear();
    $('i').tooltip();

    $.validator.setDefaults({
      submitHandler: function () {

        if ($("#id").val()) {
          updateUser();
        }
        else{
          addUser();
        }       
       
      } 
    });

    $('#quickForm').validate({
      rules: {
        companyEmail: {
          required: true,
          email: true,
        },
        personalEmail: {
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
        firstName:{
          required: true,
          minlength: 3
        },
        lastName:{
          required: true,
          minlength: 4
        },
        employeeCode:{
          required: true
        },
        designation:{
          required: true
        },
        role:{
          required: true
        },
        mobile: {
          required: true
        },
        
      },
      messages: {

        companyEmail: {
          required: "Please enter a email address",
          email: "Please enter a valid email address"
        },
        password: {
          required: "Please provide a password",
          minlength: "Your password must be at least 8 characters long"
        },
        firstName: {
          required: "Please provide a valid first name",
          minlength: "Your name must be at least 3 characters long"
        },
        lastName: {
          required: "Please provide a valid last name",
          minlength: "Your last name must be at least 4 characters long"
        },
        employeeCode:{
          required: "Employee Code is mandatory"
        },
        personalEmail: {
          required: "Please enter a email address",
          email: "Please enter a valid email address"
        },
        designation: {
          required: "Designation is mandatory"
        },
        role: {
          required: "Role is mandatory"
        },
        mobile: {
          required: "Mobile no is mandatory"
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

  // Clearing cookies and going back to Login
  window.onbeforeunload = function (e) {
    e.preventDefault();
    if (window.location.pathname == '/' || window.location.pathname == '') {
      
      swal({
        title: "Are you sure?",
        text: "You are about to logout and redirected to Login...",
        icon: "warning",
        buttons: [
          'No, cancel it!',
          'Yes, I am sure!'
        ],
        dangerMode: true,

    }).then(function (isConfirm) {
      if (isConfirm) {
        allCookies = document.cookie;      
        document.cookie = "auth=; path=/";
        window.location = "login";
      }
      else {
        console.log("Prevented");
        e.preventDefault();
      }
        
    });
      
    }
  }

  // $(function () {
  //   $("#example1").DataTable({
  //     "responsive": true, "lengthChange": false, "autoWidth": false,
  //     "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
  //   }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
  //   $('#example2').DataTable({
  //     "paging": true,
  //     "lengthChange": false,
  //     "searching": false,
  //     "ordering": true,
  //     "info": true,
  //     "autoWidth": false,
  //     "responsive": true,
  //   });
  // });

