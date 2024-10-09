query show{
getProduct(id: 51) {
id, name, price, currency, quantity, categoryName
}
}

mutation add{
addProduct( product : {
name: "hello",
price: 20.59,
currency: "EUR",
quantity: 120,
categoryId: 99
}) {
id,
name
}
}

mutation update{
updateProduct( id: 50, product : {
name: "hello",
price: 20.59,
currency: "EUR",
quantity: 120,
categoryId: 1
}) {
id,
name
}
}

mutation remove{
removeProduct(id: 50)
}

mutation addParms($n: String, $p: Float, $c: String, $q: Int, $x: Float) {
    addProduct( product : {
    name: $n,
    price: $p,
    currency: $c,
    quantity: $q,
    categoryId: $x
    }) {  id, name }
}
