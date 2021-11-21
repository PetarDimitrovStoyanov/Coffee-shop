let element = document.getElementsByClassName('header')[0];
element.addEventListener("mouseover", (e) => {
    e.preventDefault();
    let a = document.getElementsByClassName('transp');
    Array.from(a).forEach(e => e.style.color = "#4C3128");
    element.style.background = "white";
})

element.addEventListener("mouseout", (e) => {
    e.preventDefault();
    let a = document.getElementsByClassName('transp');
    Array.from(a).forEach(e => e.style.color = "white");
    element.style.background = "transparent"
})

element.addEventListener("mouseover", (e) => {
    e.preventDefault();
    let a = document.getElementsByClassName('transp-c');
    Array.from(a).forEach(e => e.style.color = "#4C3128");
    element.style.background = "white";
})

element.addEventListener("mouseout", (e) => {
    e.preventDefault();
    let a = document.getElementsByClassName('transp-c');
    Array.from(a).forEach(e => e.style.color = "white");
    element.style.background = "transparent"
})

element.addEventListener("mouseover", (e) => {
    e.preventDefault();
    let a = document.getElementsByClassName('logo-three');
    Array.from(a).forEach(e => e.style.color = "#4C3128");
    element.style.background = "white";
})

element.addEventListener("mouseout", (e) => {
    e.preventDefault();
    let a = document.getElementsByClassName('logo-three');
    Array.from(a).forEach(e => e.style.color = "white");
    element.style.background = "transparent"
})

element.addEventListener("mouseover", (e) => {
    e.preventDefault();
    let a = document.getElementsByClassName('products-logo');
    Array.from(a).forEach(e => e.style.color = "#4C3128");
    element.style.background = "white";
})

element.addEventListener("mouseout", (e) => {
    e.preventDefault();
    let a = document.getElementsByClassName('products-logo');
    Array.from(a).forEach(e => e.style.color = "white");
    element.style.background = "transparent"
})


