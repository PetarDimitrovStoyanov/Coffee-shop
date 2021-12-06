const checkbox2 = document.getElementById("checkBox");
checkbox2.addEventListener("click", () => {
    if (checkbox2.checked === true) {
        console.log("is checked")
        const owner = document.getElementById('owner').value = "N/A";
        const cvv = document.getElementById('cvv').value = "cvv";
        const cardNumber = document.getElementById('cardNumber').value = "0000 0000 0000 0000";
    } else {
        const owner = document.getElementById('owner').value = "";
        const cvv = document.getElementById('cvv').value = "";
        const cardNumber = document.getElementById('cardNumber').value = "";
    }
})

