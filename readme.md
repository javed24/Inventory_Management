# Inventory Management Application

This application allows anonymous users the ability to shop from an online store. Additionally, it allows the administrator to manage the inventory for the store. It's a web based application which is built using Java Server Pages (JSP). Some features of the application are:

### Inventory Manager

Provides store administrator the ability to add and remove items and/or services to the store's inventory. All items are added and retrieved from a MySQL database.

### StoreFront

Displays the names and prices of all items/services that are for sale in the store, and that have a quantity greater than or equal to one.

From this page a user will be able to:

  * Add an item to their shopping cart
  * Click on an item to view its details. The item's details are displayed on a different page.
  * View the number of items in their shopping cart
  * Click on their shopping cart to view it's contents. The contents of the user's shopping cart are displayed on a different page as well.

### Item details

This page displays the name of the item/service, its details, quantity, and price.

From this page, a user will be able to:

  * Add the item to their shopping cart
  * Click on their shopping cart to view its contents.
  * Click to go back to the Storefront

### Shopping Cart

The Shopping Cart page displays detailed list of all items in the user's shopping cart

  * The quantity of each item in each user's cart. This is not the number available in the store.
  * The price of each item
  * The total cost of each *`item x quantity`* in cart
  * The total cost of all items in the shopping cart.

The shopping cart page also allows the user to navigate to Checkout.


### Checkout

The checkout page prompts the user for their full name and e-mail address. It also displays an outline of all items they are purchasing, the quantity, and the total for their order. The page has a complete purchase button that finalizes the order.

Upon submission of the order, the checkout page performs error-checking to ensure a first and last name, and an e-mail address are entered. If all form fields are correctly entered, it deducts the number of items purchased from the store's inventory, and records the transaction in a transaction history stored in the MySQl database.
