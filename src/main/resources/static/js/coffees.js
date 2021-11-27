fetch('http://localhost:8080/api/products')
    .then(response => response.json())
    .then(data => {
        data.forEach(product => {
            const html = `<article class="wrapper-item">
       <div class="img-wrapper-item"><img src="data:image/jpg;base64,${product['picture']}" alt="no picture available">
       </div>
          <form th:action="@{/products}" th:method="POST" class="item-form">
            <input th:field="productName" name="productName" disabled type="text" value="${product['name']}" class="text brown">
            <div class="text-div-item">
                <p class="gold">PRICE:</p>
                <input th:field="price" name="price" class="gold" value="${product['price'].toFixed(2)}$">
            </div>
            <div class="buttons-div-item">
                <h4>Type</h4>
                <div class="item-button" id="type">
                    <input name="type" value="${product['type']}">
                </div>
            </div>
            <div class="submit-btn-wrapper">
                <div class="pieces-div">
                    <i class="fas fa-minus sign"></i>
                    <input disabled name="piece" type="text" id="qty1" name="qty" value="1">
                    <i class="fas fa-plus sign"></i>
                </div>
                <button type="submit" class="sbm-btn">ADD TO BAG<i class="transp fas fa-shopping-bag distance"></i></button>
            </div>
            <a href="/admin/edit-product/${product['id']}" class="btn-product">EDIT PRODUCT</a>
          </form>
    </article>`;
            document.getElementById("products").insertAdjacentHTML("afterend", html);
        });
    })
    .then(data => {
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
    });

