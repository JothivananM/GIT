let menuList;
let pathChangePassword = '/change-password';
let pathPofile = '/profile';

const route = (event) => {
    event = event || window.event;
    event.preventDefault();

    var href = "";

    if (event.target.nodeName === 'I' || event.target.nodeName === 'P') {
        href = event.target.parentNode.href;
    }

    else {
        href = event.target.href;
    }

    window.history.pushState({}, "", href);
    handleLocation();
};

// Gets Unique Sidebar menu list of the user
const getMenubarList = () => {

    $.ajax({
        url: httpURL + "sidebar/id",
        type: 'get',
        dataType: 'json',
        async: false,
        contentType: "application/json",
        headers: { "authorization": $.cookie("auth") },

        success: function (successData) {            

            sidebarList(successData);
            menuList = (successData);

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

getMenubarList();

const handleLocation = async () => {

    let path = window.location.pathname;

    if (path === '/') {
        path = '/' + defaultDashboard;
        
    }

    else if ((path !== pathChangePassword) && (path !== pathPofile)) {

        let hasPath = menuList.some(menuList => menuList.url === path.substring(1));

        if (!hasPath || path == '') {
            path = '/' + '404';
        }
    }

    
    page = '/templates' + path + '.html';
    
    // Breadcrumbs HOME navigation
    if (path == '/employee') {
        $('#home-id').attr('href', '/employee');
    }

    let html = await fetch(page).then((data) => data.text());
    document.getElementById("main-page").innerHTML = html;

    if (path === "/create") {
        $('.select2').select2();
    }

    else if (path === "/list") {
        employeeList();
    }
}


handleLocation();
window.onpopstate = handleLocation;
window.route = route;


