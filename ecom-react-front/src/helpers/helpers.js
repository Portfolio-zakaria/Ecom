export const empthCart = (callback) => {
    localStorage.removeItem('cart');
    callback();
}