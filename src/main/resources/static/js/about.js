let moonElement = document.getElementById("moonMilk")
moonElement.addEventListener("click", () => {
    window.location.href("");
})

let seeMore = document.getElementById("see-more")
seeMore.addEventListener("click", (e) => {
    e.preventDefault();
    let scrollTop = function () {
        window.scrollTo(0, 650);
    };
    scrollTop()
});

let downArrow = document.getElementById("down")
downArrow.addEventListener("click", (e) => {
    e.preventDefault();
    let scrollTop = function () {
        window.scrollTo(0, 1750);
    };
    scrollTop()
});

let downArrowTwo = document.getElementById("down-two")
downArrowTwo.addEventListener("click", (e) => {
    e.preventDefault();
    let scrollTop = function () {
        window.scrollTo(0, 3000);
    };
    scrollTop()
});
