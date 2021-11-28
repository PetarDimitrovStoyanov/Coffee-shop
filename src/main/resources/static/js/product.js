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

let signs = document.getElementsByClassName('sign');
for (let i = 0; i < signs.length; i++) {
    let sign = signs[i];
    sign.addEventListener("click", (e) => {
        e.preventDefault();
        let input = e.target.parentNode.querySelector('input');
        if (e.target.classList.contains('fa-plus')) {
            let inputValue = Number(input.value);
            inputValue += 1;
            input.value = inputValue + "";
        } else if (e.target.classList.contains('fa-minus')) {
            let inputValue = Number(input.value);
            inputValue -= 1;
            if (inputValue > 0) {
                input.value = inputValue + "";
            }
        }
    })
}

document.getElementsByClassName("header")[0].addEventListener("mouseover", () => {
    document.getElementsByClassName("logo-add-product")[0].style.color = "#4D3127";
})

document.getElementsByClassName("header")[0].addEventListener("mouseout", () => {
    document.getElementsByClassName("logo-add-product")[0].style.color = "white";
})






