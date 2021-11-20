let fas = document.getElementById("fa-chevron-down");
fas.addEventListener("click", (e) => scroll(e));

let seeMore = document.getElementById("see-more");
seeMore.addEventListener("click", (e) => scroll(e));

function scroll(e) {
    e.preventDefault();
    let scrollTop = function () {
        window.scrollTo(0, 750);
    };
    scrollTop()
}
