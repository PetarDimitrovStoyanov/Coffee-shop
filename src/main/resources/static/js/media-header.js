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

const header = document.querySelectorAll('.header');
const logo = document.querySelectorAll('.logo-three')[0];

header.addEventListener("mouseover", () => {
    logo.classList.add("brown-txt");
})

header.addEventListener("mouseout", () => {
    logo.classList.remove("brown-txt");
})
