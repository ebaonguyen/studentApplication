CREATE TABLE Users (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100),
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100),
    major VARCHAR(50),
    school_year INT
);

CREATE TABLE Posts (
	post_id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(300) NOT NULL,
    date_posted DATE,
    class VARCHAR(100),
    status BOOLEAN,
    number_of_comments INT,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Acceptance (
    date_accepted DATE,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    PRIMARY KEY (user_id, post_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (post_id) REFERENCES Posts(post_id)
);

CREATE TABLE Comments (
	comment_id INT AUTO_INCREMENT PRIMARY KEY,
    comment VARCHAR(300),
    date_commented DATE,
	user_id INT NOT NULL, 
    post_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (post_id) REFERENCES Posts(post_id)
);

CREATE TABLE Messages (
	message VARCHAR(300),
    time_sent TIMESTAMP,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    FOREIGN KEY (sender_id) REFERENCES Users(user_id),
    FOREIGN KEY (receiver_id) REFERENCES Users(user_id)
);

    