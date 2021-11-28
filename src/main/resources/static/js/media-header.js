const hamburger = document.querySelector('.hamburger2');
const navMenu = document.querySelector('.nav-menu');

hamburger.addEventListener("click", () => {
    hamburger.classList.toggle("active");
    navMenu.classList.toggle("active");
});

document.querySelectorAll('.nav-link').forEach(n => n.addEventListener("click", () => {
    hamburger.classList.remove("active");
    navMenu.classList.remove("active");
}));

const header2 = document.querySelectorAll('.header')[0];
const logo = document.querySelectorAll('.logo-three')[0];

header2.addEventListener("mouseover", () => {
    try {
        logo.classList.add("brown-txt");
        logo.classList.add("brown-txt");
    } catch (e){

    }
})

header2.addEventListener("mouseout", () => {
    try{
        logo.classList.remove("brown-txt");
    } catch (e){

    }

})
