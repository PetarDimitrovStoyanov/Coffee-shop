let checkBox = document.getElementById("checkBox");

checkBox.addEventListener("click", () => {
    let inputs = document.querySelectorAll(".form-control");
    if (checkBox.checked) {
        Array.from(inputs).forEach(i => {
            i.disabled = true;
        })
    } else {
        Array.from(inputs).forEach(i => {
            i.disabled = false;
        })
    }
})





