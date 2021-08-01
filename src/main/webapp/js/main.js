
/*===== SHOW NAVBAR  =====*/ 
const showNavbar = (toggleId, navId, bodyId, headerId) =>{
    const toggle = document.getElementById(toggleId),
    nav = document.getElementById(navId),
    bodypd = document.getElementById(bodyId),
    headerpd = document.getElementById(headerId)

    // Validate that all variables exist
    if(toggle && nav && bodypd && headerpd){
        toggle.addEventListener('click', ()=>{
            // show navbar
            nav.classList.toggle('show')
            // change icon
            toggle.classList.toggle('bx-x')
            // add padding to body
            bodypd.classList.toggle('body-pd')
            // add padding to header
            headerpd.classList.toggle('body-pd')
        })
    }
}

showNavbar('header-toggle','nav-bar','body-pd','header');
// showNavbar('header-toggle-sub','nav-bar-sub','body-pd-sub','header-sub');

/*===== LINK ACTIVE  =====*/ 

$(function() {
    let pageName = location.pathname.split('/').slice(-1)[0];
    let currentLink = $('.nav__list a[href*="'+pageName+'"]');
    if (currentLink) {
      $('.nav__list .nav__link').removeClass('active');
      currentLink.addClass('active');
    };
    
  });


function logout(){
  const url = "http://localhost:8001/Expense/api/users/logout";
  fetch(url).then();
   window.location.href="login.jsp";
   
}
