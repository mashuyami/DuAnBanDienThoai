CREATE DATABASE WEB_BANDT
CREATE TABLE account (
  id INT PRIMARY KEY IDENTITY(1,1),
  fullname NVARCHAR(255) NOT NULL,
  username NVARCHAR(255) NOT NULL,
  password NVARCHAR(255) NOT NULL,
  avatar NVARCHAR(255) NOT NULL,
  email NVARCHAR(255) NOT NULL UNIQUE,
  admin INT NOT NULL,
  actived INT NOT NULL
);
INSERT INTO account (fullname, username, password, avatar, email, admin, actived)
VALUES
  ('John Doe', 'johndoe', 'password1', 'avatar1.jpg', 'johndoe@example.com', 0, 1),
  ('Jane Smith', 'janesmith', 'password2', 'avatar2.jpg', 'janesmith@example.com', 1, 1),
  ('David Johnson', 'davidjohnson', 'password3', 'avatar3.jpg', 'davidjohnson@example.com', 0, 0),
  ('Sarah Williams', 'sarahwilliams', 'password4', 'avatar4.jpg', 'sarahwilliams@example.com', 0, 1),
  ('Michael Brown', 'michaelbrown', 'password5', 'avatar5.jpg', 'michaelbrown@example.com', 1, 1);
CREATE TABLE categories (
  id INT PRIMARY KEY IDENTITY(1,1),
  name NVARCHAR(255) NOT NULL,
  descriptions NVARCHAR(255) NOT NULL
);
INSERT INTO categories (name, descriptions)
VALUES
  ('Category 1', 'Description 1'),
  ('Category 2', 'Description 2'),
  ('Category 3', 'Description 3'),
  ('Category 4', 'Description 4'),
  ('Category 5', 'Description 5');
CREATE TABLE orders (
  id INT PRIMARY KEY IDENTITY(1,1),
  shipping_address NVARCHAR(255) NOT NULL,
  order_date NVARCHAR(255) NOT NULL,
  order_status INT NOT NULL,
  account_id INT NOT NULL FOREIGN KEY REFERENCES account(id)
);
INSERT INTO orders (shipping_address, order_date, order_status, account_id)
VALUES
  ('Address 1', '2023-05-18', 1, 1),
  ('Address 2', '2023-05-19', 2, 2),
  ('Address 3', '2023-05-20', 1, 3),
  ('Address 4', '2023-05-21', 2, 4),
  ('Address 5', '2023-05-22', 1, 5);
CREATE TABLE order_detail (
  id INT PRIMARY KEY IDENTITY(1,1),
  price FLOAT NOT NULL,
  quantity INT NOT NULL,
  order_id INT NOT NULL FOREIGN KEY REFERENCES orders(id),
  product_id INT NOT NULL FOREIGN KEY REFERENCES product(id)
);
INSERT INTO order_detail (price, quantity, order_id, product_id)
VALUES
  (10.5, 1, 1, 1),
  (20.5, 2, 1, 2),
  (15.0, 3, 2, 1),
  (12.0, 1, 3, 3),
  (8.5, 2, 3, 4);
CREATE TABLE payment (
  id INT PRIMARY KEY IDENTITY(1,1),
  name NVARCHAR(255) NOT NULL,
  descriptions NVARCHAR(255) NOT NULL,
  account_id INT NOT NULL FOREIGN KEY REFERENCES account(id)
);
INSERT INTO payment (name, descriptions, account_id)
VALUES
  ('Payment 1', 'Description 1', 1),
  ('Payment 2', 'Description 2', 2),
  ('Payment 3', 'Description 3', 3),
  ('Payment 4', 'Description 4', 4),
  ('Payment 5', 'Description 5', 5);
CREATE TABLE sale_off (
  id INT PRIMARY KEY IDENTITY(1,1),
  descriptions NVARCHAR(255) NOT NULL,
  product_id INT NOT NULL FOREIGN KEY REFERENCES product(id)
);
INSERT INTO sale_off (descriptions, product_id)
VALUES
  ('Sale off 1', 1),
  ('Sale off 2', 2),
  ('Sale off 3', 3),
  ('Sale off 4', 4),
  ('Sale off 5', 5);
CREATE TABLE product (
  id INT PRIMARY KEY IDENTITY(1,1),
  name NVARCHAR(255) NOT NULL,
  price FLOAT NOT NULL,
  image NVARCHAR(255) NOT NULL,
  available INT NOT NULL,
  size NVARCHAR(10) NOT NULL,
  brand NVARCHAR(255) NOT NULL,
  descriptions NVARCHAR(255) NOT NULL,
  category_id INT NOT NULL FOREIGN KEY REFERENCES categories(id)
);
INSERT INTO product (name, price, image, available, size, brand, descriptions, category_id)
VALUES
  ('Product 1', 10.5, 'image1.jpg', 1, 'S', 'Brand 1', 'Description 1', 1),
  ('Product 2', 20.5, 'image2.jpg', 0, 'M', 'Brand 2', 'Description 2', 2),
  ('Product 3', 15.0, 'image3.jpg', 1, 'L', 'Brand 3', 'Description 3', 3),
  ('Product 4', 12.0, 'image4.jpg', 1, 'XL', 'Brand 4', 'Description 4', 4),
  ('Product 5', 8.5, 'image5.jpg', 0, 'S', 'Brand 5', 'Description 5', 5);
  