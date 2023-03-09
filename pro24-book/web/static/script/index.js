/**
 * 添加到购物车方法
 * @param bookId
 */
function addCart(bookId) {
    window.location.href = "cart.do?operate=addCart&bookId=" + bookId;
}