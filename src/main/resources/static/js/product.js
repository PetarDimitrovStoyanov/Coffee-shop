let elements = document.getElementsByClassName("sbm-btn");

for (let i = 0; i < elements.length; i++) {
    elements[i].addEventListener("mouseover", (e) => {
        e.preventDefault();
        let i = e.target.parentNode.querySelector(`.transp`);
        i.style.color = "#D2102A";
    })
}

for (let i = 0; i < elements.length; i++) {
    elements[i].addEventListener("mouseout", (e) => {
        e.preventDefault();
        let i = e.target.parentNode.querySelector(`.transp`);
        i.style.color = "white";
    })
}

let elem = document.querySelectorAll('.sign');
elem.forEach(e => {
    e.addEventListener('click', () => {
        console.log("hello")
    })
})





