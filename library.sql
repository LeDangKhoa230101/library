create database library;
use library;

CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(50),
    publication_year INT,
    quantity INT,
    is_available INT
);

INSERT INTO books (title, author, genre, publication_year, quantity, is_available) VALUES 
('To Kill a Mockingbird', 'Harper Lee', 'Fiction', 1960, 5, 1),
('1984', 'George Orwell', 'Science Fiction', 1949, 3, 1),
('Pride and Prejudice', 'Jane Austen', 'Romance', 1813, 7, 1),
('The Great Gatsby', 'F. Scott Fitzgerald', 'Classic', 1925, 4, 0),
('The Hobbit', 'J.R.R. Tolkien', 'Fantasy', 1937, 6, 1),
('The Catcher in the Rye', 'J.D. Salinger', 'Fiction', 1951, 8, 0);

SELECT * FROM books;
SELECT COUNT(*) AS total_available_books FROM books WHERE is_available = 1;

SELECT genre, COUNT(*) AS quantity FROM books GROUP BY genre;

SELECT * FROM books WHERE is_available = 1;

UPDATE books SET title = ?, author = ?, genre = ?, publication_year = ?, quantity = ? WHERE title = ?;

SELECT * FROM books WHERE title LIKE '%Pride%';

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users (username, password) VALUES
('john doe', '123467'),
('jane smith', '223235'),
('bob jones', '812367');
SELECT * FROM users WHERE username = 'john doe';
SELECT * FROM users WHERE username = 'john doe' AND password = 123467;

CREATE TABLE loans (
    loan_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    book_id INT,
    loan_date DATE,
    due_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

INSERT INTO loans (user_id, book_id, loan_date, due_date) VALUES
(1, 1, '2023-08-5', '2023-08-9'),
(1, 2, '2023-08-15', '2023-08-29'),
(2, 5, '2023-08-12', '2023-08-26'),
(3, 1, '2023-08-10', '2023-08-24');

SELECT COUNT(*) AS total_borrowed_books
FROM loans
WHERE due_date IS NULL;

select * from loans;

SELECT loan_id FROM loans WHERE book_id = 1;

INSERT INTO loans (user_id, book_id, loan_date, due_date) VALUES (4, 47, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 14 DAY));

delete from loans where user_id = 4;

SELECT loans.loan_id, books.title, books.author, books.genre, books.publication_year, loans.due_date
FROM books 
INNER JOIN loans ON books.book_id = loans.book_id 
WHERE loans.user_id = 4;

UPDATE loans SET due_date = CURDATE() WHERE loan_id IN (?) AND due_date IS NULL;

UPDATE loans SET due_date = CURDATE() WHERE CONCAT(',', ? , ',') LIKE CONCAT('%,', loan_id, ',%') AND due_date IS NULL;


