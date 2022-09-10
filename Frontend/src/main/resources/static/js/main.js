const httpURL = "http://localhost:8080/"

let defaultDashboard = '';

let employeeDetailList = {};

let roleId;

// Populates sidebar menu
const sidebarList = (list) => {

    let sidebarMenu = $("#ul-div");

    $.each(list, function (i, users) {
        if (users.menuName === 'Dashboard') {
            defaultDashboard = users.url;

            $("<li class='nav-item my-nav-item'><a href='" + users.url + "'class='nav-link my-item'onClick='route()'id='my-item'><i class='nav-icon far fa-chart-bar' id='item-id' ></i><p>" + users.menuName + "</p></a> </li>").appendTo(sidebarMenu);
        }
        else {
            $("<li class='nav-item my-nav-item'><a href='" + users.url + "'class='nav-link my-item'onClick='route()'id='my-item'><i class='nav-icon far fa-chart-bar' id='item-id' ></i><p>" + users.menuName + "</p></a> </li>").appendTo(sidebarMenu);
        }
    })

}

// populates data in the table
const generateTable = (displayRecords) => {

    $('#example').DataTable({  

        stateSave: true,
        "responsive": true,     
        "pageLength": 5,
        "bDestroy": true,
        dom: 'Bfrtip',
        
        buttons: [
            {
                extend: 'csv',
                className: 'btn-primary',
                text: 'CSV',
                exportOptions: {
                    columns:[0,1,2,3],
                    modifier: {
                        page: 'current'
                    }
                }
            },
            {
                extend: 'excel',
                className: 'btn-primary',
                text: 'Excel',
                exportOptions: {
                    columns:[0,1,2,3],
                    modifier: {
                        page: 'current'
                    }
                }
            }
        ],

        data: displayRecords,
    
    columns: [ 

                {data: 'name'},
                {data: 'employeeCode'},
                {
                    data: 'role_id',
                    render: function(data, type, row) {
                        if (data == 1) {
                            return '<span>Admin</span>'
                        } else {
                            return '<span>Employee</span>'
                        }
                    }
                },
                {   
                    data: 'designation_id',
                    render: function(data) {
                        if (data == 1) {
                            return '<span>Senior Developer</span>'
                        } else {
                            return '<span>Trainee</span>'
                        }
                    }
                },
                {data: 'companyEmailId'},
                {data: 'mobileNo'},
                {
                    data: null,
                    render: function(data) {
                        // console.log(data);
                        // return "<button type='button' id='btn-edit' class='btn btn-outline-secondary btn-sm' onclick=selectEmployee(" + data.id + ")>✏ </button>&nbsp;<button type='button' id='btn-delete' class='btn btn-outline-secondary btn-sm' onclick=deleteUser(" + data.id + ")>🗑 </button>";
                        return "<button type='button' id='btn-edit' class='btn btn-outline-secondary btn-sm' onclick=selectEmployee(" + data.id + ")>✏ </button>&nbsp;<button type='button' id='btn-delete' class='btn btn-outline-secondary btn-sm' onclick=modelDelete(" + data.id + ")>🗑 </button>";
                        
                    } 
                }           

            ]
    }
  
    );
    // $(".buttons-html5").addClass("btn");
    $(".buttons-html5").addClass(" btn btn-primary");

    // let tr;
    // $('#emp-body').html(''); //Clears the table
    // $.each(
    //     displayRecords,
    //     function (i, l) {
            
    //         tr = $('<tr/>');

    //         tr.append("<td>" + displayRecords[i].name + "</td>");
    //         tr.append("<td>" + displayRecords[i].employeeCode + "</td>");

    //         if (displayRecords[i].role_id == 1) {
    //             tr.append("<td>Admin</td>");
    //         }
    //         else {
    //             tr.append("<td>Employee</td>");
    //         }

    //         if (displayRecords[i].designation_id == 1) {
    //             tr.append("<td>MD</td>")
    //         }
    //         else {
    //             tr.append("<td>Developer</td>")
    //         }
    //         tr.append("<td>" + displayRecords[i].companyEmailId + "</td>");
    //         tr.append("<td>" + displayRecords[i].mobileNo + "</td>");
    //         tr.append("<td id='edit-link'><button type='button' id='btn-edit' class='btn btn-outline-secondary btn-sm' onclick=selectEmployee(" + displayRecords[i].id + ")>✏ <span>Edit</span></button>&nbsp;<button type='button' id='btn-delete' class='btn btn-outline-secondary btn-sm' onclick=deleteUser(" + displayRecords[i].id + ")>🗑 <span>Delete</span></button></td>");


    //         $('#emp-body').append(tr);

    //     });
}

// Clears both values and validation of the form
resetForm = () => {

    var validator = $("#quickForm").validate();
    validator.resetForm();
    $("#quickForm").trigger("reset");

    $("#select-designation").select2("val", "0");
    $("#select-role").select2("val", "0");
}

// Open/Close the Card
toggleCard = () => {

    resetForm();
    $("#id").val("");
    $('#my-card-widget').CardWidget('toggle');
    $('#lbl-addEmployee').text('Add New Employee');
    $('#btn-save').text('Add');
    scrollToTop();
}

// Scrolls to the top of the document
function scrollToTop () {

    window.scroll({
        top: 0,
        behaviour: "smooth"
    });
};

// Redirects to the login
const checkStatus = (error) => {

    swal({
        title: error.responseJSON.error,
        text: error.responseJSON.data
    }).then(function () {
        allCookies = document.cookie;      
        document.cookie = "auth=; path=/";
        window.location = "login";
    });

}
