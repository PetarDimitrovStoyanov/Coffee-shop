let signs = document.getElementsByClassName('sign');
for (let i = 0; i < signs.length; i++) {
    let sign = signs[i];
    sign.addEventListener("click", (e) => {
        e.preventDefault();
        let pieces = e.target.parentNode.querySelector('.pieces');
        if (e.target.classList.contains('fa-plus')) {
            let inputValue = Number(pieces.value);
            inputValue += 1;
            pieces.value = inputValue + "";

            let total = pieces.parentNode.parentNode.parentNode.querySelectorAll(".step")[0];
            let totalValue = total.querySelectorAll(".total")[0]

            let price = pieces.parentNode.parentNode.parentNode.querySelectorAll(".light-brown-text")[0];
            let sum = (Number(price.textContent) * Number(pieces.value)).toFixed(2)

            totalValue.textContent = sum + "";
            totalSum();
        } else if (e.target.classList.contains('fa-minus')) {
            let inputValue = Number(pieces.value);
            inputValue -= 1;
            if (inputValue > 0) {
                pieces.value = inputValue + "";

                let total = pieces.parentNode.parentNode.parentNode.querySelectorAll(".step")[0];
                let totalValue = total.querySelectorAll(".total")[0]

                let price = pieces.parentNode.parentNode.parentNode.querySelectorAll(".light-brown-text")[0];
                let sum = (Number(price.textContent) * Number(pieces.value)).toFixed(2)
                if (sum > 0) {
                    totalValue.textContent = sum + "";
                    totalSum();
                }
            }

        }
    })
}

function totalSum() {
    let amount = 0;
    let totals = document.getElementsByClassName('total');

    Array.from(totals).forEach(t => {
        amount = Number(t.textContent) + Number(amount);
    })

    let totalSum = document.getElementById("totalSum");
    totalSum.textContent = amount.toFixed(2) + "";

    let discountMessage = document.getElementById("discount");
    let discountMessageHundred = document.getElementById("discountHundred");
    if (Number(totalSum.textContent) <= 35) {
        discountMessage.style.display = "none";
        discountMessageHundred.style.display = "none";
    } else if (Number(totalSum.textContent) <= 99 && Number(totalSum.textContent) >= 36) {
        discountMessage.style.display = "block";
        discountMessageHundred.style.display = "none";
        totalSum.textContent = (Number(totalSum.textContent) - 5).toFixed(2) + "";
    } else {
        discountMessage.style.display = "none";
        discountMessageHundred.style.display = "block";
        totalSum.textContent = (Number(totalSum.textContent) - 15).toFixed(2) + "";
    }
}

totalSum();
