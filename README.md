# santander-lets-code-final-project
E-commerce project for Santander Becas

Getting started:

1. Install Docker and Docker Compose/Docker Desktop
2. Download source code
3. Go to project source /back-end and run the command: "docker compose up"

4. Available endpoints are:
  - 8080/product
  - 8080/review
  - 8081/order
  - 8082/user
  - 8082/user/{userId}/cart


5. Funcionality is as follows:
    a. POST a Product to 8080/product
  
    b. POST an User to 8082/user
    
      b.1. the application will generate a Cart for the user just created
      
    c. PUT a product in the cart 8082/userId/cart
    
    d. POST an Order to 8081/order
    
      d.1. the Order takes a Cart as input, placing an Order takes all items in cart and leaves it empty
      
      d.2. after placing an Order, Review service will be available for that purchase
      
    e. POST a Review to 8080/review
    
 Done! you can now perform GET requests to the same endpoints and see what you made!
    
    
ProductRequest: POST | 9090/product | request-body: {"name":string, "category":(TECHNOLOGY or BEAUTY), "price":float, "brand":string}

UserRequest: POST | 8082/user | request-body: {"name":string, "address":string}

CartaddItem: PUT | 8082/user/{userId}/cart | request-param: addOrRemove:boolean & productId:integer

OrderRequest: POST | 8081/order | request-param: cartId:integer

ReviewRequest: POST | 8080/review | request-body: {"productId":integer, "orderId":integer, "content":text, "rating":float}
