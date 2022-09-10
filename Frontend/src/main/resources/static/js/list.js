// Add new user
let deleteId;
const addUser = () => {

    const emp = {

        name: $("#txt-name").val(),
        employeeCode: $("#txt-employeeCode").val(),
        mobileNo: $("#txt-mobile").val(),
        personalEmailId: $("#txt-personalEmail").val(),
        companyEmailId: $("#txt-companyEmail").val(),
        role_id: $('select[name="role"]').val(),
        designation_id: $('select[name="designation"]').val()
    }

    $.ajax({
        url: httpURL + "user",
        type: 'post',
        dataType: 'json',
        async: false,
        contentType: "application/json;charset=utf-8",
        headers: { "authorization": $.cookie("auth") },
        data: JSON.stringify(emp),

        success: function (successData) {  
            toggleCard();
            toastr.success("Data Successfully Added!", "Done!", { timeOut: 4000 });
            console.log("object",employeeDetailList.push(successData));
            generateTable(employeeDetailList);
        },

        error: function (error) {
            if (error.responseJSON.status == '408') {
                checkStatus(error);
            }
            else {
                toastr.error(error.responseJSON.data, error.responseJSON.error);
            }
        }
    });
}

// Update an existing user
const updateUser = () => {

    let empId = $("#id").val();

    const emp = {
        id: $("#id").val(),
        name: $("#txt-name").val(),
        employeeCode: $("#txt-employeeCode").val(),
        mobileNo: $("#txt-mobile").val(),
        personalEmailId: $("#txt-personalEmail").val(),
        companyEmailId: $("#txt-companyEmail").val(),
        role_id: $('select[name="role"]').val(),
        designation_id: $('select[name="designation"]').val(),
        userPassword: $("#txt-mobile").val()
    }
    $("#btn-save").text("Add");

    $.ajax({

        url: httpURL + "user",
        type: 'put',
        dataType: 'json',
        async: false,
        contentType: "application/json;charset=utf-8",
        headers: { "authorization": $.cookie("auth") },
        data: JSON.stringify(emp),

        success: function (successData) {

            toastr.info("Records updated ...", "Done!", { timeOut: 5000 });
            toggleCard();

            let employeeIndex = employeeDetailList.findIndex(
                (empList) => empList.id == empId);

            employeeDetailList[employeeIndex] = successData;
            
            generateTable(employeeDetailList);

        },

        error: function (error) {
            if (error.responseJSON.status == '408') {
                checkStatus(error);
            }
            else {
                toastr.error(error.responseJSON.data, error.responseJSON.error);
            }
        }
    });
}

// Select a particular user
const selectEmployee = (empId) => {

    document.body.scrollTop = 0; //Safari
    document.documentElement.scrollTop = 0; //Chrome, Edge, Firefox, Opera

    $('#my-card-widget').CardWidget('toggle');
    $('#lbl-addEmployee').text('Update Employee Details');

    let employeeRecord = employeeDetailList.find(({ id }) => id === empId);

    $("#id").val(employeeRecord.id);
    $("#txt-name").val(employeeRecord.name);
    $("#txt-employeeCode").val(employeeRecord.employeeCode);
    $("#txt-personalEmail").val(employeeRecord.personalEmailId);
    $("#txt-companyEmail").val(employeeRecord.companyEmailId);
    $("#txt-mobile").val(employeeRecord.mobileNo);
    $("#select-role").select2("val", employeeRecord.designation_id.toString());
    $("#select-designation").select2("val", employeeRecord.designation_id.toString());

    $("#btn-save").text("Update");
    $("#model-name").text("Update User");

}

// Retrieve list of users
const employeeList = () => {

    $('.select2').select2();
   
    $.ajax({
        url: httpURL + "user",
        type: 'get',
        dataType: 'json',
        async: false,
        contentType: "application/json;charset=utf-8",
        headers: { "authorization": $.cookie("auth") },

        success: function (successData) {

            employeeDetailList = successData;
            generateTable(successData);
        },

        error: function (error) {
            if (error.responseJSON.status == '408') {
                checkStatus(error);
            }
            else {
                toastr.error(error.responseJSON.data, error.responseJSON.error);
            }
        }
    });
}
// Confirm Delete
const modelDelete = (id) => {
    deleteId = id;
    $('#deleteModel').modal('show');
    // $('#deleteModel').show();
}
// Soft Delete an existing user
const deleteUser = () => {

    const empDetails = {
        id: deleteId,
        isActive: "false"
    }

    $.ajax({
        url: httpURL + "user",
        type: 'delete',
        contentType: "application/json; charset=utf-8",
        async: false,
        headers: { "authorization": $.cookie("auth") },
        data: JSON.stringify(empDetails),

        success: function () {
            $('#deleteModel').modal('hide');
            toastr.success("Deleted Successfully!", "Done!", { timeOut: 5000 });
            employeeDetailList.splice(employeeDetailList.findIndex(empList => empList.id === deleteId) , 1)
            generateTable(employeeDetailList);
        },

        error: function (error) {
            if (error.responseJSON.status == '408') {
                checkStatus(error);
            }
            else {
                toastr.error(error.responseJSON.data, error.responseJSON.error);
            }
        }
    });
}



