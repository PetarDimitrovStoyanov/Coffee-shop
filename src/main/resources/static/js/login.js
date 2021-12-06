const emailInput = document.getElementById("email");
emailInput.addEventListener("focusout", () => {
    const errorP = document.getElementById("error-email");
    if(!emailInput.value.includes("@")){
        errorP.style.display = "block";
    } else {
        errorP.style.display = "none";
    }
})

const emailPassword = document.getElementById("password");
emailPassword.addEventListener("focusout", () => {
    const errorP = document.getElementById("error-password");
    if(emailInput.value.length < 2){
        errorP.style.display = "block";
    } else {
        errorP.style.display = "none";
    }
})