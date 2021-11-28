function totalSum() {
    let amount = 0;
    let totals = document.getElementsByClassName('total');

    Array.from(totals).forEach(t => {
        amount = Number(t.textContent) + Number(amount);
    })

    let totalSum = document.getElementById("totalSum");
    totalSum.textContent = amount.toFixed(2) + "";
}

totalSum();

fetch('http://localhost:8080/api/products')
    .then(response => response.json())
    .then(data => {
        data.forEach(
            p => {
                const url = `data:image/jpg;base64,`;
                const img = document.querySelectorAll('.img-shopping-cart');
                img.forEach(i => {
                    if (i.id == p.id) {
                        i.src = url + `${p.picture}`;
                    }
                });
            });
    });
