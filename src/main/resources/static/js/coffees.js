fetch('http://localhost:8080/api/products')
    .then(response => response.json())
    .then(data => {
        data.forEach(product => {
            const html = `<article class="wrapper-item">
       <div class="img-wrapper-item"><img src="data:image/jpg;base64,${product['picture']}" alt="no picture available">
       </div>
          <form action="" class="item-form">
            <input disabled type="text" value="${product['name']}" class="text brown">
            <div class="text-div-item">
                <p class="gold">LIGHT</p>
                <p class="gold">${product['price']}</p>
            </div>
            <div class="buttons-div-item">
                <label for="type1">Select the beans type</label>
                <select class="item-button" id="type1" aria-label="Default select example">
                    <option value="1">WHOLE BEANS</option>
                    <option value="2">GRINDED</option>
                </select>
            </div>
            <div class="submit-btn-wrapper">
                <div class="pieces-div">
                    <i class="fas fa-minus sign"></i>
                    <input disabled type="text" id="qty1" name="qty" value="1">
                    <i class="fas fa-plus sign"></i>
                </div>
                <button type="submit" class="sbm-btn">ADD TO BAG<i class="transp fas fa-shopping-bag distance"></i></button>
            </div>
            <a th:href="@{/admin/edit-product}" class="btn-product">EDIT PRODUCT</a>
          </form>
    </article>`;
            document.getElementById("products").insertAdjacentHTML("afterend", html);
        });
    });
