fetch('http://localhost:8080/api/products')
    .then(response => response.json())
    .then(data => {
        data.forEach(
            p => {
                const url = `data:image/jpg;base64,`;
                const product = document.querySelectorAll('.prefix');
                product.forEach(prod => {
                    if (prod.id == p.id) {
                        const imgElem = prod.querySelector('.img-flag');
                        imgElem.src = url + `${p.picture}`;
                    }
                });
            });
    });
