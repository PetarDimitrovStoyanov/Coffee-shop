fetch('http://localhost:8080/api/products')
    .then(response => response.json())
    .then(data => {
        console.log(data);
        data.forEach(product => {
            const html = `<article class="wrapper-item">
       <div class="img-wrapper-item"><img src="data:image/jpg;base64,${product['picture']}" alt="no picture available">
       </div>
          <form action="" class="item-form">
            <input disabled type="text" value="${product['name']}" class="text brown">
            <div class="text-div-item">
                <p class="gold">PRICE:</p>
                <p class="gold">${product['price'].toFixed(2)}$</p>
            </div>
            <div class="buttons-div-item">
                <h4>Type</h4>
                <div class="item-button" id="type">
                    <h3>${product['type']}</h3>
                </div>
            </div>
            <div class="submit-btn-wrapper">
                <div class="pieces-div">
                    <i class="fas fa-minus sign"></i>
                    <input disabled type="text" id="qty1" name="qty" value="1">
                    <i class="fas fa-plus sign"></i>
                </div>
                <button type="submit" class="sbm-btn">ADD TO BAG<i class="transp fas fa-shopping-bag distance"></i></button>
            </div>
            <a href="/admin/edit-product/${product['id']}" class="btn-product">EDIT PRODUCT</a>
          </form>
    </article>`;
            document.getElementById("products").insertAdjacentHTML("afterend", html);
        });
    });


/*
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
})*/
