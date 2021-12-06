fetch('http://localhost:8080/api/products')
    .then(response => response.json())
    .then(data => {
        data.forEach(
            p => {
                const url = `data:image/jpg;base64,`;
                const product = document.querySelector('.img-order-it');
                console.log(product.id + " " + p.id)
                if (product.id == p.id) {
                    product.src = url + `${p.picture}`;
                }
            });
    });
